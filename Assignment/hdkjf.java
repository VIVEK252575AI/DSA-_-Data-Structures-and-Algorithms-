int[] nums = {/* your array here */};
int target = /* your target here */;
int start = 0;
int end = nums.length - 1;
int result = -1;

while (start <= end) {
    int mid = start + (end - start) / 2;

    if (nums[mid] == target) {
        result = mid;
        break;
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

// result contains the index or -1 if not found
