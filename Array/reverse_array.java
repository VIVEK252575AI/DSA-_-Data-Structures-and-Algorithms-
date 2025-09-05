/* Approach (How We Think Step-by-Step)
Goal: Reverse the order of elements in an array (e.g., {2, 4, 6, 8, 10} → {10, 8, 6, 4, 2}).

Key Idea:
Swap elements from the beginning and end of the array moving inward until they meet in the middle.

Steps to Implement:

Step 1: Create two pointers:
first = 0 (beginning), last = numbers.length - 1 (end)

Step 2: Run a while loop while first < last

Swap numbers[first] and numbers[last]

Increment first++ and decrement last--

Step 3: Exit the loop when both pointers meet or cross

Step 4: Print the array to verify it’s reversed

Time Complexity:
O(n/2) swaps → overall O(n) time
In-place (no extra space)
 */
package Array;

public class reverse_array {

    // Function to reverse an array
    public static void reverse(int numbers[]) {
        int first = 0, last = numbers.length - 1;

        while (first < last) {
            // Swap elements at first and last
            int temp = numbers[last];
            numbers[last] = numbers[first];
            numbers[first] = temp;

            // Move pointers
            first++;
            last--;
        }
    }

    public static void main(String args[]) {
        // Original array
        int numbers[] = {2, 4, 6, 8, 10};

        // Reverse the array
        reverse(numbers);

        // Print reversed array
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }


}
// this is the best time to start coding 