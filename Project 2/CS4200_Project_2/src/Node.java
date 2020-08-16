import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Node implements Comparable<Node> {
	
	//8 queen board is store in 1d array, and the number represents queen location in column
	int [] myBoard = new int[8];
	double cost = 0;
	boolean solve = false;
	Random rand = new Random();
	
	//Create random 8 queen board		
	public Node ()		
	{						
		for(int i = 0; i < 8; i++) {
			myBoard[i] = rand.nextInt(8)+1;
		}		
		cost = totalHit();
	}
	
	//Copy 8 queen board
	public Node (int[] myBoard)		
	{						
		this.myBoard = myBoard;		
		cost = totalHit();
	}
	
	//Create child and set each cost to compare later
	public Node (Node initialBoard, int index, int currentNumber) {	
		this.myBoard = initialBoard.myBoard.clone();
		myBoard[index] = currentNumber;
		cost = totalHit();
	}
	
	//Check how many queen hit
	public int totalHit() 
	{
		int totalHit = 0;
		
		for (int i = 0; i < myBoard.length; i++) {
            for (int j = i + 1; j < myBoard.length; j++) {
                int first = myBoard[i];
                int second = myBoard[j];

                // Check if any queen hit in the row
                if (first == second) {
                	totalHit++;
                	
                } 
                // Check diagonal rows
                else 
                {
                   int diagCol = Math.abs(i - j);
                   int diagRow = Math.abs(first - second);
                   if (diagCol == diagRow) 
                   {
                    totalHit++;
                   }
                }
            }
        }

        return totalHit;
	}
	
	//Check if the 8 queen is solved
	public boolean isSolved()
	{
		if(cost == 0)
		{		
			return true;
		}
		else 
			return false;		
	}
	
	//Get cost
	public double getCost ()
	{
		return cost;
	}
	
	//Create all 56 possible child node and return the best cost one
    public Node getChild( )
    {
    	//Use array list to hold all node
        ArrayList<Node> Child = new ArrayList<>();
        //Create all 56 possible child and make sure no repeat location
        for (int i = 0; i < myBoard.length; i++) {
        	for (int j = 0; j < myBoard.length; j++ ) {
        		//Make sure we don't create board with repeat location
        		if (myBoard[j] != i+1) {
                	Node possibleLocation = new Node(this, j, i+1);
                    Child.add(possibleLocation);
                }
        	}            
        }
        
        //System.out.println("Total child nodes : " + Child.size());
     
        //Use custom compare method to return best cost node           
        return Collections.max(Child);     
    }
    
    //Move a random node
    public Node getRandomSuccessor(int index)
    {
    	int[] successor = myBoard.clone();
    	int next = rand.nextInt(8)+1;
    	
    	//Make sure the new random number is not the same as the one store in array successor
    	while(next == successor[index] )
    	{
    		next = rand.nextInt(8)+1;
    	}
    	
    	successor[index] = next;
    	Node newNode = new Node(successor);
    	
    	return newNode;
    }
	
	//Print board
	public void printBoard()
	{
		for(int i = 8; i > 0; i--)
		{
			for(int j = 0; j < 8; j++)
			{
				if( i == myBoard[j]) {
					if(j == 0) {
						System.out.print(i +"  ");
						System.out.print("Q ");
					}
					else {
						System.out.print("Q ");
					}
				
				}
				else {
					if(j == 0) {
						System.out.print(i +"  ");
						System.out.print("- ");
					}
					else {
						System.out.print("- ");
					}
				}
			}
			System.out.println(" ");
			if(i == 1) {
				System.out.println("   1 2 3 4 5 6 7 8");
			}
		}
		
		System.out.println(" ");
	}
	
	//Custom compare method
	public int compareTo(Node Board){
        if (this.getCost() > Board.getCost()){
            return -1;
        }

        if (this.getCost() < Board.getCost()){
            return 1;
        }

        return 0;
    }

}
