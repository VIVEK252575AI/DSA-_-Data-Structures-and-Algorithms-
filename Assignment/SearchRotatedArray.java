package Assignment;
import java.util.Arrays;

public class SearchRotatedArray {
    
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            // Found target
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check if left half is sorted
            if (nums[start] <= nums[mid]) {
                // Target in left sorted half?
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } 
            // Right half is sorted
            else {
                // Target in right sorted half?
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        
        return -1; // Not found
    }
    
    public static void main(String[] args) {
        SearchRotatedArray solution = new SearchRotatedArray();
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int result1 = solution.search(nums1, target1);
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Target: " + target1 + " | Found at index: " + result1);
        System.out.println();
    } 
}
  // Linear search: iterate through the array to find the target

 
