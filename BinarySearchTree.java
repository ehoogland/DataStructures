import java.util.Scanner;
/*
 * @author Eric Hoogland, from course materials in:
 * Data Structures and Algorithms CS201, Study.com
 * My work mostly consisted of putting together code 
 * snippets provided in course materials and then reviewing
 * and making notes on the process of making the code
 * more robust and readable. 
 */
	
public class BinarySearchTree 
{
    class Node // Class Node with the data and the child nodes
    {
		 int data;
		 Node leftChild;
		 Node rightChild;
		 
		 Node (int data) 
		 {
			 this.data = data;
			 leftChild = rightChild = null;
		 }
    }
    Node root; //root node for the binary tree
    
    //Constructor
    public BinarySearchTree()
    {
		 root = null;
    }
 
    // Method for inserting new values into the tree
    public void insert (int key)
	{
		 root = insertNode(root, key);
    }
    // Insert recursive call for inserting from the root
    public Node insertNode (Node node, int key)
	{   // base case
	    if (node == null)
		{ 
			 node = new Node(key);
			 return node;
		}
		if (key <= node.data)
		{
		    node.leftChild = insertNode(node.leftChild, key);
		}
		if (key > root.data)
		{
		    node.rightChild = insertNode(node.rightChild, key);
		}
		return node;
	}
	
    // Find method asking for the node to find
    public Node find(int key)
	{
		 Node node = findNode(root, key);
		 return node;
    }
    //Find recursive method using the root node.
    public Node findNode (Node node, int key)
	{
	    if (key == node.data)
	    {
			 return node;
	    }	
		if (key <= node.data) // less than or equal to goes to left
		{
		    if (node.leftChild == null)
			{
				return null;
			}
			else
			{
				return findNode(node.leftChild, key);
			}
		} 
		if (key > node.data)
		{
			  if (node.rightChild == null)
			  {
				  return null;
			  }
			  else
			  {
				  return findNode(node.rightChild, key);
			  }
		} 
		return node;
	}

 	public static void main (String[] args)
	{
		BinarySearchTree binaryTree = new BinarySearchTree();
		Scanner scanner = new Scanner(System.in);
		
	    
		
		for (int i = 1; i <= 10; i++) 
		{   
			System.out.print("Enter value " + i + " for the tree: ");
			binaryTree.insert(scanner.nextInt());
		}
		System.out.print("Enter the value to be searched: ");
		int key = scanner.nextInt();
		Node node = binaryTree.find(key);
		
		if (node == null)
		{
			System.out.print("The number does not exist. ");
		} 
		else 
		{
			System.out.print("Node " + node.data + " was found. ");
		} 
	} 
}
	
 	/*
 	    In a Binary Tree any value on the left side of the root is
    	less than or equal to the value of the root; any value on the 
    	right side, greater. In a Binary Search Tree, every time you 
    	search for a value, you halve the data to analyze. Repeat numbers 
    	can affect performance.
    	
    	Initially this binary search tree is populated by an array of 
		integers called data[];
		
    	Next, the user populates the array using the Scanner class and a 
    	for loop. 
		
		The final version of the program eliminates the array and loop 
    	and has the user input numbers directly into the binary search tree.
	 
	2. The tree was first created with a fixed array:
	
    	int data = {3,7,1,8,4,9,2,4,6,1};
    	BinarySearchTree binaryTree = new BinarySearchTree();
    	for (int i = 0; i < data.length; i++)
    	{
    		binaryTree.insert(data[i]);
    	}
    	
    	The program is changed to ask for the values to search using the class Scanner
    	to read the terminal.
    	
    	To enable the code to ask for the numbers to be put into the tree,
    	the code is given a loop which asks, ten times, for a number 
    	to fill the data array. 
	
    	public static void main (String[] args)
    	{
    		// deleted this when Scanner class added to populate array
    		int data = {3,7,1,8,4,9,2,4,6,1};
    		BinarySearchTree binaryTree = new BinarySearchTree();
    		// use Scanner class to read terminal
    		Scanner scanner = new Scanner(System.in);
    		
    		// added this empty array and for loop when Scanner class added
            // starts with "Enter value 0 for the tree"; not intuitive		
    		int[] data = new int[10]; // array length is 10		
    		for (int i = 0; i < data.length; i++)			
    		{   
    			System.out.print("Enter value " + i + " for the tree: ");
    			data[i] = scanner.nextInt();
    		}
    		for (int i = 0; i < data.length; i++)
    		{
    			binaryTree.insert(data[i]);
    		}
    		
    		Node node = binaryTree.find(9);
    		
    		// Original code didn't have a message for cases in which 
    	    // a value was searched for and not found
    		if (node == null)
    		{
    			System.out.println("The number does not exist");
    		} 
    		else 
    		{
    			System.out.println("Node " + node.data + " was found");
    		} // original had just this line
    		System.out.println(node.data);
    		}


		The code got messier with loops for reading the numbers, 
		inserting them into the tree, and searching.
		
		Numbers chosen for insertion by the user now go directly into the tree.	
		Removed	int[] data = new int[10]; and "data[i] = scanner.nextInt();"
		Used "binaryTree.insert(scanner.nextInt());" to insert into the binary
		search tree.
		
		The array-based zero-indexing is changed to more intuitive values
		starting with one, up to "Enter value 10 for the tree: ". The user is 
		then prompted to enter a number to search.
*/
