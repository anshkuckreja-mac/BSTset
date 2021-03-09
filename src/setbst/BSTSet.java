package setbst;

public class BSTSet {

	private TNode root;
	
	//Constructors -------------------------------------------
	public BSTSet() {
		//initializes object to represent empty set, empty tree
		root = null;
		
	}
	
	public BSTSet(int[] input) {
		
		//initializes BSTSet object to represent set containig all elements in array input
		//without repetitions.
		//eg if array is 5,6,4,5 then corrosponding set is {5,7,4}
		
		if(input.length == 0) {
			root = null;
		} 
		else {
			//use insertion sort to sort, remove duplicates, shorten array.
			int[] sorted = sort(input);
			//build bstset
			root = sortArrToBSTRecursive(sorted, 0, sorted.length-1);
		}
	}
	
	//helper method for creating the bst
	public TNode sortArrToBSTRecursive(int[] arr, int start, int end) {

		//base case
		if(start > end) {
			return null;
		}

		//turn middle into the root
		int mid = (start + end) / 2;
		TNode nodes = new TNode(arr[mid], null, null);

		//make left tree, and make it left child
		nodes.left = sortArrToBSTRecursive(arr, start, mid - 1);
		
		//make right tree, and make it right child
		nodes.right = sortArrToBSTRecursive(arr, mid + 1, end);
		
		return nodes;
	}
	
	//insertion sort from previous lab
	private int[] sort(int arr[]) {
		int arrLength = arr.length;

		for (int i=1; i<arrLength; ++i) {
			int currentInt = arr[i];
			int j = i-1;

			while (j>=0 && arr[j] > currentInt) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = currentInt;
		}
		int hold = arr[arrLength-1];
		int counter = 0;
		for (int i=0; i<arrLength; i++) {
			if (arr[i] != hold) {
				counter++;
			}
			hold = arr[i];
		}
		int[] output = new int[counter];
		hold = arr[arrLength-1];
		counter = 0;
		for (int i=0; i<arrLength; i++) {
			if (arr[i] != hold) {
				output[counter] = arr[i];
				counter++;
			}
			hold = arr[i];
		}
		return output;
	}

	
	
	//Methods -----------------------------------------------------
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
	public BSTSet difference(BSTSet s) {
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

	public TNode getRoot() {
		return root;
	}
	
	public static void main(String[] args) {

		
		//TODO: asymptotic run time and space complexity must be presented.
		//TODO: the tutorial has code for queue
		//should probably attempt bonus cuz i think i have a balanced one.
		//geeksforgeeks and tutorialspoint was sources.
		
	}

}