public class Factorial {
    public static void main(String[] args) {
        int n = 5;

        System.out.println("Factorial of " + n + " = " + factorial(n));
        System.out.println("Factorial using iteration: " + factorialIterative(n));
    }

    // Recursive approach
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // Iterative approach
    public static long factorialIterative(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}