// CS 0445 Spring 2026
// Recitation Exercise 7
// Complete the runTest() method so that this program will run
// correctly. See details in the Recitation 7 document. Also see
// more comments below.

import java.util.*;

public class Rec7 {
	private static final int range = 100000000;
	private int[] data;
	private double ave;
	private int reps, num, found;
	private int item;
	private long seed, total;
	private Random R;

	public Rec7() {
		Scanner S = new Scanner(System.in);
		System.out.println("Please enter size of array: ");
		num = S.nextInt();
		S.nextLine();
		System.out.println("How many searches to try?: ");
		reps = S.nextInt();
		S.nextLine();
		System.out.println("Enter 1 for iterative or 2 for recursive: ");
		int algo = S.nextInt();
		S.nextLine();
		System.out.println("Please enter a seed: ");
		seed = S.nextLong();
		S.nextLine();
		R = new Random();
		R.setSeed(seed); // Using the same seed in multiple runs will allow
							// the same random data to be used for each run.
		loadData(num);
		runTest(algo);
	}

	public void runTest(int algo) // algo: 1 for iterative, 2 for recursive
	{
		long start = System.nanoTime(); // start the timer

		found = 0;

		for (int i = 0; i < reps; i++) { // search for reps random values
			item = R.nextInt(range);

			int result;

			if (algo == 1) {
				result = bSearchi(data, item); // iterative binary search
			} else {
				result = bSearchr(data, item, 0, data.length - 1); // recursive binary search
			}

			if (result != -1) {
				found++;
			}
		}

		long stop = System.nanoTime();

		long delta = stop - start; // total time elapsed

		ave = (double) delta / reps; // average time per search

		if (algo == 1) {
			System.out.println("Iterative Binary Search: ");

		} else {
			System.out.println("Recursive Binary Search: ");
		}

		System.out.println("\tTotal time: " + delta);
		System.out.println("\tAverage time: " + ave);
		System.out.println("\tFound: " + found + " out of " + reps + "\n");
	}

	public static void main(String[] args) {
		Rec7 prog = new Rec7();
	}

	public void loadData(int num) {
		data = new int[num];
		for (int i = 0; i < num; i++) {
			int item = R.nextInt(range);
			data[i] = item;
		}
		Arrays.sort(data); // Sort the data
		System.out.println("Data loaded");
	}

	// Iterative BS on an array of int
	public int bSearchi(int[] a, int X) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			int midItem = a[mid];
			if (midItem < X)
				low = mid + 1;
			else if (midItem > X)
				high = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	// Recursive BS on an array of int
	public int bSearchr(int[] a, int X, int low, int high) {
		int ans;
		if (low <= high) // if (low > high) the BASE CASE is reached
		{
			int mid = (low + high) / 2;
			int midItem = a[mid];

			if (midItem < X)
				return (bSearchr(a, X, mid + 1, high));
			else if (midItem > X)
				return (bSearchr(a, X, low, mid - 1));
			else
				return mid;
		}
		return -1;
	}
}