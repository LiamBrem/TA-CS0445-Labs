import java.util.Scanner;
import java.util.Random;

public class Main{
	  public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		Random rand = new Random();

		System.out.println("Enter array size: ");
		int size = scanner.nextInt();

		Integer[] arr1 = new Integer[size];
		Integer[] arr2 = new Integer[size];

		for (int i = 0; i < size; i++) {
		    int value = rand.nextInt(1000);
		    arr1[i] = value;
		    arr2[i] = value;
		}

		TextMerge sorter = new TextMerge();


		long start = System.nanoTime(); // start the timer
						
		sorter.mergeSort(arr1, size);

		long stop = System.nanoTime();
		long delta = stop - start; // total time elapsed
		System.out.println("Merge Sort A: " + delta);
		

		start = System.nanoTime(); // start the timer
						
		sorter.mergeSort(arr2, size);

		stop = System.nanoTime();
		delta = stop - start; // total time elapsed
		System.out.println("Merge Sort B: " + delta);
	  }
}
