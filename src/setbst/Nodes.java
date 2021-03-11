package setbst;

//used in stack and queue, linked list kinda
//from lecture topic 2 and tutorial 6 and tutorial 3
//mostly lecture topic 2 tho

public class Nodes {
	public TNode element; //have to adapt from TNode
	public Nodes next;
	
	public Nodes(TNode e, Nodes n) {
		this.element = e;
		this.next = n;
	}

}
