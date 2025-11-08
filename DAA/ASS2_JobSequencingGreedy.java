// 💡 Problem Statement
// You are given a list of jobs, each having:
// A deadline (the time by which it should be completed)
// A profit (earned if you complete it before or on the deadline)
// The goal is to schedule jobs in such a way that:
// You do not exceed the deadlines, and
// The total profit is maximized.

// The logic uses a Greedy Algorithm — meaning:
// At each step, pick the most profitable job available and try to schedule it as late as possible before its deadline.
// Why "as late as possible"?
// Because it leaves earlier time slots open for other jobs that have earlier deadlines.
import java.util.*;

// Job class holds ID, deadline and profit
class Job {
    char id;       // Job ID (A, B, C...)
    int deadline;  // Deadline in time slots (1,2,...)
    int profit;    // Profit if job is finished by its deadline

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class ASS2_JobSequencingGreedy {

    // Main algorithm: schedule jobs to maximize profit using greedy approach
    public static void jobSequencing(Job[] jobs) {
        int n = jobs.length;

        // 1) Sort jobs by profit in descending order
        //    We take most profitable jobs first so we don't miss big profits.
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // 2) Find maximum deadline among jobs
        //    That determines how many time slots we need to consider.
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // 3) Prepare arrays to store final sequence and mark occupied slots
        char[] result = new char[maxDeadline];     // which job is placed in each slot
        boolean[] slot = new boolean[maxDeadline]; // true if slot is already taken

        Arrays.fill(slot, false); // initially all slots are free

        int totalProfit = 0; // sum of profits of scheduled jobs
        int countJobs = 0;   // how many jobs scheduled

        // 4) Try to schedule each job in the latest possible free slot before its deadline
        //    Loop through jobs in order of descending profit.
        for (Job job : jobs) {
            // Check slots from job.deadline-1 down to 0 (0-based index)
            // Putting job as late as possible preserves earlier slots for other jobs.
            for (int j = Math.min(maxDeadline, job.deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {           // if this time slot is free
                    slot[j] = true;       // occupy it
                    result[j] = job.id;   // store which job goes here
                    totalProfit += job.profit;
                    countJobs++;
                    break;                // move to next job (job scheduled)
                }
            }
        }

        // 5) Print the chosen job sequence and totals
        System.out.print("Job sequence: ");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println("\nTotal Jobs Done: " + countJobs);
        System.out.println("Total Profit: " + totalProfit);
    }

    // Simple input method to test the algorithm
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
    }
}
/* ⚙️ Simplified Big-O Interpretation

If maxDeadline ≈ n (which often happens in practice):

Time: O(n²)
Space: O(n)

If deadlines are small relative to n:

Time: O(n log n) (sorting dominates)
Space: O(maxDeadline)*/
