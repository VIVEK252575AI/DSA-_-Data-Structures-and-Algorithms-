public class StackOverflowDemo {
    public static void main(String[] args) {
        try {
            recursiveFunction();
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow occurred!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void recursiveFunction() {
        // This will cause stack overflow as it calls itself infinitely
        recursiveFunction();
    }
}