package pxc190029;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * CS6301, SP2: Implementation of Bounded Queue
 * @author Pengchao
 * @author Jie Su
 */
public class BoundedQueue<T> {
	
	private T[] arr;  // Internal circular array to simulate the queue. 
	private int size; // Actual size of arr.
	private int head; // Pointer to first element of this queue.
	private int tail; // Pointer to the last spot in arr available for a coming element.

	/**
	 * Constructor.
	 * @param clazz: Users specify a data type contained in queue. 
	 * @param capacity: Users specify the capacity of this queue.  
	 */
    public BoundedQueue(Class<T> clazz, int capacity) {
    	size = capacity + 1;
    	arr  = (T[]) Array.newInstance(clazz, size);
    } 
    
    /**
     * Add a new element to this queue.
     * @param x User input value
     * @return true or false
     */
    boolean offer(T x) {
    	// If queue is full, no more elements can be added.
    	if ((tail + 1) % size == head) {
    		return false;
    	}
    	
    	arr[tail++] = x;
    	tail %= size;
    	return true;
    }
    
    /**
     * Get the first element of this queue with deletion.
     * @return the first element of this queue with deletion
     */
    T poll() {
    	if (isEmpty()) {
    		return null;
    	}
    	T res = arr[head++];
    	head %= size;
    	return res;
    }
    
    /**
     * Get the first element of this queue without deletion.
     * @return the first element of this queue without deletion
     */
    T peek() {
    	if (isEmpty()) return null;
    	return arr[head];
    }
    
    /**
     * Get actual number of elements in this queue.
     * @return Queue size
     */
    int size() {
    	if (tail >= head) return tail - head;
    	return size - (head - tail);
    }
    
    /**
     * Check if queue is empty.
     * @return true or false
     */
    boolean isEmpty() {
    	return head == tail;
    }
    
    /**
     * Clear this queue.
     */
    void clear() {
    	head = tail;
    	arr[head] = null;
    }
    
    /**
     * Convert queue into user specified array a.
     * @param a User specified array.
     */
    void toArray(T[] a) {
    	if (isEmpty()) {
    		System.out.println("Queue is empty!");
    		return;
    	}
    	int copiedHead = head;
    	int k = 0;
    	while (true) {
    		if (copiedHead == size) copiedHead = 0;
    		if (copiedHead == tail) break;
    		a[k++] = arr[copiedHead];
    		copiedHead++;
    	}
    }
    


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter size of BoundedQueue: ");
		int queueSize = input.nextInt();
		BoundedQueue foo = new BoundedQueue(Integer.class, queueSize);  
		
		while(true) {
			Integer[] queueArray = new Integer[queueSize];
			System.out.println("Please select: 1. offer() 2. poll() 3. peek() 4. size() 5. isEmpty() 6. clear() 7. toArray(): 8. Quit ");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
						
			switch(choice) {
			case 1: // offer(x)
				
				System.out.println("Enter the number to be added:");
				if (foo.offer(in.nextInt())) {
					foo.toArray(queueArray);
					System.out.println("Current queue is: ");
					System.out.println(Arrays.toString(queueArray));
				}
				else {
					System.out.println("Queue is full!");
				}
				break;
				
			case 2: // poll()
				
				Integer res = (Integer) foo.poll();
				if (res == null) {
					System.out.println("Queue is empty!");
				}
				else {
					System.out.println(res + " has been taken out of the queue.");
					foo.toArray(queueArray);
					System.out.println("Current queue is: ");
					System.out.println(Arrays.toString(queueArray));
				}
				break;
				
			case 3: // peek()
				
				Integer peek = (Integer) foo.peek();
				if (peek == null) {
					System.out.println("Queue is empty!");
				}
				else {
					System.out.println("Frist element of the queue is: " + peek);
				}
				break;
				
			case 4: // Get size of queue. 
				
				System.out.println("Current queue size is: " + foo.size());
				break;
				
			case 5: // check if queue is empty.
				
				boolean empty = foo.isEmpty();
				if (empty) System.out.println("Current queue IS empty.");
				else System.out.println("Current queue IS NOT empty.");
				break;
				
			case 6: // clear the queue.
				
				foo.clear();
				System.out.println("Queue is now empty.");
				break;
				
			case 7: // toArray()
				
				foo.toArray(queueArray);
				System.out.println(Arrays.toString(queueArray));
				break;
				
			case 8: // Quit.
				break;
				
			}
			if (choice == 8) break;
		}
        
	}

}
