// ✅ Binary Search Program in Java with Dry Run
// ----------------------------------------------
// ➤ Approach:
// 1. Given a sorted array and a key to search.
// 2. Apply binary search:
//    - Set start = 0 and end = length-1.
//    - Calculate mid = (start + end) / 2.
//    - If key == numbers[mid] → return mid.
//    - If key > numbers[mid] → search right half (start = mid + 1).
//    - If key < numbers[mid] → search left half (end = mid - 1).
// 3. If not found, return -1.

package Array;
import java.util.*;

public class binary_search {

    // Binary Search Method
    public static int binarySearch(int numbers[], int key) {
        int start = 0, end = numbers.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;


            if (numbers[mid] == key) {
                return mid; // key found
            }

            if (numbers[mid] < key) {
                start = mid + 1; // move to right half
            } else {
                end = mid - 1; // move to left half
            }
        }

        return -1; // key not found
    }

    public static void main(String args[]) {
        int numbers[] = {2, 4, 6, 8, 10, 12, 14}; // must be sorted
        int key = 10;

        int index = binarySearch(numbers, key);

        if (index == -1) {
            System.out.println("Key not found.");
        } else {
            System.out.println("Index for key is: " + index);
        }
    }
}
