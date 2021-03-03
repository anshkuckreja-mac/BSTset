package setbst;

public class BSTSet {

	
	//Constructors
	public BSTSet() {
		
		//initializes object to represent empty set, empty tree
		
	}
	
	public BSTSet(int[] input) {
		
		//initializes BSTSet object to represent set containig all elements in array input
		//without repetitions.
		//eg if array is 5,6,4,5 then corrosponding set is {5,7,4}
		
	}
	
	//Methods
	public boolean isIn(int v) {
		//return true if integer v is an element of this BSTSet
		
		//else return false
		return false;
	}
	
	public void add(int v) {
		//adds v to this bstset if v isnt a repitition.
		
		//else doesnt do anything.
	}
	public boolean remove(int v) {
		//removes v if present, and returns true
		
		//else return false
		return false;
	}
	public BSTSet union(BSTSet s) {
		//return the union of this and s. should not modify the input sets
		
		return s; //placeholder
	}
	public BSTSet intersection(BSTSet s) {
		//return the intersection of this and s. should not modify the inptu sets
		
		return s; //placeholder
	}
	public int size() {
		//returns number of elements in this
		
		return 0; //placeholder
	}
	public int height() {
		//returns height of this
		
		return 0; //placeholder
	}
	public void printBSTSet() {
		if(root==null)
			System.out.println("The set is empty");
		else {
			System.out.print("The set elements are: ");
			printBSTSet(root);
			System.out.print("\n");
		}
	}
	private void printBSTSet(TNode t) {
		if(t!=null) {
			printBSTSet(t.left);
			System.out.print(" " + t.element + ", ");
			printBSTSet(t.right);
		}
	}
	public void printNonRec() {
		//prints the integers in this in increasing order. nonrecursive.
		//uses a stack to implement inorder traversal, MyStack used.
	}
	public void printLevelOrder() {
		//prints integers in this in level order using a queue, MyQueue
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//asymptotic run time and space complexity must be presented.
		
	}

}
