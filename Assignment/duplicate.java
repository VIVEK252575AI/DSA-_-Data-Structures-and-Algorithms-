package Assignment;

public class duplicate {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 1};   // try other examples too
        boolean foundDuplicate = false;

        for (int i = 0; i < arr.length; i++) {
            int count = arr[i];                 // value to check
            for (int j = 0; j < arr.length; j++) {
                if (i != j && count == arr[j]) { // skip self-comparison
                    foundDuplicate = true;
                    break;                      // stop inner loop when duplicate found
                }
            }
            if (foundDuplicate) break;           // stop outer loop too
        }

        // print single result
        if (foundDuplicate) System.out.println("true");
        else System.out.println("false");
    }
}
