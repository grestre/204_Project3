import java.util.*;

public class BasicDoubleLinkedList<T> implements Iterable<T>{
	Node <T> head = null; 
	Node <T> tail = null; 
	int size = 0; 
	public BasicDoubleLinkedList() {
		this.head = null; 
		this.tail = null; 
		this.size = 0; 
	}
	
	public class Node <T>{
		T data;
		Node prev, next; 
		public Node(T dataNode) {
			this.data = dataNode;  
			this.next = next; 
			this.prev = prev; 
		}
	}
	public class DoubleLinkedListIterator implements ListIterator<T>{
		Iterator iterator;
		Node current; 
		
		public DoubleLinkedListIterator() {
			current = head; 
		}
		@Override
		/**
		 * @return True if the iterator has another entry to return when traversing the list forward; otherwise returns false
		 */
		public boolean hasNext() {
			if(current != null) {
				return true; 
			}
			return false;
		}

		@Override
		/**
		 * @return A reference to the next entry in the iteration, if one exists
		 * @throws NoSuchElementException if the iterator is at the end, that is, if hasNext() is false
		 */
		public T next() throws NoSuchElementException{
			if(hasNext()) {										//Checks if there is a next element
				T temp = (T) current.data; 						//temp equals the current value
				tail = current; 
				current = current.next; 						//Moves the current value to the next value
				if(hasNext()) {
					current.prev = tail; 
				}
				return temp; 									//Returns next
			}
			else
				throw new NoSuchElementException(); 
		}

		@Override
		/**
		 * @return True if the iterator has another entry to visit when traversing the list backward; otherwise returns false
		 */
		public boolean hasPrevious() {
			if(tail != null)
				return true; 
			else
				return false; 
		}

		@Override
		/**
		 * @return A reference to the previous entry in the iteration, if one exists
		 * @throws NoSuchElemenetException if the iterator has no previous entry, that is, if hasPrevious() is false
		 */
		public T previous()  throws NoSuchElementException{
			if(hasPrevious()) {									//Checks if there is a previous element 
				current = tail; 								//Starts from the tail
				tail = current.prev; 							//Elements go back
				T temp = (T) current.data; 
				return temp; 									//Returns the current value
			}
			else
				throw new NoSuchElementException(); 
			}
		@Override
		public int nextIndex(){
			throw new UnsupportedOperationException(); 
		}

		@Override
		public int previousIndex(){
			throw new UnsupportedOperationException(); 
		}

		@Override
		public void remove(){
			throw new UnsupportedOperationException(); 
		}

		@Override
		public void set(Object e){
			throw new UnsupportedOperationException(); 
		}

		@Override
		public void add(Object e){
			throw new UnsupportedOperationException(); 
		}
		
	}
	//Adds an element to the end of the list and updated the size of the list
	void addToEnd(T data) {
		Node temp = new Node(data); 
		if(head == null) {								//If the list is empty
			head = temp;
			tail = temp; 
			head.prev = null; 							//Previous value of the head is set to null
			tail.next = null; 							//Next value of the tail is set to null
			size++; 									//Increases the size of the linked list
		}
		else {
			tail.next = temp; 							//Creates a new value after the tail and is set to temp
			temp.prev = tail; 							//Previous value of temp is set to the tail
			tail = temp;  								//Moves the tail to the temp
			size++; 									//Increases the size of the linked list
		}
	}
	//Adds element to the front of the list and updated the size of the list
	void addToFront(T data) {
		Node temp = new Node(data); 					//Create new node temp
		if(head == null) {								//Checks if the list is empty
			head = temp; 
			tail = temp; 
			head.prev = null; 							//Previous value of the head is set to null
			tail.next = null; 							//Next value of the tail is set to null
			
		}
		else {
			temp.next = head; 							//next value is set to the head
			head.prev = temp; 							//Previous value of the head is set to temp
			temp.prev = null; 							//Previous value of the temp is set to null to set it as the head
			head = temp; 								//New value of temp is set to the head
		}
	}
	/**
	 * Returns but does not remove the first element from the list
	 * if there are no elements the methods returns null
	 * Do not implement using iterators
	 * @return The data element or null
	 */
	T getFirst() {
		return head.data; 
	}
	/**
	 * Returns but does not remove the last element from the list
	 * If there are no elements the method returns null.
	 * Do not implement using iterators
	 * @return the data element or null
	 */
	T getLast() {
		return tail.data; 
	}
	//Returns the number of nodes in the list
	int getSize() {
		return size; 
	}
	/**
	 * Returns an object of the DoubleLinkedListIterator(The inner class that implements java's ListIterator interface
	 * @return A ListIterator object
	 */
	@Override
	public ListIterator<T> iterator(){
		
		return new DoubleLinkedListIterator();
	}
	/**
	 * Removes the first instance of the targetData from the list
	 * @param targetData - The data element to be removed
	 * @param comparator - The comparator to determine equality of data elements
	 * @return a node containing the targetData or null
	 */
	Node remove(T targetData, Comparator<T> comparator) {
		Node prev = null;
		Node temp = head; 
		while (temp != null) {
			if(comparator.compare((T) temp.data, targetData) == 0) {		//Comparator class to compare the temp with the target data checks if they are equal
				if(temp == head) {											//Checks if the temp value is the head								
					head = head.next;  										//Head is set to the next value of head
				} else if(temp == tail) {									//Checks if the temp value is the tail
					tail = prev; 											//Tail is set to prev
					tail.next = null; 										//next value of tail is set to null 
				} else {
					temp = temp.next; 	
				}
				size--; 													//Size goes down after removing a value
			} else {
				prev = temp; 
				temp = temp.next; 
			}
		}
		return temp; 														//Returns the temp
	}
	/**
	 * Removes and returns the first element from the list. 
	 * If there are no elements return null
	 * Do not implement with iterators
	 * @return data element or null
	 */
	T retrieveFirstElement() {
		Node temp = head; 												//Starts from the head
		head = head.next; 												//Goes to the next element
		head.prev = null; 												//Previous of the current value is set to null
		return (T) temp.data; 											//Returns the value for the first element
	}
	/**
	 * Removes and returns the last element from the list
	 * If there are no elements the method returns null
	 * Do not implement with iterators
	 * @return data element or null
	 */
	T retrieveLastElement() {
		Node temp = tail; 												//Starts from the tail
		tail = tail.prev; 												//Goes to the previous element
		tail.next = null; 												//Next element of the tail is set to null
		return(T) temp.data; 											//Returns the value for the last element
	}
	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * @return an arrayList of the items in the list
	 */
	ArrayList<T> toArrayList(){
		ArrayList<T> list = new ArrayList<T>(); 
		ListIterator<T> iterator = new	DoubleLinkedListIterator(); 
		while(iterator.hasNext()) {										//Checks if there is a next value
			list.add(iterator.next()); 									//Adds the value of the next element from the iterator to the list
		}
		return list; 													//Returns the list
	}
}
