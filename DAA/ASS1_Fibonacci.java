import java.util.*;

// 🧮 ASS1_Fibonacci — Recursive vs Iterative Comparison
public class ASS1_Fibonacci {

    // Global counter to count the total steps taken in each method
    // (For recursive: counts function calls, for iterative: loop iterations)
    static int stepCount = 0;

    // ---------------------------------------------------------
    // 🔁 Recursive Fibonacci
    // ---------------------------------------------------------
    // Follows the mathematical definition directly:
    // F(n) = F(n-1) + F(n-2), with base cases F(0)=0, F(1)=1
    static int fibonacciRecursive(int n) {
        stepCount++; // count each recursive call
        if (n <= 1) {
            return n; // base condition
        }
        // Recursive case — call itself twice
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // ---------------------------------------------------------
    // 🔂 Iterative Fibonacci (Efficient)
    // ---------------------------------------------------------
    // Uses loop and only a few variables, avoiding recursion.
    static int fibonacciIterative(int n) {
        int a = 0, b = 1, c = 0;
        stepCount = 0; // reset counter for iterative approach

        if (n == 0)
            return a; // directly return 0

        // Loop from 2 → n to compute next Fibonacci number
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
            stepCount++; // count each loop iteration
        }
        return b; // return the final Fibonacci number
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        // Recursive Result
        stepCount = 0;
        int resultRecursive = fibonacciRecursive(n);
        System.out.println("\nRecursive Fibonacci(" + n + ") = " + resultRecursive);
        System.out.println("Step Count (Recursive): " + stepCount);

        // Iterative Result
        int resultIterative = fibonacciIterative(n);
        System.out.println("\nIterative Fibonacci(" + n + ") = " + resultIterative);
        System.out.println("Step Count (Iterative): " + stepCount);
    }
}

/*
=====================================================================
📘 TRACE EXAMPLES
=====================================================================
🔹 Recursive (n=4)
F(4) = F(3) + F(2)
     = (F(2) + F(1)) + (F(1) + F(0))
     = ((F(1)+F(0)) + 1) + (1 + 0)
     = ((1+0)+1)+(1+0)
     = 3
→ Step Count grows fast because F(2) repeats many times.

🔹 Iterative (n=5)
i	a	b	c=a+b	stepCount
0	0	1	-	0
2	0	1	1	1
3	1	1	2	2
4	1	2	3	3
5	2	3	5	4
✅ Result: F(5) = 5
✅ Efficient and no repeated calculations
=====================================================================
*/

// =============================================================
// ⚙️ COMPLEXITY ANALYSIS
// =============================================================
// 🔁 Recursive:
//     → Time Complexity: O(2^n)  (due to repeated calls)
//     → Space Complexity: O(n)   (recursive call stack)
//
// 🔂 Iterative:
//     → Time Complexity: O(n)
//     → Space Complexity: O(1)
//
// ✅ Iterative method is much faster and memory-friendly.
// =============================================================


// =============================================================
// 💡 KEY DIFFERENCES (Summary Table)
// =============================================================
// Approach     | Time        | Space | Steps (n=5) | Efficiency | Comment
// ----------------------------------------------------------------------
// Recursive    | O(2^n)      | O(n)  | 15          | Slow       | Repeats same calls
// Iterative    | O(n)        | O(1)  | 4           | Fast       | Simple loop, no recursion
// =============================================================


// =============================================================
// 🎯 IMPORTANT THEORY POINTS (Exam/Interview)
// =============================================================
// 🔸 Fibonacci Series: 0, 1, 1, 2, 3, 5, 8, 13, ...
// 🔸 Each term = sum of previous two terms.
// 🔸 Recursion gives direct mathematical clarity, but is inefficient.
// 🔸 Iteration is preferred for real-world programming.
// 🔸 Recursive method causes “overlapping subproblems”.


// =============================================================
// 🎓 VIVA QUESTIONS (with Easy Answers)
// =============================================================

// Q1) What is Fibonacci Series?
// → It’s a series where each term is the sum of the previous two: 0, 1, 1, 2, 3, 5, 8...

// Q2) What is recursion?
// → When a function calls itself until a base condition is met.

// Q3) Why is recursion slower here?
// → Because it repeats the same calculations multiple times and uses more memory.

// Q4) What is the time complexity of recursive Fibonacci?
// → O(2^n), because each call branches into two more calls.

// Q5) What is the time complexity of iterative Fibonacci?
// → O(n), because it just runs a simple loop from 2 to n.

// Q6) Which method is better and why?
// → Iterative — it uses less memory and runs much faster.

// Q7) What is the space complexity of recursion?
// → O(n), since each call is stored in the call stack.

// Q8) What is meant by “step count” in this program?
// → It represents how many operations (calls/loops) are performed — helps compare both methods.

// Q9) Can recursion be optimized?
// → Yes, by using **Dynamic Programming** (Memoization) to store already calculated results.

// Q10) Which approach will you use in large input (like n=40)?
// → Iterative, because recursion becomes too slow and memory-heavy.
