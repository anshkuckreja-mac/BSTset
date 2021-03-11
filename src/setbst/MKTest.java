package setbst;

public class MKTest {

	public static void testLevel(BSTSet input) {
		
		input.printLevelOrder();
	}
	
	
	
	public static void main(String[] args) {
		
//		
//		//test level order because its test is confusing....
//		
//		int[] arrBST = {37, 22, 55, 7, 5, 6};
//		// Level 1 -> 7; level 2-> 5, 37; level 3 -> 6, 22, 55.
//		
//		BSTSet funBST = new BSTSet(arrBST);
//		
//		testLevel(funBST);
//		
//		
		
		//test intersection for balance.....
		
		int[] inter1 = {1,2,3,4,5,6,7,8};
		int[] inter2 = {1,3,4,5,6,7,8};
		
		BSTSet fun1 = new BSTSet(inter1);
		BSTSet fun2 = new BSTSet(inter2);
		
		BSTSet fun3 = fun1.intersection(fun2);
		//level order should be 5..... 3,7 .... 1,4,6,8
		//print should be 1,3,4,5,6,7,8
		
		fun3.printLevelOrder();
		System.out.println("");
		fun3.printNonRec();
		//bruh im stumped.
	}

}
