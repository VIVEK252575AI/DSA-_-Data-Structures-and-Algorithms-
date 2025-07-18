
// ✅ Linear Search Program in Java
// ------------------------------------
// ➤ Approach:
// 1. We define an integer array `numbers[]` with some values.
// 2. We take a key value that we want to search in the array.
// 3. We implement a method `linearSearch`:
//    - Traverse the array from start to end.
//    - Compare each element with the key.
//    - If found, return the index.
//    - If not found after the loop, return -1.
// 4. In the main method, we call the `linearSearch` function.
// 5. Based on the return value, we print the result.

package Array;
import java.util.*;

public class linear_search {


    // Method to perform Linear Search
    public static int linearSearch(int numbers[], int key) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                return i; // return the index if key is found
            }
        }
        return -1; // return -1 if key not found
    }

    public static void main(String args[]) {
        // Array to search in
        int numbers[] = {2, 4, 6, 8, 10, 12, 14, 16};

        // Key to search
        int key = 10;

        // Call the linear search method
        int index = linearSearch(numbers, key);

        // Print result based on index
        if (index == -1) {
            System.out.println("NOT found");
        } else {
            System.out.println("Key is at index: " + index);
        }
    }
}
