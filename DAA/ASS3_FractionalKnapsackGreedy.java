import java.util.*;

class Item {
    int weight;
    int profit;
    double ratio; // profit/weight ratio

    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
}

public class ASS3_FractionalKnapsackGreedy {

    public static double getMaxProfit(Item[] items, int capacity) {
        // 1️⃣ Sort items in descending order of profit/weight ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0.0;
        int currentWeight = 0;

        // 2️⃣ Iterate through sorted items
        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // take the whole item
                currentWeight += item.weight;
                totalProfit += item.profit;
            } else {
                // take fractional part of item
                int remaining = capacity - currentWeight;
                totalProfit += item.ratio * remaining;
                break; // knapsack is full
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
