package setbst;

//from topic 2 lecture

public class MyQueue {

	private Nodes front;
	private Nodes end;

	public MyQueue() {//empty queue constructor
		front = new Nodes(null,null);
		end = front;
	}
	public boolean isEmpty() {
		return(front==end); //true if empty
	}
	public void enqueue(TNode e) { // adapted from int like usual
		end.next = new Nodes(e,null);
		end = end.next;
	}
	public TNode dequeue() {
		//if its empty then error
		if(isEmpty()) {
			System.out.println("Its empty");
		}
		//if behind == the one after front
		//then behind = front
		if(end == front.next) {
			end = front;
		}
		TNode element = front.next.element;
		front.next = front.next.next;
		
		return element;
	}

}
