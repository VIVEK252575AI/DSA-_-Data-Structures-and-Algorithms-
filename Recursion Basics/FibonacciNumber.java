public class FibonacciNumber {
    public static void main(String[] args) {
        int n = 10;

        System.out.println("The " + n + "th Fibonacci number:");
        System.out.println("Using recursion: " + fibonacciRecursive(n));
        System.out.println("Using iteration: " + fibonacciIterative(n));
        System.out.println("Using memoization: " + fibonacciMemo(n, new int[n + 1]));

        System.out.println("\nFirst " + n + " Fibonacci numbers:");
        printFibonacciSeries(n);
    }

    // Recursive approach (inefficient for large n)
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative approach (efficient)
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int temp = curr;
            curr = prev + curr;
            prev = temp;
        }
        return curr;
    }

    // Memoization approach (efficient recursive)
    public static int fibonacciMemo(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        return memo[n];
    }

    // Print Fibonacci series
    public static void printFibonacciSeries(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciIterative(i) + " ");
        }
        System.out.println();
    }
}