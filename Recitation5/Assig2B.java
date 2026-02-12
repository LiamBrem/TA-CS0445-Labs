// CS 0445 Spring 2026
// Recitation 5 timing test
// Times String concatenation and deletion using immutable String operations.

public class Assig2B
{
	static int [] vals = {50000, 100000, 200000, 400000, 800000};
	
	public Assig2B()
	{
		for (int N: vals)
		{	
			System.out.println("Testing N = " + N + ":");
			testAppend(N);
			testDeleteBack(N);
			System.out.println();
		}
	}
	
	// Appending with '+' should show quadratic growth because Strings are immutable.
	public void testAppend(int N)
	{
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
	
	// Deleting from the back via substring should also show quadratic growth.
	public void testDeleteBack(int N)
	{
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
	
	public static void main(String [] args)
	{
		Assig2B E = new Assig2B();
	}
}


