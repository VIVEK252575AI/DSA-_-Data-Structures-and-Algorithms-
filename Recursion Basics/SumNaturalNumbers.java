public class SumNaturalNumbers {
    public static void main(String[] args) {
        int n = 10;

        System.out.println("Sum of first " + n + " natural numbers:");
        System.out.println("Using recursion: " + sumRecursive(n));
        System.out.println("Using formula: " + sumFormula(n));
        System.out.println("Using iteration: " + sumIterative(n));
    }

    // Recursive approach
    public static int sumRecursive(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + sumRecursive(n - 1);
    }

    // Using formula: n(n+1)/2
    public static int sumFormula(int n) {
        return n * (n + 1) / 2;
    }

    // Iterative approach
    public static int sumIterative(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}