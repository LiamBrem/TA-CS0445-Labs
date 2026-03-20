import java.util.Scanner;
import java.util.Random;

public class MainComplete {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter array size: ");

        int n = input.nextInt();

        if (n <= 0) {
            System.out.println("Array size must be greater than 0.");
            input.close();
            return;
        }

        Integer[] selectionArray = new Integer[n];
        Integer[] insertionArray = new Integer[n];

        for (int i = 0; i < n; i++) {
            int value = rand.nextInt(1000);
            selectionArray[i] = value;
            insertionArray[i] = value;
        }

        // 1) Sort random arrays
	SimpleSorts.selectionSort(selectionArray, n);
	long selectionRandomComps = SimpleSorts.comps;
	SimpleSorts.comps = 0;
	SimpleSorts.insertionSort(insertionArray, n);
	long insertionRandomComps = SimpleSorts.comps;
	SimpleSorts.comps = 0;

        // 2) Sort already sorted arrays
	SimpleSorts.selectionSort(selectionArray, n);
	long selectionSortedComps = SimpleSorts.comps;
	SimpleSorts.comps = 0;
	SimpleSorts.insertionSort(insertionArray, n);
	long insertionSortedComps = SimpleSorts.comps;
	SimpleSorts.comps = 0;

        // 3) Reverse arrays, then sort reverse-sorted arrays
	reverse(selectionArray);
	reverse(insertionArray);

	SimpleSorts.selectionSort(selectionArray, n);
	long selectionReversedComps = SimpleSorts.comps;
	SimpleSorts.comps = 0;
	SimpleSorts.insertionSort(insertionArray, n);
	long insertionReversedComps = SimpleSorts.comps;
	SimpleSorts.comps = 0;

        // 4) Print results
	System.out.println("---RESULTS---");
	System.out.println("Selection Sort Random:");
	System.out.println(selectionRandomComps);
	System.out.println("Insertion Sort Random:");
	System.out.println(insertionRandomComps);
	System.out.println("Selection Sort Already Sorted:");
	System.out.println(selectionSortedComps);
	System.out.println("Insertion Sort Already Sorted:");
	System.out.println(insertionSortedComps);
	System.out.println("Selection Sort Reversed:");
	System.out.println(selectionReversedComps);
	System.out.println("Insertion Sort Reversed:");
	System.out.println(insertionReversedComps);

        input.close();
    }

    private static <T> void reverse(T[] arr) {
        // helper method to reverse array
	int start = 0;
	int end = arr.length - 1;
	while (start < end) {
		T temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;

        start++;
        end--;
	}
    }
}
