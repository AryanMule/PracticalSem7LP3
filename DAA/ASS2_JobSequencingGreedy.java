// 💡 Problem Statement
// --------------------
// You are given a list of jobs, each having:
// 👉 A deadline (the time by which it should be completed)
// 👉 A profit (earned if you complete it before or on the deadline)
//
// Goal: Schedule jobs in such a way that
//   1️⃣ You do not exceed the deadlines
//   2️⃣ The total profit is maximized
//
// Algorithm Type: GREEDY
// -----------------------
// Always choose the job that gives the highest profit first.
// Try to schedule it as LATE AS POSSIBLE (before its deadline)
// so that earlier slots remain free for other jobs.
// This greedy choice gives the optimal solution.

import java.util.*;

// 🔹 Class to represent each Job with its properties
class Job {
    char id;       // Job ID (like A, B, C, ...)
    int deadline;  // Deadline in time slots (1, 2, 3, ...)
    int profit;    // Profit if job is done within its deadline

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class ASS2_JobSequencingGreedy {

    // ✅ Main function to find the sequence of jobs that maximizes total profit
    public static void jobSequencing(Job[] jobs) {
        int n = jobs.length;

        // Step 1️⃣: Sort all jobs by profit (descending)
        // We pick most profitable jobs first to avoid missing high-profit tasks.
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2️⃣: Find the maximum deadline value
        // This gives us the number of available time slots.
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Step 3️⃣: Prepare helper arrays
        char[] result = new char[maxDeadline];     // which job is placed in which slot
        boolean[] slot = new boolean[maxDeadline]; // keeps track of used/free time slots
        Arrays.fill(slot, false);                  // initially all time slots are free

        int totalProfit = 0; // total profit collected
        int countJobs = 0;   // total jobs successfully scheduled

        // Step 4️⃣: Schedule each job one by one
        // For each job (starting from highest profit):
        for (Job job : jobs) {
            // Try to place job in its latest available slot before deadline
            // Example: if job deadline=3, check slot 2, then 1, then 0 (0-based)
            for (int j = Math.min(maxDeadline, job.deadline) - 1; j >= 0; j--) {
                if (!slot[j]) { // if this slot is free
                    slot[j] = true;       // mark slot as occupied
                    result[j] = job.id;   // store job in result array
                    totalProfit += job.profit;
                    countJobs++;
                    break; // move to next job
                }
            }
        }

        // Step 5️⃣: Display the results
        System.out.print("\n✅ Job Sequence (Optimal Order): ");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }

        System.out.println("\nTotal Jobs Done: " + countJobs);
        System.out.println("Total Profit: " + totalProfit);
    }

    // Input and Execution
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Job " + (char) ('A' + i));
            System.out.print("Deadline: ");
            int deadline = sc.nextInt();
            System.out.print("Profit: ");
            int profit = sc.nextInt();
            jobs[i] = new Job((char) ('A' + i), deadline, profit);
        }

        jobSequencing(jobs);
        sc.close();
    }
}


/*
-------------------------------------------------------
🔹 DRY RUN EXAMPLE (Explain this in Viva)
-------------------------------------------------------
Jobs:
Job A → deadline=2, profit=100
Job B → deadline=1, profit=19
Job C → deadline=2, profit=27
Job D → deadline=1, profit=25
Job E → deadline=3, profit=15

Step 1: Sort by profit → A(100), C(27), D(25), B(19), E(15)

Max deadline = 3 → we have 3 time slots: [ _, _, _ ]

Now, assign jobs greedily:

- Job A → deadline=2 → put in slot 2 → [ _, A, _ ]
- Job C → deadline=2 → slot 2 taken → check slot 1 → [ C, A, _ ]
- Job D → deadline=1 → slot 1 taken → can’t fit → skip
- Job B → deadline=1 → slot 1 taken → skip
- Job E → deadline=3 → slot 3 free → [ C, A, E ]

✅ Final sequence: C, A, E  
✅ Total Profit = 27 + 100 + 15 = 142
✅ Jobs Done = 3
-------------------------------------------------------
*/


/*
=====================================================
🔹 TIME AND SPACE COMPLEXITY
=====================================================
Time Complexity:
👉 O(n log n)  → for sorting jobs by profit
👉 + O(n * maxDeadline)  → for scheduling each job in slots

So, overall ≈ O(n²) when deadlines ≈ n.

Space Complexity:
👉 O(maxDeadline) for slot[] and result[] arrays.

If deadlines are small:
→ Time ≈ O(n log n)
→ Space ≈ O(n)
=====================================================
*/


/*
=====================================================
🔹 THEORY + VIVA QUESTIONS (Simple Answers)
=====================================================

1️⃣ What is the Job Sequencing Problem?
   → A scheduling problem where we assign jobs to time slots 
     so that deadlines are not exceeded and total profit is maximized.

2️⃣ What approach is used here?
   → Greedy Algorithm — always take the job with highest profit first.

3️⃣ Why use “as late as possible” scheduling?
   → It keeps earlier time slots free for other jobs with tighter deadlines.

4️⃣ Can we take fractions of jobs?
   ❌ No, this is a 0/1 problem — a job is either fully done or not done at all.

5️⃣ How are jobs sorted?
   → By profit (descending order) so that we prioritize high-profit jobs.

6️⃣ What is the base condition to skip a job?
   → When no slot is free before its deadline.

7️⃣ What is the optimal substructure property here?
   → Each sub-solution (choosing jobs greedily till now) leads to global optimum.

8️⃣ What is the Greedy Choice Property?
   → Choosing the most profitable job first doesn’t affect future optimality.

9️⃣ Difference between Job Sequencing and Knapsack?
   | Feature              | Job Sequencing             | Knapsack Problem         |
   |----------------------|----------------------------|--------------------------|
   | Type                 | Scheduling                 | Selection                |
   | Fraction allowed?    | No                         | Fractional allowed (if fractional type) |
   | Goal                 | Maximize profit under time | Maximize profit under weight |
   | Approach             | Greedy                     | Greedy / Dynamic Programming |

🔟 Real-life Examples:
   - Scheduling tasks on limited CPUs
   - Project deadline planning
   - Job/task selection in production systems

=====================================================
*/

/*
💡 ONE-LINER SUMMARY FOR VIVA:
"Job Sequencing uses a greedy method — pick the most profitable jobs first 
and schedule each as late as possible before its deadline. 
This maximizes profit without missing deadlines. 
Time complexity ≈ O(n²), space O(n)."
*/
