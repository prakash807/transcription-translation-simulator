/**
 * This class is a queue of objects of generic type T.
 * This is a data structure used to perform actions on data in order from "front" to "tail".
 * Although it may be more standard and beneficial in ways to use a pre-made Queue class, I wrote this myself for a few reasons:
 * -I admittedly felt a brief need to test my knowledge regarding this data structure
 * -Only certain methods were needed for the purpose in this project
 * -It meant I would already be familiar with how to use it
 * @author Prakash
 *
 * @param <T> The generic type of data stored in an item in the queue
 */
public class Queue<T> {
	
	/**
	 * The linked list used to implement this queue
	 */
	private SinglyLinkedList<T> list;
	
	/**
	 * Constructor to create a queue and initialize its list
	 */
	public Queue() {
		
		list = new SinglyLinkedList();
		
	}
	
	/**
	 * Method to add a new item into the queue (at the back)
	 * @param newItem The new item to be added into the queue
	 * @return The data stored in the new item. Usually not used
	 */
	public T enqueue(T newItem) {
		
		// Create a node with newItem as its data
		SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode(newItem);
		// Insert this node at the tail of the list
		list.insert(newNode, list.findTail());
		// Normally this method would not return anything. I set it to return the node in case that would be useful and since it seemed to pose no significant risk
		return newNode.getData();
		
	}
	
	/**
	 * Method to take off the item that is at the front of the queue. Its data is returned
	 * @return The data stored in the item that was just taken off
	 */
	public T dequeue() {
		
		// Get the node from the front of the list
		SinglyLinkedListNode<T> nodeToDelete = list.getFront();
		
		// If the list is empty (i.e. the queue is empty), return null
		if (nodeToDelete == null) return null;
		
		//Otherwise
		// Get the data of the node
		T dataToDelete = nodeToDelete.getData();
		// Delete the node from the list (so the next node will now be at the front of the queue)
		list.delete(list.getFront());
		// Return the data of the node we dequeued
		return dataToDelete;
		
	}
	
	/**
	 * A string representation of the whole queue
	 */
	public String toString() {
		
		// This method will add onto an empty string for every node
		// Start by labelling the front
		String str1 = "Front -> ";
		
		// Get the front node
		SinglyLinkedListNode<T> current = list.getFront();
		// For all nodes in the list
		// (If current is null, then there are no nodes and the following loop is not entered)
		while (current != null && current.getNext() != null) {
			
			// Append a string representing a queue item containing the data of this node
			str1 = str1 + " -> QueueItem(" + current.getData() + ')';
			
		}
		
		// Finish by labelling the tail
		str1 = str1 + " -> Tail";
		
		// Return the resulting string
		return str1;
		
	}

}
