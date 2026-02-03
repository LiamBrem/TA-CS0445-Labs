// CS 0445 Spring 2026
// Test Program for Recitation 4 Exercise
// This program requires the following files:
// 		ListInterface.java
//		DLList.java (modified by you)
// After you get the program to compile and run, compare your output to that
// in file:
// Rec4Out.txt

public class CS445Rec4
{	
	public static void main(String [] args)
	{
		String [] A = {"Joey", "DeeDee", "Johnny", "Tommy"};
		String [] B = {"Debbie", "Chris", "Jimmy", "Clem", "Frank", "Nigel"};
		
		DLList<String> L1 = new DLList<String>();
		
		// Adding Strings at the end
		for (String S: A)
			L1.add(S);	
		showData(L1);
		
		String val;
		// Removing all of the Strings from the front
		while (!L1.isEmpty())
		{
			val = L1.remove(1);
			System.out.println("Removed: " + val.toString());
		}
		System.out.println("List has length: " + L1.getLength());
		
		// Adding Strings at the front
		for (String S: B)
			L1.add(1, S);
		showData(L1);
		
		// Test for some values in the list
		testMembership(L1, "Nigel");
		testMembership(L1, "Debbie");
		testMembership(L1, "Chrissie");
		
		// Remove a value and test again
		val = L1.remove(L1.getLength());
		System.out.println("Removed: " + val.toString());
		
		testMembership(L1, "Debbie");
		testMembership(L1, "Chris");
		
		// Remove all of the String again - this time from the back.
		showData(L1);
		while (!L1.isEmpty())
		{
			val = L1.remove(L1.getLength());
			System.out.println("Removed: " + val.toString());
		}
		System.out.println("List has length: " + L1.getLength());	
	}
	
	// Method to show all values in the list
	public static <T> void showData(ListInterface<T> list)
	{
		T [] data = list.toArray();
		for (T val:data)
		{
			System.out.print(val.toString() + " ");
		}
		System.out.println();
	}
	
	// Method to test membership in the list
	public static <T> void testMembership(ListInterface<T> list, T X)
	{
		if (list.contains(X))
			System.out.println(X + " is in the band!");
		else
			System.out.println(X + " is not in the band!");
	}
}

