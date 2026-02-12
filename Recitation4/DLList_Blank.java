/**
 A linked implemention of the ADT list.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
 
/*	CS 0445 Spring 2026 Recitation Exercise 4
	Implementation of DLList<T> class. This class implements the
	same ListInterface<T> as the author's LList<T>, but now using a
	circular, doubly-linked list. Note the comments below with my changes.
	
	In the exercise you must implement the remove() and contains() methods.
	Their headers are below but the bodies are empty. For help with these
	methods see:
	LList.java (their implementation with null-terminated, singly-linked list)
	The methods below that I have provided for you.
	
	When complete, this class should run with the main program
	CS445Rec4.java
	and it should produce the same input shown in 
	Rec4Out.txt
*/

public class DLList_Blank<T> implements ListInterface<T>
{
   private Node firstNode;            // Reference to first node of chain
   private int numberOfEntries;
   
   // CS 0445
   // This method removes the value at givenPosition from the list and must
   // be updated in DLList<T>.
   // For detailed specifications see ListInterface.java
  	public T remove(int givenPosition)
	{
   		
	} // end remove
	
   // CS 0445 
   // The original version of contains() looks for null at the end
   // of the list to stop the search. You must re-implement it here
   // to work with a circular list (that is not null terminated)              
   public boolean contains(T anEntry)
   {
      
   } // end contains
     
   
   // The rest of this class is implemented for you. Read the comments for
   // some help.

   public DLList_Blank()
   {
      initializeDataFields();
   } // end default constructor
   
   public void clear()
   {
      initializeDataFields();
   } // end clear
   
   // CS 0445
   // This method adds a node to the end of the list
   public void add(T newEntry)
   {
      Node newNode = new Node(newEntry);
      
      // Special case for empty list -- node points to itself
	  // in both directions.
      if (isEmpty())
      {
         firstNode = newNode;
      	 firstNode.setPrevNode(firstNode);
      	 firstNode.setNextNode(firstNode);
      }
      else      // Add to end of nonempty list. Note that
      			// because the list is circular and doubly-
      			// linked, we can get to the end Node easily
      			// (without having to traverse).
      {
      	 Node lastNode = firstNode.getPrevNode();
      	 lastNode.setNextNode(newNode); 
      	 newNode.setPrevNode(lastNode);
      	 newNode.setNextNode(firstNode);
      	 firstNode.setPrevNode(newNode);
      } // end if
      
      numberOfEntries++;
   } // end add

   // CS 0445
   // This method adds a node to the list at an arbitrary index and also
   // must be updated in the DLList. Note how nodes are connected to both
   // the next and previous nodes in the list. Read over this code carefully.
   public void add(int givenPosition, T newEntry) // OutOfMemoryError possible
   {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
      {
         Node newNode = new Node(newEntry);
         // Same case for isEmpty() as above - have single Node point
         // to itself in both directions.
         if (isEmpty())
      	 {
         	firstNode = newNode;
      	 	firstNode.setPrevNode(firstNode);
      	 	firstNode.setNextNode(firstNode);
      	 }
         else if (givenPosition == 1) // Adding at the front of the
         {							  // list. This is also a special
         		// case because the firstNode reference must be updated
         		// and the "previous" Node is actually the last Node in
         		// the list.
         	Node lastNode = firstNode.getPrevNode();
            newNode.setNextNode(firstNode);
            firstNode.setPrevNode(newNode);
            newNode.setPrevNode(lastNode);
            lastNode.setNextNode(newNode);
            firstNode = newNode;
         }
         else // Adding in the middle of the list. Note that this will also
         {    // work if adding at the end of the list since the list is
        	  // circular.
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeAfter.setPrevNode(newNode);
            newNode.setPrevNode(nodeBefore);
            nodeBefore.setNextNode(newNode);
         } // end if
         numberOfEntries++;
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
   } // end add


  
   // CS 0445. This method is unchanged in circular-doubly linked
   // list since it involves only a simple traversal.
   public T replace(int givenPosition, T newEntry)
   {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: !isEmpty()
         Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
         desiredNode.setData(newEntry);
         return originalEntry;
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } // end replace

   // CS 0445. This method is unchanged in circular-doubly linked
   // list since it involves only a simple traversal.
   public T getEntry(int givenPosition)
   {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: !isEmpty()
         return getNodeAt(givenPosition).getData();
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } // end getEntry

   // CS 0445 
   // This method simply counts through the nodes and should be the
   // same in a null-terminated or circular list
   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while (index < numberOfEntries)
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
                            
   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
      boolean result;
      
      if (numberOfEntries == 0) // Or getLength() == 0
      {
         // Assertion: firstNode == null
         result = true;
      }
      else
      {
         // Assertion: firstNode != null
         result = false;
      } // end if
      
      return result;
   } // end isEmpty
	
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
      firstNode = null;
      numberOfEntries = 0;
   } // end initializeDataFields
   
   // CS 0445
   // Note: This method does not have to be changed in DLList because
   // it is counting through the nodes in the list.
   // Returns a reference to the node at a given position.
   // Precondition: The chain is not empty;
   //               1 <= givenPosition <= numberOfEntries.
   private Node getNodeAt(int givenPosition)
   {
      // Assertion: (firstNode != null) &&
      //            (1 <= givenPosition) && (givenPosition <= numberOfEntries)
      Node currentNode = firstNode;
      
      // Traverse the chain to locate the desired node
      // (skipped if givenPosition is 1)
      for (int counter = 1; counter < givenPosition; counter++)
         currentNode = currentNode.getNextNode();
      // Assertion: currentNode != null
      return currentNode;
   } // end getNodeAt
   
   // CS 0445
   // Node class for DLList<T>. Note tha addition of the prev
   // reference and updates to the accessor and mutator methods
   // to take the prev reference into account.
   private class Node
   {
      private T    data; // Entry in list
      private Node next; // Link to next node
      private Node prev; // Link to prev node
      
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
         prev = null;
      } // end constructor
      
      private Node(T dataPortion, Node nextNode, Node prevNode)
      {
         data = dataPortion;
         next = nextNode;
         prev = prevNode;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
      
      private Node getPrevNode()
      {
      	 return prev;
      }
      
      private void setPrevNode(Node prevNode)
      {
      	 prev = prevNode;
      }
   } // end Node
} // end LList



