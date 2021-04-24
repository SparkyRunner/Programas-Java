// Java program to demonstrate impact  
// of Parallel Sort vs Serial Sort  
  
import java.util.Arrays;  
import java.util.Random;  
  
public class sort_methods {  
    public static void main(String[] args)  
    {  
        // Creating an array 
        int n = 10000; 
        int numbers[] = new int[n];  
        Random rand = new Random();  
  
        
        for (int j = 0; j < n; j++) {  
            numbers[j] = rand.nextInt();  
        }  
        // Start and End Time of Arrays.sort()  
        long startTime = System.currentTimeMillis();  
  
        // Performing Serial Sort  
        Arrays.sort(numbers);  

        long endTime = System.currentTimeMillis();  

        // Printing result of Serial Sort  
        System.out.printf("\n%.3f ms%n", (endTime -startTime) / 1000d);

        // Start and End Time of Arrays.parallelSort()  
        startTime = System.currentTimeMillis();

        // Performing Parallel Sort  
        Arrays.parallelSort(numbers);  

        endTime = System.currentTimeMillis();

        // Printing result of Parallel Sort  
        System.out.printf("\n%.3f ms%n", (endTime -startTime) / 1000d);
        System.out.println();  
    }  
}  
