import java.util.Scanner;
import java.util.Random;

public class Main {
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

        // 2) Sort already sorted arrays

        // 3) Reverse arrays, then sort reverse-sorted arrays

        // 4) Print results

        input.close();
    }

    private static <T> void reverse(T[] arr) {
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
