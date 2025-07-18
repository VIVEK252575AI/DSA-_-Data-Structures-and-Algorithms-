/* this is the program that's take marks and give percentage by using array 
first take input 

marks 1 
marks 2 
marks 3 

than call function to calculate precentage 
sum store the total marks and sum/3 gives = persentage  

 */

package Array;

import java.util.*;

public class percentage {

    // Method to calculate average percentage
    public static int calculatePercentage(int[] marks) {
        int sum = marks[0] + marks[1] + marks[2];
        int percentage = sum / 3;
        return percentage;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int marks[] = new int[3];

        // Input marks
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter mark " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        // Call method and print result
        int result = calculatePercentage(marks);
        System.out.println("Average Percentage: " + result + "%");
    }
}


// if any time i want to understand the inner working of this program
// i can use the following this 
/* 
   Dry Run (Example: {2, 4, 6, 8, 10})
Step	first	last	Swap	Resulting Array
1	0	4	2 ↔ 10	{10, 4, 6, 8, 2}
2	1	3	4 ↔ 8	{10, 8, 6, 4, 2}
3	2	2	(stop)	No swap needed, loop ends

🔚 Final Output: {10, 8, 6, 4, 2} 
*/