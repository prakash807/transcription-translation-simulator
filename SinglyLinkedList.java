/**
 * This class represents a singly linked list.
 * @author Prakash
 *
 * @param <T> Generic type of element in each list node
 */

public class SinglyLinkedList<T> {
	
	/**
	 * The node at the front of the list
	 */
	private SinglyLinkedListNode<T> front;
	
	/**
	 * Constructor to initialize a singly linked list.
	 * It is initialized with a null value for front
	 */
	public SinglyLinkedList() {
		
		front = null;
		
	}
	
	/**
	 * Accessor method for front node
	 * @return Front node of list
	 */
	public SinglyLinkedListNode<T> getFront() {
		
		return front;
		
	}
	
	/**
	 * Method to find and return the node at the tail of the list
	 * @return Tail node of list
	 */
	public SinglyLinkedListNode<T> findTail() {
		
		// Start at the front node
		SinglyLinkedListNode<T> current = front;
		// If that is null, that means front is null, which must mean back is also null
		if (current == null) return null;
		// Otherwise
		// move the the next node, until it is a node with no next node
		while (current.getNext() != null) {
			
			current = current.getNext();
			
		}
		// Current now has no next node so it must be the tail node
		return current;
		
	}
	
	/**
	 * Method to insert a node into the list
	 * @param newNode The node to be inserted into the list
	 * @param predecessor The node in the list, immediately after which the new node will be inserted
	 */
	public void insert(SinglyLinkedListNode<T> newNode, SinglyLinkedListNode<T> predecessor) {
		
		// If predecessor is null, that means we want to make newNode the front node now (insert it at the front)
		if (predecessor == null) {
			
			// Set newNode's next as the current front
			newNode.setNext(front);
			// Now update front to be the newly added node
			front = newNode;
			
		} else { // Otherwise
			
			// Find the successor of predecessor
			SinglyLinkedListNode<T> successor = predecessor.getNext();
			// That successor shall now instead be the successor of newNode
			newNode.setNext(successor);
			// and the successor of predecessor shall now be newNode
			predecessor.setNext(newNode);
			
		}
		
	}
	
	/**
	 * Method to delete a node from the list
	 * @param nodeToDelete The node to be deleted from the list
	 * @return true if the node was successfully deleted, false if it was not
	 */
	public boolean delete(SinglyLinkedListNode<T> nodeToDelete) {
		
		// Declare nodes that will be used in this process
		SinglyLinkedListNode<T> current;
		SinglyLinkedListNode<T> predecessor;
		
		// Start with current as the front node, and thus its predecessor as null
		current = front;
		predecessor = null;
		
		// Until we have passed the tail node or we have reached the node we want to delete
		while ((current != null) && (current != nodeToDelete)) {
			// Keep updating predecessor and current
			predecessor = current;
			current = current.getNext();
		}
		
		// If we passed the tail node, the node of interest must not be in the list, so return false
		if (current == null) {
			return false;
		} else {// If did reach the node we want to delete
			
			// If the node to delete is not the front node
			if (predecessor != null)
				// the predecessor's successor is now the node that was successor to the node being deleted
				predecessor.setNext(current.getNext());
			// Otherwise, update front to be the next node
			else front = front.getNext();
			// Now the node has been deleted so return true
			return true;
		
		}
	} 

}
