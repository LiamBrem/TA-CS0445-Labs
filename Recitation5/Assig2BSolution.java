// CS 0445 Spring 2026

public class Assig2BSolution {
	static int[] vals = { 50000, 100000, 200000, 400000, 800000 };

	public Assig2BSolution() {
		for (int N : vals) {
			System.out.println("Testing N = " + N + ":");
			testAppend(N);
			testDeleteBack(N);
			System.out.println();
		}
	}

	public void testAppend(int N) {
		String s = "";
		long start = System.nanoTime();
		for (int i = 0; i < N; i++)
			s = s + "A";
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing append with +: ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " appends");
		System.out.println("\t\tTime per append: " + ave + " ns");
	}

	public void testDeleteBack(int N) {
		String s = "";
		for (int i = 0; i < N; i++)
			s = s + "A";
		long start = System.nanoTime();
		while (s.length() > 0)
			s = s.substring(0, s.length() - 1);
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing delete from back: ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " deletes");
		System.out.println("\t\tTime per delete: " + ave + " ns");
	}

	public static void main(String[] args) {
		Assig2BSolution E = new Assig2BSolution();
	}
}
