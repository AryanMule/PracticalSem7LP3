
import java.util.*;

public class ASS1_Fibonacci {

    static int stepCount = 0; // global counter

    // Recursive Fibonacci function
    static int fibonacciRecursive(int n) {
        stepCount++; // count every call Each time the function is called, we increase the step count. Total recursive calls made (step count)
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative Fibonacci function (efficient)
    static int fibonacciIterative(int n) {
        int a = 0, b = 1, c = 0;
        stepCount = 0; // reset steps Total steps (number of loop iterations)
        if (n == 0) {
            return a;
        }
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
            stepCount++; // one main operation per loop
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        // Recursive approach
        stepCount = 0;
        int resultRecursive = fibonacciRecursive(n);
        System.out.println("Recursive Fibonacci(" + n + ") = " + resultRecursive);
        System.out.println("Step Count (Recursive): " + stepCount);

        // Iterative approach
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
