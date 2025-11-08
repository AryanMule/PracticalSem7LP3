import java.util.*;

public class ASS4_01KnapsackDP {

    // Function to find maximum profit
    static int knapSack(int W, int wt[], int val[], int n) {
        int dp[][] = new int[n + 1][W + 1];

        // Build table dp[][] bottom-up
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(
                        val[i - 1] + dp[i - 1][w - wt[i - 1]],  // include item
                        dp[i - 1][w]                            // exclude item
                    );
                else
                    dp[i][w] = dp[i - 1][w]; // item too heavy
            }
        }

        return dp[n][W]; // bottom-right cell = max profit
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int val[] = new int[n];
        int wt[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter profit and weight of item " + (i + 1) + ": ");
            val[i] = sc.nextInt();
            wt[i] = sc.nextInt();
        }

        System.out.print("\nEnter capacity of knapsack: ");
        int W = sc.nextInt();

        int maxProfit = knapSack(W, wt, val, n);
        System.out.println("\nMaximum Profit = " + maxProfit);

        sc.close();
    }
}
