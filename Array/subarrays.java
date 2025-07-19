package Array;
import java.util.*;

public class subarrays {
    public static void sub(int numbers[]){
        for (int i=0 ; i<numbers.length; i++){
            int start = i ;
               for(int j = i ; j < numbers.length ; j++) {
                int end = j ; 
                // Fixed: increment k instead of j, and print numbers[k] instead of [k]
                for (int k = start; k  <=end ; k++ ){
                     System.out.print(numbers[k]+" ");
                } System.out.print(" | ");

            }System.out.println();
        }


    }
    
    
    public static void main(String[] args) {
        int numbers []= {2,4,6,8,10};
        sub(numbers);
    }
}
