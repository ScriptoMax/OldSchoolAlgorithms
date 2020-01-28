//case #3.2: implement linked list-based stack-featured data structure
public class ListBasedStack<T> {
	int size;
	
	private class ListNode {
		T elem;
		ListNode nodeLink;		
	}
	
	ListNode head;
	public ListBasedStack() {
		this.head = null;
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	
	public void push(T a) {
		ListNode tempNode = new ListNode();
		tempNode.elem = a;
		tempNode.nodeLink = head;
		head = tempNode;
		size++;
	}
	
	public T pop() throws EmptyStackException {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		T headNum = head.elem;  
		head = (head).nodeLink;
		size--;
		return headNum;
	}
	
	public T getOnTop() {
		return head.elem;
	}
	
}
