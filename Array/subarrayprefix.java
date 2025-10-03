package Array;

public class subarrayprefix {

    public static void sub(int numbers[]) {
        // Step 1: Create prefix sum array
        int[] prefix = new int[numbers.length];
        prefix[0] = numbers[0];

        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + numbers[i];
        }

        // Step 2: Find max subarray sum using prefix array
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < numbers.length; i++) {
            int start = i;
            for (int j = i; j < numbers.length; j++) {
                int end = j;

                int currSum;
                if (start == 0) {
                    currSum = prefix[end];
                } else {
                    currSum = prefix[end] - prefix[start - 1];
                }

                if (currSum > maxSum) {
                    maxSum = currSum;
                }
            }
        }

        System.out.println("Max sum = " + maxSum);
    }

    public static void main(String[] args) {
        int numbers[] = {2, 4, 6, 8, 10};
        sub(numbers);
    }
}
 