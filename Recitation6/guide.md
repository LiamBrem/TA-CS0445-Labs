```java
public boolean contains(T anEntry)
   {
      // check if first node matches an entry
      // if not -> return containsRec(firstNode.getNextnode(), anEntry, firstNode)
   }

   private boolean containsRec(Node currNode, T target, Node startNode){
      // if currNode == startNode -> return False
      // return (currNode == target || containsRec(currNode.getNextNode(), target, startNode))
      // ^ will return true if the current == target OR if any of the other calls down the line return true
   }
```

```java
public T[] toArray()
   {
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      // we'll the "result" by reference here
      // This means we're modifying this result variable above ^ in the toArrayRec method below v
      // toArrayRec(result, firstNode, 0, numberOfEntries) 
      // What do we have to return?
   }

private void toArrayRec(T[] arr, Node currNode, int pos, int size){
    // base case: if position is out of bounds -> break out
    // current position at result = currNode value
    // toArrayRec(arr, currNode.getNextNode(), pos + 1, size)
}
```

```java
private Node getNodeAt(int givenPosition) {
      // Assertion: (firstNode != null) &&
      // (1 <= givenPosition) && (givenPosition <= numberOfEntries)
      // return getNodeAtRec(0, givenPosition, firstNode);
   }

private Node getNodeAtRec(int currPos, int targetPos, Node curr){
      // base case -> we get to the current node, what do we do now?
      // else -> move to the next node/position by calling getNodeAtRec
   }
```