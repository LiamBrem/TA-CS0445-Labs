// CS 0445 Spring 2026
// primitive generic queue that adds at index 0 of the array
// and removes from the rear of the array.  In other words, index
// 0 will always represent the back item of the queue.  Some of this
// is already completed for you.  

// Complete this class so that it works correctly with the program
// CS445Rec2.java

public class PrimQ1<T> implements QueueInterface<T>, Moves
{
	private T [] theQueue;  // the queue array
	private int size;		// logical size (number of items in the queue)
	private int moves;		// moves variable (for bookkeeping)

	// Constructor method.  Note how the array is created here.
	public PrimQ1(int initsize)
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
		for (int i = size; i > 0; i--){
			theQueue[i] = theQueue[i - 1];
			moves++;
		}
		
		theQueue[0] = element;
		size++;
	}
	
	public T dequeue()
	{
		if (isEmpty()) throw new EmptyQueueException();
		T element = theQueue[size - 1];
		theQueue[size - 1] = null;
		size--;
		moves++;
		return element;
	}
	
	public T getFront()
	{
		if (isEmpty()) throw new EmptyQueueException();
    		return theQueue[size - 1];
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
		
		// import java.util.Arrays;
		// Arrays.fill(theQueue, null);

		size = 0;
		moves = 0;
	}	
	
	// I have implemented these already -- fairly simple methods for
	// the Moves interface.  However, be sure to correctly update the
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
