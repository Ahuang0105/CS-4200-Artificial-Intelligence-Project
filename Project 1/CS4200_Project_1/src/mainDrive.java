import java.util.Scanner;

public class mainDrive {
	
	static int userInput;
	static puzzleSolver mySolver = new puzzleSolver();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
				
		while(userInput != 3)
		{		
			System.out.println("CS 4200 Project 1");
			System.out.println("Select: ");
			System.out.println("[1] Random Puzzle");
			System.out.println("[2] Manual input Puzzle");
			System.out.println("[3] Exit\n");
			System.out.print(">> ");
			userInput = input.nextInt();
							
			if(userInput == 1)		
			{
				randomPuzzle();
			}
			else if(userInput == 2)
			{
				manualPuzzle();		
			}
			else if(userInput == 3)
			{
				System.out.println("Program end");
				System.exit(0);
			}
			else
			{
				System.out.println("Wrong selection please enter number 1, 2, or 3");
			}
			
		}
	}
	
	//Manually enter puzzle
	private static void manualPuzzle() throws Exception
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);	
		int[] userPuzzle = new int[9];
		
		while(userInput != 0)
		{					
			System.out.println( );
			System.out.println("Please enter numbers from 1 to 8, and 0 as the empty tile.");	
			
		
			for(int i = 0; i < 9; i++)		
			{			
				System.out.print("Square " + i +": ");
					
				userInput = input.nextInt();			
				userPuzzle[i] = userInput;					
			}
		
			System.out.println(" ");
			
			//Open two puzzle to test
			Node puzzle = new Node(userPuzzle, 1);	
			Node puzzle2 = new Node(userPuzzle, 2);
			
			//Display input
			System.out.println("The puzzle you enter is ");		
			System.out.println( puzzle.toString() +"\n" );
			
			//Check if the puzzle is solvable
			if( !puzzle.isSolvable() )		
			{			
				System.out.println("Sorry the puzzle is not solvable");		
			}		
			else	
			{			
				System.out.println("-------------------------------");	
				System.out.println("Solving with Manhattan distance");		
				mySolver.eightPuzzle(puzzle);					
				System.out.println("-------------------------------");			
				System.out.println("Solving with Hamming distance");			
				mySolver.eightPuzzle(puzzle2);		
				System.out.println("-------------------------------");
			}
			
			
			System.out.println("Enter [0] return to main menu ");
			System.out.println("Enter [1] to [9] to continue use manual input \n");
			System.out.print(">> ");
			userInput = input.nextInt();	
		}	
	}
	
	//Create random puzzle
	@SuppressWarnings({ "unused", "resource" })
	private static void randomPuzzle() throws Exception
	{
		Scanner input = new Scanner(System.in);	
		int[] userPuzzle = new int[9];
		
		while(userInput != 0)
		{			
				
			//Create puzzle to test
			Node puzzle = new Node();	
			
			//Display input
			System.out.println("The random puzzle is ");		
			System.out.println( puzzle.toString() +"\n" );
			
			puzzle.setType(1);	
			System.out.println("-------------------------------");	
			System.out.println("Solving with Manhattan distance");		
			mySolver.eightPuzzle(puzzle);					
			System.out.println("-------------------------------");
			puzzle.setType(2);
			System.out.println("Solving with Hamming distance");			
			mySolver.eightPuzzle(puzzle);		
			System.out.println("-------------------------------");		
			
			System.out.println("Enter [0] return to main menu ");
			System.out.println("Enter [1] to [9] to continue test another random puzzle \n");
			System.out.print(">> ");
			userInput = input.nextInt();	
		}	
	}

}
