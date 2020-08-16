import java.util.Scanner;

public class mainDrive {

	static int myBoard[] = new int[8];
	static float count = 0;
	static float total = 0;
	static long RunTime = 0;
	static long totalRunTime = 0;
	static int cost = 0;
	static int totalCost = 0;
	static int userInput = 0;

	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		while(userInput != 3)
		{
				
			System.out.println("CS 4200 Project 2");		
			System.out.println("Select: ");		
			System.out.println("[1] Random 8 queen Puzzle");		
			System.out.println("[2] Random 8 queen Puzzle with display");		
			System.out.println("[3] Exit\n");		
			System.out.print(">> ");
			userInput = input.nextInt();
			
			if(userInput == 1)		
			{		
				System.out.println(" ");
				System.out.println("Please enter how many time you want to test the algorithm");
				System.out.print(">> ");
				userInput = input.nextInt();
				hillClimb(userInput, 0);
				simulatedAnnealing(userInput, 0);
			}
			else if(userInput == 2)
			{
				hillClimb(3, 1);
				simulatedAnnealing(3, 1);		
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
	
	public static void hillClimb (int n, int token) throws Exception
	{
		for(int i = 0; i< n; i++)
		{		
			//Create random 8 queen board		
			Node eightQueen = new Node( );	
			hillClimbing hillClimb = new hillClimbing();
			
			if(token == 1)
			{
				System.out.println("**************************************************");
				System.out.println("* \t\t 8-queen puzzle \t\t *");
				System.out.println("*\t Steepest-ascent hill climbing algorithm *");
				System.out.println("**************************************************");
				//Print initial board	
				System.out.println("Initial board");		
				eightQueen.printBoard();
			}
										
			if (hillClimb.hillClimbingSolver(eightQueen, token))
			{				
				RunTime += hillClimb.getTime();
				cost += hillClimb.getCost();
				count++;
			}	
			else
			{
				RunTime += hillClimb.getTime();
				cost += hillClimb.getCost();
			}
		}
		
		if(token == 0) {
			System.out.println(" ");
			total = ((count/n)*100);
			totalRunTime = RunTime/n;
			totalCost  = cost/n;
			String.format("%.2f", total);
			System.out.println("**************************************************");
			System.out.println("* \t\t 8-queen puzzle \t\t *");
			System.out.println("*\t Steepest-ascent hill climbing algorithm *");
			System.out.println("* \t\tTotal test " +n +" time" + "\t\t *" );
			System.out.println("* \t\tTotal percent " + total +" %" + "\t\t *");
			System.out.println("* \t\tAverage run time is " + totalRunTime +" ns" + "\t *");
			System.out.println("* \t\tAverage cost per run is " + totalCost + "\t *");
			System.out.println("**************************************************");			
		}
		totalRunTime = 0;
		totalCost = 0;
		RunTime = 0;
		count = 0;
		total = 0;
		cost = 0;		
	}
	
	public static void simulatedAnnealing (int n, int token) throws Exception
	{
		for(int i = 0; i< n; i++)
		{		
			//Create random 8 queen board		
			Node eightQueen = new Node( );	
			SimulatedAnnealing tempControl = new SimulatedAnnealing();
			
			if(token == 1)
			{
				System.out.println("**************************************************");
				System.out.println("* \t\t 8-queen puzzle \t\t *");
				System.out.println("*\t Simulated Annealing algorithm " + "\t\t *");
				System.out.println("**************************************************");
				//Print initial board	
				System.out.println("Initial board");		
				eightQueen.printBoard();
			}
			
			if (tempControl.simulatedAnnealing(eightQueen, token))
			{	
				RunTime += tempControl.getTime();
				cost += tempControl.getCost();
				count++;
			}
			else
			{
				RunTime += tempControl.getTime();
				cost += tempControl.getCost();
			}
		}
		
		if(token == 0) {
			System.out.println(" ");
			total = ((count/n)*100);
			totalRunTime = RunTime/n;
			totalCost  = cost/n;
			String.format("%.2f", total);
			System.out.println("**************************************************");
			System.out.println("* \t\t 8-queen puzzle \t\t *");
			System.out.println("*\t Simulated Annealing algorithm " + "\t\t *");
			System.out.println("* \t\tTotal test " +n +" time" + "\t\t *" );
			System.out.println("* \t\tTotal percent " + total +" %" + "\t\t *");
			System.out.println("* \t\tAverage run time is " + totalRunTime +" ns" + "\t *");
			System.out.println("* \t\tAverage cost per run is " + totalCost + "\t *");
			System.out.println("**************************************************");
		}
		
		totalRunTime = 0;
		totalCost = 0;
		RunTime = 0;
		count = 0;
		total = 0;
		cost = 0;
		
	}
}
	


