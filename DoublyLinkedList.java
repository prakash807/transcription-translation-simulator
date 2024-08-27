/**
 * This class represents a doubly linked list.
 * @author Prakash
 *
 * @param <T> Generic type of element in each list node
 */

public class DoublyLinkedList<T> {
	
	/**
	 * The node at the front of the list
	 */
	private DoublyLinkedListNode<T> front;
	/**
	 * The node at the back of the list
	 */
	private DoublyLinkedListNode<T> tail;
	
	/**
	 * Constructor to initialize a doubly linked list.
	 * It is initialized with null values for front and tail
	 */
	public DoublyLinkedList() {
		
		front = null;
		tail = null;
		
	}
	
	/**
	 * Accessor method for front node
	 * @return Front node of list
	 */
	public DoublyLinkedListNode<T> getFront() {
		
		return front;
		
	}
	
	/**
	 * Accessor method for tail node
	 * @return Tail node of list
	 */
	public DoublyLinkedListNode<T> getTail() {
		
		return tail;
		
	}
	
	/**
	 * Method to insert a node into the list
	 * @param newNode The node to be inserted into the list
	 * @param predecessor The node in the list, immediately after which the new node will be inserted
	 */
	public void insert(DoublyLinkedListNode<T> newNode, DoublyLinkedListNode<T> predecessor) {
		
		// If we want to insert this node at the front
		if (predecessor == null) {
			
			// Set the new node's next value as the front
			newNode.setNext(front);
			if (front != null) { // If the list was not empty
				// Set the front's previous value as the new node
				front.setPrevious(newNode);
			}
			// Update the front to be the new node
			front = newNode;
			
			// If both the predecessor and the tail were null, then the list was empty before. So this newly added node should be both the front and the tail
			if (tail == null) {
				
				tail = newNode;
				
			}
			
		} else { // If we want to insert the node anywhere else
			
			// Set the next value of the predecessor as this new node
			predecessor.setNext(newNode);
			// Set the previous value of the new node as that predecessor
			newNode.setPrevious(predecessor);
			
			if (predecessor.equals(tail)) { // If we are adding this to the tail of the list
				
				// Update the tail to be this new node
				tail = newNode;
				
			} else { // If we are inserting this variable in the middle of the list, fix linkage with the successor
				
				DoublyLinkedListNode<T> successor = predecessor.getNext();
				newNode.setNext(successor);
				successor.setPrevious(newNode);
				
			}
			
		}
		
	}
	
	/**
	 * Method to delete a node from the list
	 * @param nodeToDelete The node to be deleted from the list
	 * @return true if the node was successfully deleted, false if it was not
	 */
	public boolean delete(DoublyLinkedListNode<T> nodeToDelete) {
		
		// Declare variables for the current node and its predecessor
		DoublyLinkedListNode<T> current;
		DoublyLinkedListNode<T> predecessor;
		
		// Start by look at the front node
		current = front;
		predecessor = null;
		
		// Until we reach either the node to delete, or the end of the list
		while ((current != null) && (current != nodeToDelete)) {
			
			// Traverse through the list
			predecessor = current;
			current = current.getNext();
		}
		
		// If we reached the end of the list without finding the node to delete, it was not there so nothing was successfully deleted so return false
		if (current == null) {
			return false;
		} else { // If we did find the node to delete
			
			if (predecessor != null) { // If this node was not at the front
				if (current.equals(tail)) { // If this node is the tail node
					tail = predecessor; // Update the tail node
				}
				// Change the linkages to delete the former tail node
				predecessor.setNext(current.getNext());
				current.getNext().setPrevious(predecessor);
			}
			
			else { // If this was the front, then remove it and update front
				front = front.getNext();
				front.setPrevious(null);
			}
			
			// The node has now been deleted so return true
			return true;
		
		}
	}
	
	/**
	 * Method to create and return a string representation of the doubly linked list
	 * @return String representation of the doubly linked list
	 */
	public String toString() {
		
		// Start at the front node
		DoublyLinkedListNode<T> current = front;
		// Start the string with a label of the front
		String strToReturn = "front";
		
		// For every node
		while (current != null) {
			
			// Append a string representing double linkage and then the toString() value of the current node
			strToReturn = strToReturn + " <-> " + current;
			// Update the current node to the next one
			current = current.getNext();
			
		}
		
		// Finish with a label of the tail
		strToReturn = strToReturn + " tail";
		
		// Return the resulting string
		return strToReturn;
		
	}

}
