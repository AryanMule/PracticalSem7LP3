
import java.util.*;

// ASS1_Fibonacci
public class ASS1_Fibonacci {

    // Global counter used to count "steps" for whichever method we run.
    // For recursive: it counts how many times the recursive function is called.
    // For iterative: it counts how many loop iterations (main operations) were executed.
    static int stepCount = 0; // global counter

    // -----------------------
    // Recursive Fibonacci
    // -----------------------
    // This version directly follows the mathematical definition:
    // fibonacci(n) = fibonacci(n-1) + fibonacci(n-2) for n > 1,
    // with base cases fibonacci(0)=0, fibonacci(1)=1.
    static int fibonacciRecursive(int n) {
        // Count every function call — this shows how expensive recursion is.
        stepCount++; // count every call. Each time the function is called, we increase the step count.
        if (n <= 1) { // base cases: 0 or 1
            return n;
        }
        // Two recursive calls for each non-base n -> causes exponential blow-up.
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // -----------------------
    // Iterative Fibonacci (efficient)
    // -----------------------
    // Uses a simple loop and only constant extra memory.
    static int fibonacciIterative(int n) {
        int a = 0, b = 1, c = 0; // a = F(0), b = F(1), c temporary
        stepCount = 0; // reset steps. For iterative, stepCount measures loop iterations (main ops)
        if (n == 0) { // handle n = 0 explicitly
            return a;
        }
        // Loop from 2 to n computing each Fibonacci by summing previous two.
        // For n = 1 the loop is skipped and b (which is 1) is returned.
        for (int i = 2; i <= n; i++) {
            c = a + b; // compute next Fibonacci number
            a = b;     // slide window: old b becomes new a
            b = c;     // new b is the newly computed Fibonacci
            stepCount++; // one main operation per loop iteration
        }
        return b; // b now holds F(n)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        // Recursive approach: run and show step count (number of recursive calls)
        stepCount = 0;
        int resultRecursive = fibonacciRecursive(n);
        System.out.println("Recursive Fibonacci(" + n + ") = " + resultRecursive);
        System.out.println("Step Count (Recursive): " + stepCount);

        // Iterative approach: run and show step count (number of loop iterations)
        int resultIterative = fibonacciIterative(n);
        System.out.println("Iterative Fibonacci(" + n + ") = " + resultIterative);
        System.out.println("Step Count (Iterative): " + stepCount);

    }
}


// RECURSIVE APPROACH 
// f(4)
// = f(3) + f(2)
// = (f(2) + f(1)) + (f(1) + f(0))
// = ((f(1)+f(0)) + 1) + (1 + 0)
// = ((1+0)+1)+(1+0) = 3
// But here’s the catch — it does a LOT of repeated work.
// For example, f(2) is calculated multiple times.
// That’s why the recursive step count grows exponentially (O(2^n)).
// ITERATIVE APPROACH
// Let’s trace it for n = 5:
// i	a	b	c = a+b	stepCount
// Start	0	1	-	0
// 2	0	1	1	1
// 3	1	1	2	2
// 4	1	2	3	3
// 5	2	3	5	4
// At the end, b = 5, which is Fibonacci(5).
// ✅ No repeated calculations.
// ✅ Runs in linear time (O(n))
// ✅ Much faster for large n.
// 💡 Key Takeaways
// Approach	Time Complexity	Steps (for n=5)	Space Used	Comments
// Recursive	O(2^n)	15	High (due to call stack)	Conceptually simple but inefficient
// Iterative	O(n)	4	Low	Fast and memory efficient
