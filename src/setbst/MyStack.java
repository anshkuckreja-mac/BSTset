package setbst;

//from topic 2 lecture

public class MyStack {

	private Nodes head;
	
	public boolean isEmpty() {
		return(head == null);
	}
	public void push(TNode e) {
		head = new Nodes(e, head);
	}
	public TNode pop() {  //adapted from int to TNode
		if(isEmpty()) {
			//EmptyStackException doesnt exist and im lazy soooo
			System.out.println("Stack Underflow");
		}
		TNode element = head.element;
		head = head.next;
		return element;
	}
	//stack peek from geeksforgeeks
	public TNode peek() {
		return head.element;
	}
	
}
