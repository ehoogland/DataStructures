import java.util.Arrays;
import java.util.Scanner;
// or: import java.util.*;
/**
 * Computer Science 201: Data Structures and Algorithms 
 * Assignment: Navigating Data; Part 1: Sorting Arrays
 * File: SortingArrays.java
 * Class: SortingArrays
 * @author Eric Hoogland
 *
 * Compile: javac SortingArrays.java (creates bytecode SortingArrays.class file)
 * Run: java SortingArrays 
 * Run (Alternative): Paste text into JDoodle or similar on-line compiler. Enable interactive mode.
 *
 * Testing: tableStatesCaps.length was first assigned to the variable ROWS to reduce code 
 *          duplication, but also facilitated testing: commenting tableStatesCaps.length and 
 *          assigning a small number such as 2 or 4 to ROWS reduces tedious state capital entries.
 * 
 * Program: The Arrays and Scanner libraries are first imported (java.util.* works). In main(), String[][] 
 *          tableStatesCaps[50][2] is constructed but not populated. This adds a line but can
 *          helps with referencing array.length.  The getTable() method is called and returns
 *.         table[][], a 2D array of states and capitals. The program helpfully fails to compile if the  
 *          source 2D array is lengthened, since both it an the receiving array are constructed as [50][2].
 *          The returned table is then assigned to tableStatesCaps[][]. A Scanner input object is created 
 *          to obtain user input. A counter is initialized to keep track of correct user input. 
 *          
 *			Method calls begin with PrintTable(), which uses the Arrays.toString() method
 *          to display each state-state capital pair/array/row. The stable bubbleSort method
 *          is then used to lexically order arrays/rows by state capital from smallest to largest. 
 *          Next, user input of state capitals is converted to lower case and stripped
 *          of leading and trailing white space. User input and the sorted array Strings are compared
 *          using the compareTo() method. A total is given after each correct guess and a grand total
 *          and congratulatory message is displayed at the end of the program.
 *          
 */
public class SortingArrays 
{   
    public static void main(String args[]) 
	{   
	    String[][] tableStatesCaps = new String[50][2]; // construct 2D array
	    tableStatesCaps = getTable(); // populate array
		 
		int ROWS = tableStatesCaps.length; 
		int countNumRight = 0; // Declare counter for correct answers
		
		// create Scanner object "input" to obtain user input
	    Scanner input = new Scanner(System.in);
	    
		// display title, call method to display 2D array.
		System.out.println("\nU.S. States and Capitals");
		System.out.println("------------------------");
		// Method contains Arrays.toString(tableStatesCaps[row]) for 2D arrays.
	    printTable(tableStatesCaps, ROWS); 
	    
       	// Call bubbleSort method. Zero-indexed number 1, the second column, is sorted on.
        bubbleSort(tableStatesCaps, 1, ROWS); // 
		// Display title for bubble sort
        System.out.println("\n\nAscending Bubble Sort on State Capitals");
	    System.out.println("---------------------------------------");
        // Call printTable() method again, this time to display result of bubble sort
        printTable(tableStatesCaps, ROWS); 
		
		// loop through the arrays of the 2D array
		for (int i = 0; i < ROWS; i++)
		{   
			for (int j = 0; j < tableStatesCaps[0].length-1; j++)
			{
				System.out.println(""); 
				// [i][0] is the particular state
				System.out.print("Enter capital of " + tableStatesCaps[i][0] + ": ");
				
				/**
				 * nextLine() can read words which contain spaces
				 *
				 * equalsIgnoreCase() makes case insensitive String comparisons.
				 * @param String anotherString
				 * @return boolean is true if the capitals--excepting case and leading
				 * and trailing white space--lexically match 
				 *         
			     * trim() method strips left and right white space from a String. 
				 * strip() method is much faster given significant white space.
				 * That is unlikely here, so trim() is used for backward compatibility.
			     */
			     if ( input.nextLine().trim().equalsIgnoreCase(tableStatesCaps[i][1].trim()) )
			     {
					System.out.println("You're right.");
					countNumRight++;
					System.out.println("Number correct: " + countNumRight + "\n");
			     }
			     else // [i][1] is the state capital column
			         System.out.println("Incorrect--it's " + tableStatesCaps[i][1] + "." + "\n");
			}	
		}
		System.out.println("\nThanks for playing!");
		System.out.print("Total number correct: " + countNumRight);
	} // end main  
	
    /** 
     * Method to sort strings by capital using bubble sort. This sort is stable.
     * @param   String[][] tableStatesCaps the array of the arrays of states and their capitals
     * @param   col column. Parameter always takes an argument of 1 in this game, the second column.
     * @param   ROWS = tableStatesCaps.length Since tableStatesCaps is [50][2] this is 50. 
     * 
	 * 
	 * Base case			ROWS == 1. Recall ROW is just table.length, and length method is not zero-based
     * Outer loop:          [R]ow is zero-indexed. Last row cycled through is 49, making the inner loop's 
     *                      j < ROWS - row,  50 - 49, or 1 in that case. 
     * 
     * Inner loop's:        Since j begins at 1, j - 1 < 1 is only true if j is decremented. Since j is 
     *                      incremented, j is effectively fixed at 1.
     * 
     * Inner loop's if:     J being fixed at 1 means that j - 1 is always 0, so to the left of compareTo 
     *                      [j minus 1] is always 0 and to the right [j] is always 1. The bubbleSort 
     *                      method call value for the col parameter is always 1. So, for example, element 
	 *						[0][1] is always lexically compared to [1][1]. "Juneau", at [0][1], 
	 *						is lexically less than "Montgomery", so compareTo returns > 0. The if condition is met.
	 *
     * Inner loop's swap    Unlike the if condition, in which elements are compared, whole rows are swapped 
     *                      by means of a temp variable. Note how temp holds a String array, temp[], each row being 
	 *                      an array  
     */ 
	static void bubbleSort(String[][] tableStatesCaps, int col, int ROWS)
	{
		if (ROWS == 1) // base case 
		return;
	
		for (int row = 0; row < ROWS-1; row++) 
        {
            for (int j = 1; j < ROWS - row; j++)
			{   
				if (tableStatesCaps[j-1][col].compareTo(tableStatesCaps[j][col]) > 0)    // ascending sort 
				{   												
					String[] temp = tableStatesCaps[j]; 		    // c := a
					tableStatesCaps[j] = tableStatesCaps[j-1]; 		// a := b
					tableStatesCaps[j-1] = temp; 				    // a := c	
				}
			}
		}
	}
	/**
     * PrintTable() wraps the Arrays.toString() method.  
	 * Arrays.toString() displays array rows in ascending order, by capital.
     * @param String[][] tableStatesCaps 2D array of states and capitals
	 * @param int ROWS   table.length is assigned to it. Table dimensions: [50][2] 
	 */
	public static void printTable(String[][] tableStatesCaps, int ROWS)
	{
	    System.out.println("");
	    for (int row = 0; row < ROWS; row++)
	    {   // braces not required
	        System.out.println(Arrays.toString(tableStatesCaps[row]));
	    }
		System.out.println("");
	}
    /**
     * @author Eric Hoogland
     * @return table a 2D String[][] array
     */ 
	public static String[][] getTable()
    {   //Explicitly states array dimensions.
        String[][] table = new String[50][2]; 
        // Visually reflects 2D array row and column structure
		table[0][0] = "Alabama";        table[0][1] = "Montgomery";
	    table[1][0] = "Alaska";	        table[1][1] = "Juneau"; 
		table[2][0] = "Arizona";        table[2][1] = "Phoenix";
		table[3][0] = "Arkansas";       table[3][1] = "Little Rock";
		table[4][0] = "California";     table[4][1] = "Sacramento";
	    table[5][0] = "Colorado";       table[5][1] = "Denver";
	    table[6][0] = "Connecticut";    table[6][1] = "Hartford";
	  	table[7][0] = "Delaware";       table[7][1] = "Dover";
        table[8][0] = "Florida";        table[8][1] = "Tallahassee";
	    table[9][0] = "Georgia";        table[9][1] = "Atlanta";
	    table[10][0] = "Hawaii";        table[10][1] = "Honolulu"; 
	    table[11][0] = "Idaho";         table[11][1] = "Boise";
	    table[12][0] = "Illinois";      table[12][1] = "Springfield";
	    table[13][0] = "Indiana";       table[13][1] = "Indianapolis";
		table[14][0] = "Iowa";          table[14][1] = "Des Moines";
	    table[15][0] = "Kansas";        table[15][1] = "Topeka";
	    table[16][0] = "Kentucky";      table[16][1] = "Frankfort";
		table[17][0] = "Louisiana";     table[17][1] = "Baton Rouge";
		table[18][0] = "Maine";         table[18][1] = "Bangor";
	    table[19][0] = "Maryland";      table[19][1] = "Annapolis";
	    table[20][0] = "Massachusetts"; table[20][1] = "Boston"; 
		table[21][0] = "Michigan"; 	    table[21][1] = "Lansing";  
	    table[22][0] = "Minnesota";     table[22][1] = "Minneapolis";
	    table[23][0] = "Mississippi";	table[23][1] = "Jackson"; 
	    table[24][0] = "Missouri";      table[24][1] = "Jefferson City";
	    table[25][0] = "Montana";	    table[25][1] = "Helena";
	    table[26][0] = "Nebraska";	  	table[26][1] = "Lincoln"; 
	    table[27][0] = "Nevada";	    table[27][1] = "Carson City"; 
		table[28][0] = "New Hampshire"; table[28][1] = "Concord";
	    table[29][0] = "New Jersey";	table[29][1] = "Trenton";	
	    table[30][0] = "New Mexico";	table[30][1] = "Santa Fe";  
        table[31][0] = "New York"; 	    table[31][1] = "Albany";
		table[32][0] = "North Carolina";table[32][1] = "Raleigh";
	    table[33][0] = "North Dakota";	table[33][1] = "Bismarck";
		table[34][0] = "Ohio";	        table[34][1] = "Columbus";
	    table[35][0] = "Oklahoma";	    table[35][1] = "Oklahoma City";
	    table[36][0] = "Oregon";	    table[36][1] = "Salem";  
	    table[37][0] = "Pennsylvania"; 	table[37][1] = "Harrisburg";
	    table[38][0] = "Rhode Island";  table[38][1] = "Providence";
	    table[39][0] = "South Carolina";table[39][1] = "Columbia"; 
	    table[40][0] = "South Dakota";	table[40][1] = "Pierre";
		table[41][0] = "Tennessee";	    table[41][1] = "Nashville";
	  	table[42][0] = "Texas";  	    table[42][1] = "Austin";
   	    table[43][0] = "Utah";	        table[43][1] = "Salt Lake City"; 
	    table[44][0] = "Vermont";       table[44][1] = "Montpelier";
	    table[45][0] = "Virginia"; 	    table[45][1] = "Richmond";
	    table[46][0] = "Washington";	table[46][1] = "Olympia";
		table[47][0] = "West Virginia";	table[47][1] = "Charleston";
	    table[48][0] = "Wisconsin";	    table[48][1] = "Madison";
  	    table[49][0] = "Wyoming";	    table[49][1] = "Cheyenne";
        
	    return table; 
    }
}
 
