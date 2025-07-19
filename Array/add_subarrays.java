package Array; 
import java.util.*;

public class add_subarrays {

    public static void sub(int numbers[]) {
        int max = Integer.MIN_VALUE;  // Now it's declared inside static method

        for (int i = 0; i < numbers.length; i++) {
            int start = i;
            for (int j = i; j < numbers.length; j++) {
                int end = j;
                int current = 0; // Reset current for each subarray

                for (int k = start; k <= end; k++) {
                    current += numbers[k];  // Add elements to sum
                }

                if (max < current) {
                    max = current;  // No redeclaration, just update
                }
            }
        }

        System.out.println("Maximum subarray sum is: " + max);
    }

    public static void main(String[] args) {
        int numbers[] = {2, 4, 6, 8, 10};
        sub(numbers);
    }
}
