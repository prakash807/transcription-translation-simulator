/**
 * This class represents a node in a doubly linked list of generic type T.
 * It stored data of type T.
 * It points to the next node in a linked list via the instance variable next
 * It also points to the previous node in a linked list via the instance variable previous
 * @author Prakash
 *
 * @param <T> The type of the data stored in the node.
 */
public class DoublyLinkedListNode<T> {
	
	/**
	 * Data stored in node
	 */
	private T data;
	/**
	 * This node's successor node. The next node in the linked list.
	 */
	private DoublyLinkedListNode<T> next;
	/**
	 * This node's predecessor node. The previous node in the linked list.
	 */
	private DoublyLinkedListNode<T> previous;
	
	/**
	 * Constructor that initializes this node with null values for data, next, and previous.
	 */
	public DoublyLinkedListNode() {
		
		this.data = null;
		this.next = null;
		this.previous = null;
		
	}
	
	/**
	 * Constructor that initializes this node with a given value for data and null values for next and previous.
	 * @param data The value wished to be stored as data in this node
	 */
	public DoublyLinkedListNode(T data) {
		
		this.data = data;
		this.next = null;
		this.previous = null;
		
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
	public void setNext(DoublyLinkedListNode<T> newNext) {
		next = newNext;
	}
	
	/**
	 * Mutator method to set the node that is previous
	 * @param newPrevious The node desired to be previous
	 */
	public void setPrevious(DoublyLinkedListNode<T> newPrevious) {
		previous = newPrevious;
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
	public DoublyLinkedListNode<T> getNext() {
		return next;
	}
	
	/**
	 * Accessor method to get the previous node
	 * @return previous node
	 */
	public DoublyLinkedListNode<T> getPrevious() {
		return previous;
	}
	
	/**
	 * A string representation of the linked list
	 */
	public String toString() {
		
		// The string is the node's data between the parentheses in "DoubNode()" to show this is a doubly linked list node with that data in it
		return "DoubNode(" + data.toString() + ')';
		
	}

}
