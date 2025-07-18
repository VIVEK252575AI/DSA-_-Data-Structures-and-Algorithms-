// ✅ Find the Largest Number in an Array
// --------------------------------------
// ➤ Approach:
// 1. We define an integer array called `numbers[]`.
// 2. We create a method `getLargest()` to find the largest element:
//    - Initialize `largest` with the smallest possible integer using `Integer.MIN_VALUE`.
//    - Traverse the array using a loop.
//    - For each element, check if it is greater than `largest`.
//    - If true, update `largest` to that value.
// 3. In the main method, call `getLargest(numbers)` and print the result.
package Array;
import java.util.*;


public class largest_in_array {

    // Method to find the largest number in an array
    public static int getLargest(int numbers[]) {
        int largest = Integer.MIN_VALUE; // Start with the smallest possible value

        // Loop through all elements in the array
        for (int i = 0; i < numbers.length; i++) {
            if (largest < numbers[i]) {
                largest = numbers[i]; // Update largest if current element is bigger
            }
        }

        return largest; // Return the largest number found
    }

    public static void main(String args[]) {
        // Define the input array
        int numbers[] = {1, 2, 6, 3, 5};

        // Call the method and print the largest number
        System.out.println("Largest number is: " + getLargest(numbers));
    }
}
