package Array;

import java.util.Scanner;

public class update_marks {

    // Method to update marks
    public static int[] update_marks(int marks[]) {
        for (int i = 0; i < marks.length; i++) {
            marks[i] = marks[i] + 1; // increment each mark by 1
        }
        return marks;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int marks[] = new int[3];

        // Taking input
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter marks " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        // Update marks
        update_marks(marks);

        // Print updated marks
        System.out.println("Updated marks:");
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks[i]);
        }
    }
}
