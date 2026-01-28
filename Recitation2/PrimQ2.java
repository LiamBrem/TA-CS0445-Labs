// CS 0445 Spring 2026
// Primitive Generic Queue that adds at the rear of an array
// and removes from index 0 of the array.  In other words, index
// 0 will always represent the front item of the queue. Some of this 
// is already completed for you.

// Complete this class so that it works correctly with the program
// CS445Rec2.java

public class PrimQ2<T> implements QueueInterface<T>, Moves
{
	private T [] theQueue;	// the queue array
	private int size;		// logical size (number of items in the queue)
	private int moves;		// moves variable (for bookkeeping)

	// Constructor method.  Note how the array is created here.
	public PrimQ2(int initsize)
	{
		@SuppressWarnings("unchecked")	
		T [] temp = (T []) new Object[initsize];
		theQueue = temp;
		size = 0;
	}

	// See the interface files QueueInterface.java and Moves.java
	// for the requirements for the methods below.
	public void enqueue(T element)
	{
		theQueue[size] = element;
		size++;
		moves++;
	}
	
	public T dequeue()
	{
		if (isEmpty()) throw new EmptyQueueException();
		T element = theQueue[0];
		for (int i = 0; i < size - 1; i ++){
			theQueue[i] = theQueue[i + 1];
			moves++;
		}
		theQueue[size - 1] = null;
		size--;
		return element;
	}
	
	public T getFront()
	{
		if (isEmpty()) throw new EmptyQueueException();

		return theQueue[0];
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public void clear()
	{
		for (int i = 0; i < size; i++) {
		    theQueue[i] = null;
		}
		size = 0;
		moves = 0;	
	}
	
	// I have implemented these already -- fairly simple methods for
	// the Moves interface.	 However, be sure to correctly update the
	// moves variable in your enqueue and dequeue methods.
	public int getMoves()
	{
		return moves;
	}
	
	public void setMoves(int val)
	{
		moves = val;
	}
	
}
