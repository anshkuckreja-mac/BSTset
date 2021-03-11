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
			//first bubble sort
			int[] sorted = sort(input);
			
			//then remove duplicates
			int[] uniqued = unique(sorted);
			
			
			//build bstset
			//note that this method, because it starts at the
			//middle, it will be balanced always. its simple maths!
			root = sortArrToBSTRecursive(uniqued, 0, uniqued.length-1);
		}
	}
	
	//bubble sort from previous lab
	public int[] sort(int[] arr) {
		
		int temp; 
		
		//loop through array, nested. 
		//compare adjacent ones and swap.
		int arrLength = arr.length;
		for(int i=0;i<arrLength;i++) {
			for(int j=i+1; j<arrLength; j++) {
				if(arr[i]>arr[j]) {
					temp = arr[i];
					arr[i]= arr[j];
					arr[j] = temp;
				}
			}
		}
		
		return arr;
		//time complexity is n^2 but i dont think we need to know that
	}
	//helper function to remove duplicates
	public int[] unique(int[] arr) {
		
		//size may be different, make new array copy
		int arrLength = arr.length;
		int[] unique = new int[arrLength];
		
		int j=0; //index of unique
		for(int i=0; i<arrLength-1; i++) {
			//if adjacent elements are not the same then add to unique
			if(arr[i] != arr[i+1]) {
				unique[j++] = arr[i];
			}			
		}
		//store last element cuz its not in the loop
		unique[j++] = arr[arrLength-1];
		
		return unique;
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
	


	
	
	//Methods -----------------------------------------------------
	public boolean isIn(int v) {
		//return true if integer v is an element of this BSTSet
		
		//base case "else return false"
		if (root == null) {
			return false;
		}
		
		//call a recursive function that literally goes through all nodes
		//and checks if the element exists or not.
		return (isInRecursive(root, v));
		
		//log n because we do binary search.
	}

	
	//helper method for isIn
	private boolean isInRecursive(TNode node, int v) {
		
		//if node doesnt exist
		if(node == null) {
			return false;
		}

		//node element matches
		if(node.element == v) {
			return true;
		}

		//check and return reuslt from left tree child
		boolean leftCheck = isInRecursive(node.left, v);

		if (leftCheck == true) {
			return true;
		}
		//if its false then try right side.

		//check and return reuslt from right tree child
		boolean rightCheck = isInRecursive(node.right, v);

		return rightCheck; //can be false or true.
		
	}
	
	public void add(int v) {
		//adds v to this bstset if v isnt a repitition.
		
		//trivial case where root is null, just make the root.
		if(root == null) {
			root = new TNode(v, null, null);
		} 
		else {
			//call a recursive add function that checks each child tree
			//it keeps on going until it finds where it can append
			//or until it finds its repetition
			addRecursive(root,v);
		}
		//if repetition, nothing happens.
		
		//log n because we do binary search and operations
	}
	
	//helper function for add
	public TNode addRecursive(TNode node, int v) {

		//base case if empty tree
		if(node == null) {
			node = new TNode(v,null,null);
		}
		
		//add to left tree if value is less than node
		if(v<node.element) {
			node.left = addRecursive(node.left,v);
		}
		//add to right tree if value is less than node
		else if(v>node.element) {
			node.right = addRecursive(node.right,v);
		}
		
		//if everything fails just return node
		return node;
	}
	
	public boolean remove(int v) {
		//removes v if present, and returns true

		//if v isnt in the tree then dont do anything
		if(isIn(v) == false) {
			return false;
		}
		//else then remove and return true
		else {
			removeRecursive(root,v);
			return true;
		}
		
		//log n because we do binary search and operations
	}
	
	//helper function for remove
	public TNode removeRecursive(TNode node, int v) {
		
		//base case if empty
		if(node == null) {
			return node;
		}
		
		//basically to remove the node, we just gotta do
		//if value is less than node.element then go left
		//if value is greater than node.element then go right
		//if its equal, then remove!
		if(v<node.element) {
			node.left = removeRecursive(node.left,v);
		}
		else if(v>node.element) {
			node.right = removeRecursive(node.right,v);
		}
		else {
			//this gets weird because we have to 
			//handle if this is an internal node
			
			//if left address is null, just replace the node with the right child!
			if(node.left == null) {
				return node.right;
			}
			//if right address is null, just replace the node with the left child!
			else if(node.right == null) {
				return node.left;
			}
			
			//if the node has two children we need to replace it with the 
			//smallest value in the right child tree
			//or the largest value in the left child tree.
			//how does the first option sound?
			//anyways call a helper function to find minimum 
			node.element = minimum(node.right);
			
			
			//now if we call removeRecursive with the new node.element,
			//node.element = v
			//so it'll just return the node. this is wack, yo!
			node.right = removeRecursive(node.right, node.element);
		}
		
		//if the element isnt here, then just return.
		return node;
	}
	
	//helper function for remove
	public int minimum(TNode node) {
		int min = node.element;
		while(node.left != null) {
			min = node.left.element;
			root = node.left;
		}
		//when you run out just return min
		return min;
	}
	
	public BSTSet union(BSTSet s) {
		//return the union of this and s. should not modify the input sets
		
		//make an empty unioned BSTSet 
		BSTSet unioned = new BSTSet();
		
		//literally copy this into unioned with add
		unionRecursive(unioned,root);
		//literally also copy s into unioned too
		unionRecursive(unioned,s.root);

		return unioned; 
		
		//nlogm time complexity because 
	}
	public void unionRecursive(BSTSet s, TNode node) {
		
		if(node != null) {
			//add elements together
			s.add(node.element);
			//try it for left subtree
			unionRecursive(s,node.left);
			//try it for right subtree
			unionRecursive(s,node.right);
		}
		
		else {
			; //literally do nothing
		}
	}
	
	public BSTSet intersection(BSTSet s) {
		//return the intersection of this and s. should not modify the input sets
		//only the common elements
		


		//make an empty intersected BSTSet.
		BSTSet intersected = new BSTSet();
		
		//then call intersectionRecursive to find only elements
		//that are both in this and s (root is this)
		intersectionRecursive(s.root, intersected);

		return intersected;
		
		//TODO: broken! it isnt balanced...
		
		//logn + logm because it calls isin and add only
	}
	
	public void intersectionRecursive(TNode node, BSTSet s) {
		//if node exists then...
		if(node != null) {
			
			//preorder traversal pretty much.
			//but even inorder and postorder doesnt work argggggg
			//is isIn or add the problem?
			//likely add....
			
			
			//if the element is in the set then add it to the new set.
			if(isIn(node.element)) {
				s.add(node.element);
			}
			
			//now do left subtree
			intersectionRecursive(node.left, s);
			

			//now do right subtree
			intersectionRecursive(node.right, s);
	
			
			
		}
	}

	
	
	public BSTSet difference(BSTSet s) {
		//return the intersection of this and s. should not modify the input sets
		//basically whats in this thats not in s
		
		//make an empty differed BSTSet 
		BSTSet differed = new BSTSet();

		//then do call differedRecursive to find elements
		//that are in this and not s (root is this)
		differenceRecursive(s,root,differed);
		
		return differed;
		
		//nlogn + mlogm complexity, goes through n and m. for every element
		//but it is divided due to isIn and stuff, so it has log instead of squared.
		
		
		
		//Grrrrr i had a recursive remove method...
		//where you just call remove() from differed when differed contains this
		//but it didnt work.........
		//whatever this is simpler
	}
	public void differenceRecursive(BSTSet s, TNode node, BSTSet differed) {
		//if node exists then...
		if(node != null) {
			//find if the node's element is in s
			//if it is, do nothing.
			//if it isnt then add the element since its part of the difference
			if(s.isIn(node.element)) {
				; //will be subtracted by s
			}
			else {
				differed.add(node.element);
			}
			//now do left subtree
			differenceRecursive(s, node.left, differed);
			//now do right subtree
			differenceRecursive(s, node.right, differed);
		}
	}
	
	public int size() {
		//returns number of elements in this
		
		//call a recursive function to find size of whole tree from root
		return sizeRecursive(root);
		
		//n because we need to check each node
	}
	public int sizeRecursive(TNode node) {
		//empty tree base case
		if(node == null) {
			return 0;
		}
		else {
			//literally add together and return the sum of the size.
			//at the end u cant recurse anymore and you just sum.
			return(sizeRecursive(node.left) + 1 + sizeRecursive(node.right));
		}
	}
	
	
	public int height() {
		//returns height of this
		
		//call a recursive function.
		return heightRecursive(root);
		
		//n because we check every node
	}
	
	public int heightRecursive(TNode node) {
		//eg if just 1 node, height will be zero because
		//you have it having left height adding 1
		//but then base case subtracting 1
		//so its 0
		
		//if its empty tree, no height exists. must be -1
		if(node == null) {
			return -1;
		}
		else {
			//compute depth of each subtree
			int leftHeight = heightRecursive(node.left);
			int rightHeight = heightRecursive(node.right);
			
			//use larger one. recursively forever. until you find height finally.
			if(rightHeight > leftHeight) {
				return(rightHeight+1);
			}
			else {
				return(leftHeight+1);
			}
		}
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
		
		//geeksforgeeks again!
		
		//make a root
		TNode current = root;
		
		// make a stack
		MyStack stack = new MyStack();
		
		//do inorder traversal which is "left, print, right"
		//when the inorder hits, put the element in the stack.
		//then you can just pop and print in order
		
		//loop forever until current == null, you break.
		while(true) {
			
			//1: first find leftmost node of current node
			while(current != null) {
				//place pointer to a tree node on the stack before 
				//traversing the node's left subtree
				stack.push(current);
				current = current.left;
			}
			
			//when empty you break out of loop
			if(stack.isEmpty()) {
				break;
			}
			
			//2: do parent
			current = stack.pop();	
			System.out.print(current.element + ", "); //comma for separation, from test cases
			
			//3: do right
			current = current.right;	
		}
		
		//n because we view every single one.
		//this is despite the nested loops.
	}
	public void printLevelOrder() {
		//prints integers in this in level order using a queue, MyQueue
				
		//make a root
		TNode current = root;
		
		//if the tree exists...
		if(current != null) {
			//make a queue
			MyQueue queue = new MyQueue();
			
			//enqueue the current root
			queue.enqueue(current);
			
			//while the queue is not empty
			while(queue.isEmpty() == false){
				
				//dequeue and print the node
				//then check its left child
				//then check its right child
				//then loop again! literally genius
				
				TNode node = queue.dequeue();
				System.out.print(node.element + ", ");//cant print lines, must be in same line
				
				if(node.left != null) {
					queue.enqueue(node.left);
				}
				if(node.right != null) {
					queue.enqueue(node.right);
				}
				
			}
			
		}
		//if tree doesnt exist
		else {
			System.out.print("The tree doesn't exist");
		}
		
		//n because we go through each node.
	}

	public TNode getRoot() {
		return root;
	}
	
	public static void main(String[] args) {

	}

}