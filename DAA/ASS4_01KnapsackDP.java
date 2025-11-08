import java.util.*;

public class ASS4_01KnapsackDP {

    /*
     💡 Problem Statement:
     You are given 'n' items, each having a profit (value) and a weight.
     You have a bag (knapsack) with limited capacity 'W'.
     The goal is to pick some items (without splitting) such that:
         ➤ Total weight ≤ W
         ➤ Total profit is maximum.

     This is the **0/1 Knapsack Problem** — each item can be either:
         0 → not taken
         1 → taken completely (no fractions).

     ✅ Why Dynamic Programming (DP)?
     - The brute force way tries all combinations → exponential time (2^n).
     - DP avoids repetition by storing results of smaller subproblems in a table.
       This method is efficient and ensures the best (optimal) solution.
    */

    static int knapSack(int W, int wt[], int val[], int n) {
        /*
         dp[i][w] means:
         → The maximum profit we can get using the first 'i' items
           when the knapsack capacity is 'w'.

         Table size: (n+1) x (W+1)
         - +1 is for including base conditions (0 items, 0 capacity)
        */
        int dp[][] = new int[n + 1][W + 1];

        // Build table in bottom-up manner (starting from smaller problems)
        for (int i = 0; i <= n; i++) { // i = number of items considered
            for (int w = 0; w <= W; w++) { // w = current capacity

                // Base case: if we have no items or zero capacity → profit = 0
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }

                // If the current item's weight is less than or equal to current capacity
                // we have two options:
                // 1️⃣ Include the item (add its value + remaining capacity profit)
                // 2️⃣ Exclude the item (keep previous max profit)
                // Choose the option that gives maximum profit.
                else if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        val[i - 1] + dp[i - 1][w - wt[i - 1]],  // include item i-1
                        dp[i - 1][w]                            // exclude item i-1
                    );
                }

                // If item is too heavy, we can’t include it → skip it.
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // ✅ Final Answer:
        // dp[n][W] holds the maximum profit for all items and given capacity.
        return dp[n][W];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int val[] = new int[n]; // profit of each item
        int wt[] = new int[n];  // weight of each item

        // Step 2: Input profit and weight for each item
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter profit and weight of item " + (i + 1) + ": ");
            val[i] = sc.nextInt();
            wt[i] = sc.nextInt();
        }

        // Step 3: Input knapsack capacity
        System.out.print("\nEnter capacity of knapsack: ");
        int W = sc.nextInt();

        // Step 4: Call the DP function to calculate maximum profit
        int maxProfit = knapSack(W, wt, val, n);

        // Step 5: Output the result
        System.out.println("\nMaximum Profit = " + maxProfit);

        sc.close();
    }
}

/*
--------------------------------------------
🧠 SIMPLE EXPLANATION (For Viva)
--------------------------------------------
Example:
Items (Profit, Weight)
 1: (60, 10)
 2: (100, 20)
 3: (120, 30)
Capacity W = 50

We build a DP table of size (3+1)x(50+1)
Each cell dp[i][w] stores best profit using first i items and capacity w.

For i=3 and w=50:
✅ Include item 3: profit = 120 + dp[2][20]
✅ Exclude item 3: profit = dp[2][50]
Choose the max → 220

Hence, Maximum Profit = 220

--------------------------------------------
⚙️ TIME AND SPACE COMPLEXITY
--------------------------------------------
✅ Time Complexity: O(n * W)
   - We fill an (n+1) x (W+1) table, each cell = O(1) operation.
   - Example: If 4 items and capacity 10 → 4*10 = 40 operations.

✅ Space Complexity: O(n * W)
   - Because we store all results in the dp[][] table.

🧩 Space Optimization (if asked):
   - We can reduce it to O(W) using a 1D array and iterating capacity backward.

--------------------------------------------
🎯 KEY POINTS TO REMEMBER
--------------------------------------------
- 0/1 means: item is either taken or not (no fractions allowed)
- Works when we need exact combinations, not partial division.
- DP ensures no repeated subproblems, unlike recursion.
- The last cell dp[n][W] gives the maximum profit.

--------------------------------------------
💬 POSSIBLE VIVA QUESTIONS AND SHORT ANSWERS
--------------------------------------------
Q1. What is the 0/1 Knapsack Problem?
→ Choose items to maximize profit without exceeding capacity.
   Each item can be either included (1) or excluded (0).

Q2. Why do we use DP here?
→ Because the recursive or brute force approach repeats subproblems,
   DP stores them and avoids recalculating, making it efficient.

Q3. What is stored in dp[i][w]?
→ Best profit using first i items and capacity w.

Q4. What are the base conditions?
→ When i = 0 (no items) or w = 0 (no capacity) → profit = 0.

Q5. What is the formula used?
→ dp[i][w] = max(val[i-1] + dp[i-1][w - wt[i-1]], dp[i-1][w]) if wt[i-1] <= w
   else dp[i][w] = dp[i-1][w].

Q6. What is time and space complexity?
→ Time = O(n * W), Space = O(n * W).

Q7. Can we make it more space-efficient?
→ Yes, use a 1D array of size W+1 (Space = O(W)).

Q8. What is the difference between 0/1 and Fractional Knapsack?
→ 0/1 = No splitting, uses DP.
   Fractional = Can split items, uses Greedy algorithm.

Q9. What is the real-world use case?
→ Resource allocation, investment decisions, cargo loading, etc.

Q10. Why can't Greedy approach work here?
→ Because taking the highest ratio item first may not give the best total profit
   when items cannot be divided.

--------------------------------------------
📌 ONE-LINER SUMMARY (say confidently)
"This code uses Dynamic Programming to solve the 0/1 Knapsack problem
in O(n*W) time and O(n*W) space, finding the maximum profit without
exceeding the bag's capacity."
--------------------------------------------
*/
