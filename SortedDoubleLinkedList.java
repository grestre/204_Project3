import java.util.*; 

public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList<T>  implements Iterable<T>{
	Comparator<T> comparator = null;	
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		this.comparator = compareableObject; 
	}
	/*
	 * Inserts the specified element at the correct position in the sorted list
	 */
	void add(T data) {
		Node temp = new Node(data); 
		if(head == null) {													//Checks if the head is null
			head = temp; 													//Linked list is empty
			tail = temp; 													//Linked list is empty
		}else {
		if(comparator.compare(data, head.data) <= 0) {						//Checks if the head data is greater than the current data 
			head.prev = temp; 
			temp.next = head; 
			head = temp; 
		}
		else if(comparator.compare(data, tail.data) >= 0) {					//Checks if data is greater than the tail
			temp.prev = tail; 
			tail.next = temp; 
			tail = temp; 
		}else {
			Node newNode = head.next; 
			while(comparator.compare(data, (T) newNode.data) == 0) {		//Checks if the data compared are equal
				newNode = newNode.next; 
			}
			newNode.prev.next = temp; 
			temp.next = newNode; 
		}
		}
		size++; 															//Increases the size of the linked list
	}
	/*
	 * Implements the iterator by calling the super class iterator method
	 */
	@Override
	public ListIterator<T> iterator(){
		return new DoubleLinkedListIterator(); 		
	}
	void addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException(); 
	}
	void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException(); 
	}
	
	/*
	 * @return the node that was removed
	 */
	BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator) {
		BasicDoubleLinkedList<T>.Node<T> temp = head; 
		BasicDoubleLinkedList<T>.Node<T> nullNode = null; 
		while(temp != null) {
			if(comparator.compare(temp.data, data) == 0) {					//Compares the temp data with the data and checks if they are equal
				size--; 													//Decreases the size of the linked list
				if(nullNode != null) {
					nullNode.next = temp.next; 
				}
				else {
					head = temp.next; 										//Head is set to the next value of temp
				}
				if(temp == tail) {											//Checks if temp is the tail
					tail = nullNode; 										//tail is set to the nullNode		
				}
			}
			nullNode = temp; 
			temp = temp.next; 												//temp is set to its next value
		}
		return temp;														//Return the temp that was removed
	}
	 
}
