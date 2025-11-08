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

class Job {

    char id;       // Job ID (A, B, C...)
    int deadline;  // Deadline (in time slots)
    int profit;    // Profit for completing the job

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class ASS2_JobSequencingGreedy {

    public static void jobSequencing(Job[] jobs) {
        int n = jobs.length;

        // 1️⃣ Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // 2️⃣ Find maximum deadline to define time slots
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // 3️⃣ Initialize result arrays
        char[] result = new char[maxDeadline];   // to store job sequence
        boolean[] slot = new boolean[maxDeadline]; // track if slot is occupied

        Arrays.fill(slot, false); // initially all time slots empty

        int totalProfit = 0;
        int countJobs = 0;

        // 4️⃣ Place each job in latest available slot before its deadline
        for (Job job : jobs) {
            for (int j = job.deadline - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = job.id;
                    totalProfit += job.profit;
                    countJobs++;
                    break; // move to next job
                }
            }
        }

        // 5️⃣ Display results
        System.out.print("Job sequence: ");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println("\nTotal Jobs Done: " + countJobs);
        System.out.println("Total Profit: " + totalProfit);
    }

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
