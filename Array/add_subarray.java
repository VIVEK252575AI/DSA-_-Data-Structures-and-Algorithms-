package Array; 
import java.util.*;

public class add_subarray {
    public static void sub(int numbers[]) {
        for (int i = 0; i < numbers.length; i++) {
            int start = i;
            for (int j = i; j < numbers.length; j++) {
                int end = j;
                int sum = 0;  // Reset sum for each subarray

                for (int k = start; k <= end; k++) {
                    sum += numbers[k];  // Add elements to sum
                    System.out.print(numbers[k] + " ");
                }

                System.out.print("= " + sum + " | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numbers[] = {2, 4, 6, 8, 10};
        sub(numbers);
    }
}
