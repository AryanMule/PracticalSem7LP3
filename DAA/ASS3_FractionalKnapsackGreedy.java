/* 
1) Problem statement (easy language)
-----------------------------------
You have several items. Each item has:
   → a weight (how much space it takes)
   → a profit (value you get if you take it).

You also have a bag (knapsack) that can carry only a limited weight (capacity).
You can even take FRACTIONS of an item (not just whole ones).

Goal: Fill the knapsack in such a way that total profit is maximum.

Greedy logic used:
   Always pick the item that gives the MOST PROFIT per unit weight first.
   (i.e., maximize value per weight ratio)
*/


import java.util.*;

// 🔹 Class to represent each item
class Item {
    int weight;     // weight of the item
    int profit;     // profit/value of the item
    double ratio;   // profit/weight ratio (used for greedy selection)

    // Constructor to initialize an item
    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        // compute profit/weight ratio (for greedy sorting)
        this.ratio = (double) profit / weight;
    }
}

public class ASS3_FractionalKnapsackGreedy {

    /*
     * Function: getMaxProfit()
     * ------------------------
     * Finds the maximum profit for given capacity using fractional knapsack.
     *
     * Step-by-step:
     * 1️⃣ Sort all items by their profit/weight ratio (descending order).
     * 2️⃣ Pick items in that order:
     *     → if item fits completely → take it
     *     → if not → take the possible fraction of it
     * 3️⃣ Stop when bag is full.
     * 4️⃣ Return total profit.
     */
    public static double getMaxProfit(Item[] items, int capacity) {

        // 1) Sort items in descending order of ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0.0; // total profit collected so far
        int currentWeight = 0;    // total weight used in knapsack

        // 2) Loop through sorted items
        for (Item item : items) {

            // ✅ Case 1: If item fully fits, take all of it
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalProfit += item.profit;
            } 
            // ⚠️ Case 2: If only part fits, take fractional part and stop
            else {
                int remaining = capacity - currentWeight; // remaining space in bag
                if (remaining > 0) {
                    totalProfit += item.ratio * remaining; // take fraction
                }
                break; // bag is now full
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        // Input: weight and profit for each item
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter weight and profit of item " + (i + 1) + ":");
            int weight = sc.nextInt();
            int profit = sc.nextInt();
            items[i] = new Item(weight, profit);
        }

        // Input: knapsack capacity
        System.out.print("\nEnter knapsack capacity: ");
        int capacity = sc.nextInt();

        // Function call to calculate max profit
        double maxProfit = getMaxProfit(items, capacity);

        // Output final answer
        System.out.println("\nMaximum Profit = " + maxProfit);

        sc.close();
    }
}

/*
-----------------------------------------------------
🔹 DRY RUN EXAMPLE (Explain this in viva)
-----------------------------------------------------
Items:
Item1 → weight=10, profit=60 → ratio=6
Item2 → weight=20, profit=100 → ratio=5
Item3 → weight=30, profit=120 → ratio=4
Capacity = 50

→ Sort by ratio → Item1 (6), Item2 (5), Item3 (4)
Step 1: Take item1 fully (10kg) → profit = 60
Step 2: Take item2 fully (20kg) → profit = 160
Step 3: Only 20kg space left (50 - 30 used), take 20/30 of item3
         → profit += (20 * 4) = 80
Final Profit = 240

✅ Answer: 240
-----------------------------------------------------
*/


/*
=====================================================
🔹 TIME AND SPACE COMPLEXITY
=====================================================
Time Complexity:
👉 O(n log n)
   → because sorting items by ratio takes O(n log n)
   → iterating items takes O(n)
   → total = O(n log n + n) ≈ O(n log n)

Space Complexity:
👉 O(1) auxiliary (ignoring input array)
   → Sorting in-place uses small constant space
   → Otherwise no extra major data structure used.
=====================================================
*/


/*
=====================================================
🔹 THEORY / KEY POINTS FOR VIVA
=====================================================
1️⃣ What is the Fractional Knapsack Problem?
   → A problem where you can take fractions of items to maximize profit 
     under a limited capacity constraint.

2️⃣ What algorithmic strategy is used?
   → Greedy Algorithm (choose locally best option first — here highest ratio)

3️⃣ What is the greedy choice property?
   → At every step, picking the item with the best profit/weight ratio 
     gives the globally optimal solution.

4️⃣ Can we use this same method for 0/1 Knapsack?
   ❌ No. Because in 0/1 knapsack, you can’t take fractions.
      Greedy method fails there — need Dynamic Programming.

5️⃣ Why sort items by profit/weight ratio?
   → Because ratio shows which item gives the most value per unit weight.

6️⃣ What happens if two items have same ratio?
   → Any order works; both are equally profitable per weight.

7️⃣ How does this algorithm end?
   → When the knapsack reaches its full capacity.

8️⃣ Real-life examples:
   → Loading trucks, cargo selection, resource allocation, investment optimization.

9️⃣ Difference between 0/1 Knapsack and Fractional Knapsack:
   | Feature             | 0/1 Knapsack         | Fractional Knapsack     |
   |----------------------|----------------------|--------------------------|
   | Item Splitting       | Not allowed          | Allowed                 |
   | Approach Used        | Dynamic Programming  | Greedy Algorithm         |
   | Time Complexity      | O(nW)                | O(n log n)               |
   | Optimal Solution     | Greedy fails         | Greedy gives optimal     |

10️⃣ Formula used for fractional part:
     profit += ratio * remainingWeight
=====================================================
*/

/*
💡 ONE-LINER SUMMARY FOR VIVA:
"Fractional Knapsack uses a greedy method where we pick items based on
highest profit/weight ratio first. It’s solved in O(n log n) time by sorting,
and allows taking fractions of items to maximize profit."
*/
