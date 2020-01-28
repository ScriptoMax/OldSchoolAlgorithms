//case #3.1: design array-based stack-featured data structure

public class ArrayBasedStack<T> {	
		T [] stackArray;
		int upperBound;
		int ind;
		
		public ArrayBasedStack(int size) {
			ind = -1;
			upperBound = size;		
			stackArray = (T[]) (new Object [size]); 
		}
		
		public int size() {
			return ind + 1;
		}
		
		public T getOnTop() {
			return stackArray[ind];
		}
		
		public boolean isEmpty() {
			return ind < 0 ? true : false;
		}
		
		public boolean isFull() {
			return ind > upperBound - 1 ? true : false;
		}
		
		public void push(Object elem) throws FullStackException {
			if(isFull()) {
				throw new FullStackException();
			} else {
				stackArray[++ind] = (T) elem;
			}
		}
		
		public T pop() throws EmptyStackException  {
			if(isEmpty()) {
				throw new EmptyStackException();
			} else {
				return stackArray[ind--];
			}
		}
}

