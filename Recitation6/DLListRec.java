/**
 * A linked implemention of the ADT list.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 */

/*
 * CS 0445 Spring 2026 Recitation Exercise 4
 * Implementation of DLList<T> class. This class implements the
 * same ListInterface<T> as the author's LList<T>, but now using a
 * circular, doubly-linked list. Note the comments below with my changes.
 */

public class DLListRec<T> implements ListInterface<T> {
   private Node firstNode; // Reference to first node of chain
   private int numberOfEntries;

   public DLListRec() {
      initializeDataFields();
   } // end default constructor

   public void clear() {
      initializeDataFields();
   } // end clear

   // CS 0445
   // This method adds a node to the list and must be updated in DLList<T>
   public void add(T newEntry) {
      Node newNode = new Node(newEntry);

      // Special case for empty list -- node points to itself
      // in both directions.
      if (isEmpty()) {
         firstNode = newNode;
         firstNode.setPrevNode(firstNode);
         firstNode.setNextNode(firstNode);
      } else // Add to end of nonempty list. Note that
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
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
         Node newNode = new Node(newEntry);
         // Same case for isEmpty() as above - have single Node point
         // to itself in both directions.
         if (isEmpty()) {
            firstNode = newNode;
            firstNode.setPrevNode(firstNode);
            firstNode.setNextNode(firstNode);
         } else if (givenPosition == 1) // Adding at the front of the
         { // list. This is also a special
            // case because the firstNode reference must be updated
            // and the "previous" Node is actually the last Node in
            // the list.
            Node lastNode = firstNode.getPrevNode();
            newNode.setNextNode(firstNode);
            firstNode.setPrevNode(newNode);
            newNode.setPrevNode(lastNode);
            lastNode.setNextNode(newNode);
            firstNode = newNode;
         } else // Adding in the middle of the list. Note that this will also
         { // work if adding at the end of the list since the list is
           // circular.
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeAfter.setPrevNode(newNode);
            newNode.setPrevNode(nodeBefore);
            nodeBefore.setNextNode(newNode);
         } // end if
         numberOfEntries++;
      } else
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
   } // end add

   // CS 0445
   // This method removes a node from the list and must be updated in DLList<T>.
   public T remove(int givenPosition) {
      T result = null; // return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
         if (numberOfEntries == 1) // case 1: remove only entry -- this is
         { // special because of the circular list.
            result = firstNode.getData();
            firstNode = null;
         } else if (givenPosition == 1) // case 2: remove first entry
         {
            result = firstNode.getData(); // save entry to be removed
            Node lastNode = firstNode.getPrevNode();
            firstNode = firstNode.getNextNode(); // move firstNode down
            lastNode.setNextNode(firstNode); // reset neighbors
            firstNode.setPrevNode(lastNode);
         } else // case 3: not first entry
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
            nodeAfter.setPrevNode(nodeBefore);
            result = nodeToRemove.getData(); // save entry to be removed
         } // end if

         numberOfEntries--;
      } // end if

      return result; // return removed entry, or
      // null if operation fails
   } // end remove

   // CS 0445. This method is unchanged in circular-doubly linked
   // list since it involves only a simple traversal.
   public T replace(int givenPosition, T newEntry) {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
         // Assertion: !isEmpty()
         Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
         desiredNode.setData(newEntry);
         return originalEntry;
      } else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } // end replace

   // CS 0445. This method is unchanged in circular-doubly linked
   // list since it involves only a simple traversal.
   public T getEntry(int givenPosition) {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
         // Assertion: !isEmpty()
         return getNodeAt(givenPosition).getData();
      } else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } // end getEntry

   // CS 0445
   // This method simply counts through the nodes and should be the
   // same in a null-terminated or circular list
   public T[] toArray() {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[]) new Object[numberOfEntries];

      toArrayRec(firstNode, 0, result);

      return result;
   } // end toArray

   public void toArrayRec(Node curr, int pos, T[] arr){
      if (pos >= numberOfEntries){
         return;
      }

      arr[pos] = curr.getData();
      toArrayRec(curr.getNextNode(), pos + 1, arr);
   }

   // CS 0445
   // This method uses null to detect the end of the list and
   // must be changed in DLList<T>.
   public boolean contains(T anEntry) {
      if (firstNode.getData().equals(anEntry)){
         return true;
      }
      return containsRec(firstNode.getNextNode(), anEntry, firstNode);

   } // end contains

   private boolean containsRec(Node curr, T target, Node start) {
      if (curr == start) {
         return false;
      }

      return (curr.getData().equals(target) || containsRec(curr.getNextNode(), target, start));

   }

   public int getLength() {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty() {
      boolean result;

      if (numberOfEntries == 0) // Or getLength() == 0
      {
         // Assertion: firstNode == null
         result = true;
      } else {
         // Assertion: firstNode != null
         result = false;
      } // end if

      return result;
   } // end isEmpty

   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields() {
      firstNode = null;
      numberOfEntries = 0;
   } // end initializeDataFields

   // CS 0445
   // Note: This method does not have to be changed in DLList because
   // it is counting through the nodes in the list.
   // Returns a reference to the node at a given position.
   // Precondition: The chain is not empty;
   // 1 <= givenPosition <= numberOfEntries.
   private Node getNodeAt(int givenPosition) {
      // Assertion: (firstNode != null) &&
      // (1 <= givenPosition) && (givenPosition <= numberOfEntries)
      return getNodeAtRec(0, givenPosition, firstNode);
   } // end getNodeAt


   private Node getNodeAtRec(int currPos, int targetPos, Node curr){
      if (currPos == targetPos){
         return curr;
      }

      return getNodeAtRec(currPos + 1, targetPos, curr.getNextNode());

   }

   // CS 0445
   // Node class for DLList<T>. Note tha addition of the prev
   // reference and updates to the accessor and mutator methods
   // to take the prev reference into account.
   private class Node {
      private T data; // Entry in list
      private Node next; // Link to next node
      private Node prev; // Link to prev node

      private Node(T dataPortion) {
         data = dataPortion;
         next = null;
         prev = null;
      } // end constructor

      private Node(T dataPortion, Node nextNode, Node prevNode) {
         data = dataPortion;
         next = nextNode;
         prev = prevNode;
      } // end constructor

      private T getData() {
         return data;
      } // end getData

      private void setData(T newData) {
         data = newData;
      } // end setData

      private Node getNextNode() {
         return next;
      } // end getNextNode

      private void setNextNode(Node nextNode) {
         next = nextNode;
      } // end setNextNode

      private Node getPrevNode() {
         return prev;
      }

      private void setPrevNode(Node prevNode) {
         prev = prevNode;
      }
   } // end Node
} // end LList
