public class IncreasingOrder {
    public static void main(String[] args) {
        int n = 10; // Print numbers from 1 to n

        System.out.println("Numbers in increasing order:");
        printIncreasing(n);
    }

    public static void printIncreasing(int n) {
        if (n > 0) {
            printIncreasing(n - 1);
            System.out.print(n + " ");
        }
    }
}