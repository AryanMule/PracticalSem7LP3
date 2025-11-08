/*1) Problem statement (easy language)
You have several items. Each item has:
a weight (how much space it takes), and
a profit (value you get if you take it).
You also have a knapsack with a fixed capacity (maximum total weight you can carry).
You can take fractions of an item (not only whole items). The goal: fill the knapsack to maximize total profit.
Greedy idea used here: take the item (or fraction) with the highest profit per unit weight first — i.e., maximize value density.

2) High-level algorithm (what the code does)
Compute profit/weight ratio for each item.
Sort items in descending order of ratio.
Iterate items in that order:
If the whole item fits, take it (add its full profit and weight).
If not, take as much as fits (a fraction) and add proportional profit, then stop (knapsack full).
Return the total profit collected.
This greedy choice (highest ratio first) is optimal for the fractional knapsack problem.*/


import java.util.*;

// Simple container for an item
class Item {
    int weight;        // weight (how much space it takes)
    int profit;        // profit/value gained if whole item is taken
    double ratio;      // profit per unit weight = profit / weight

    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        // compute ratio as double so fractional selection possible
        this.ratio = (double) profit / weight;
    }
}

public class ASS3_FractionalKnapsackGreedy {

    // Returns maximum profit achievable with given items and capacity.
    // Greedy idea: sort by profit/weight ratio (best value per weight first),
    // take whole items while they fit, then take a fraction of the next item.
    public static double getMaxProfit(Item[] items, int capacity) {
        // 1) Sort items in descending order of profit/weight ratio
        //    so the item giving most profit per unit weight is first.
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0.0; // accumulated profit
        int currentWeight = 0;    // how much weight we've put in the knapsack

        // 2) Iterate through items in sorted order
        for (Item item : items) {
            // If item fully fits, take it entirely
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;    // fill knapsack with this item
                totalProfit += item.profit;      // add full profit
            } else {
                // If it doesn't fit fully, take the fraction that fits
                int remaining = capacity - currentWeight; // space left
                if (remaining > 0) {
                    // profit contribution = ratio * remaining weight
                    totalProfit += item.ratio * remaining;
                }
                break; // knapsack is full — no need to check further items
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter weight and profit of item " + (i + 1) + ":");
            int weight = sc.nextInt();
            int profit = sc.nextInt();
            items[i] = new Item(weight, profit);
        }

        System.out.print("\nEnter knapsack capacity: ");
        int capacity = sc.nextInt();

        double maxProfit = getMaxProfit(items, capacity);
        System.out.println("\nMaximum Profit = " + maxProfit);

        sc.close();
    }
}

/*🔹 Final Complexity Summary

✅ Time Complexity:
O(nlogn)
(Sorting dominates; iteration is linear)

✅ Space Complexity:
O(n)
(Due to sorting’s temporary storage; otherwise constant auxiliary space)*/
