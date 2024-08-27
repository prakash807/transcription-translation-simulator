/**
 * This class represents a node in a singly linked list of generic type T.
 * It stored data of type T.
 * It points to the next node in a linked list via the instance variable next
 * @author Prakash
 *
 * @param <T> The type of the data stored in the node.
 */
public class SinglyLinkedListNode<T> {
	
	/**
	 * Data stored in node
	 */
	private T data;
	/**
	 * This node's successor node. The next node in the linked list.
	 */
	private SinglyLinkedListNode<T> next;
	
	/**
	 * Constructor that initializes this node with null values for data and next.
	 */
	public SinglyLinkedListNode() {
		
		this.data = null;
		this.next = null;
		
	}
	
	/**
	 * Constructor that initializes this node with a given value for data and a null value for next.
	 * @param data The value wished to be stored as data in this node
	 */
	public SinglyLinkedListNode(T data) {
		
		this.data = data;
		this.next = null;
		
	}
	
	/**
	 * Mutator method to set the data stored in the node
	 * @param newData The data desired to be stored in the node
	 */
	public void setData(T newData) {
		data = newData;
	}
	
	/**
	 * Mutator method to set the node that is next
	 * @param newNext The node desired to be next
	 */
	public void setNext(SinglyLinkedListNode<T> newNext) {
		next = newNext;
	}
	
	/**
	 * Accessor method to get the data stored in the node
	 * @return data stored in the node
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Accessor method to get the next node
	 * @return next node
	 */
	public SinglyLinkedListNode<T> getNext() {
		return next;
	}
	
	/**
	 * A string representation of the linked list
	 */
	public String toString() {
		
		// The string is the node's data between the parentheses in "SingNode()" to show this is a singly linked list node with that data in it
		return "SingNode(" + data.toString() + ')';
		
	}

}
