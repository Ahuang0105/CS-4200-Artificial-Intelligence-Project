import java.util.ArrayList;
import java.util.Arrays;

public class Node implements Comparable<Node> {
	
    private final int[] goal = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private final Node originalPuzzle;
    private int[] puzzle = new int[9];
    private int emptyTileIndex;
    private int estimatCost;
    private int cost;
    private int type;
    
    //For manual puzzle
    public Node(int[] puzzle, int type) throws Exception{
    	cost = 0;
        this.puzzle = puzzle;
        this.type = type;

        if (type == 1)
        {
        	estimatCost = manhattan();
        } else if (type == 2)
        {
        	estimatCost = hamming();
        }else {
            throw new Exception("Not a valid heuristic.");
        }
        this.emptyTileIndex = emptyTileSearch();
        originalPuzzle = null;
    }
    
    //Create random puzzle
    public Node( )
    {    
    	do 
    	{      
    		ArrayList<Integer> numberList = new ArrayList<Integer>( Arrays.asList( 0, 1, 2, 3, 4, 5, 6, 7, 8 ) );
            for (int i = 0; i < puzzle.length; ++i)
            {
            	puzzle[i] = numberList.remove((int)(Math.random() * numberList.size()));
            }
        }
        while(!isSolvable());
    	         
        this.emptyTileIndex = emptyTileSearch();    
        originalPuzzle = null;
        cost = 0;   
    }

    //Create child
    public Node(Node originalPuzzle, int newEmptySpace, int type)
    {
    	//Copy the original puzzle
        this.puzzle = originalPuzzle.puzzle.clone();
        this.originalPuzzle = originalPuzzle;
        this.emptyTileIndex = newEmptySpace;     
        this.type = type;
        
        //Select type
        if (type == 1)
        {
        	estimatCost = manhattan();
        }
        else if (type == 2)
        {
        	estimatCost = hamming();
        }
        //Change original empty tile with new empty tile location number
        puzzle[originalPuzzle.emptyTileIndex] = puzzle[newEmptySpace];
        //Set new empty space to 0 (Empty tile)
        puzzle[newEmptySpace] = 0;    
        this.cost = originalPuzzle.cost + 1;
        
    }
 
    //Search the index of the empty tile
    private int emptyTileSearch ()
    {
    	for(int i = 0; i < puzzle.length; i++) 
    	{
    		if(puzzle[i] == 0)
    		{
    			return i;
    		}
    	}
		return 0;
    }

    //Calculate how many tile are misplaced
    private int hamming()
    {
        int misplaced = 0;
        
        for (int i = 0; i < puzzle.length; i++)
        {
            if (puzzle[i] != i){
            	misplaced++;
            }
        }

        return misplaced;
    }

    //Calculate total steps for each number to reach goal state
    private int manhattan()
    {
        int sum  = 0;
        
        for (int i = 0; i < puzzle.length; i++)
        {
            int position = puzzle[i];
            sum  += (Math.abs(position % 3 - i % 3) + Math.abs(position / 3 - i / 3));
        }
        return sum ;
    }

    //Check if the puzzle is solved
    public boolean isSolved()
    {
    	return (Arrays.equals(puzzle, goal));
    }

    //Check if the puzzle is solvable
    public boolean isSolvable()
    {
        int inverCount = 0;
        
        for (int i = 0; i < puzzle.length - 1; i++)
        {
            for (int j = i + 1; j < puzzle.length; j++)
            {
                if (puzzle[i] > puzzle[j] && puzzle[i] != 0 && puzzle[j] != 0)
                {
                	inverCount++;
                }
            }
        }

        return inverCount %2 == 0;
    }

    //Create child
    public ArrayList<Node> getChild()
    {
        ArrayList<Node> Child = new ArrayList<>();
        
        int emptyRow = emptyTileIndex / 3;
        int emptyCol = emptyTileIndex % 3;

        for (int i = 0; i < puzzle.length; i++)
        {
            int currRow = i / 3;
            int currCol = i % 3;
            
            //Calculate all location near 0, then create all possible child.
            //If the empty tile is at 0,2,6,8 then there are 2 moving positions (2 child)
            //If the empty tile is at 1,3,5,7 then there are  3 moving positions (3 child)
            //If empty tile is at 4 the there are 4 moving positions (4 child)
            if ((Math.abs(currRow - emptyRow) + Math.abs(currCol - emptyCol)) == 1)
            {
            	Node puzzle = new Node(this, i, type);
                Child.add(puzzle);
            }
        }

        return Child;
    }
    
    //Set type
    public void setType(int type) 
    {
    	this.type = type;
    }
    
    //Get cost
    public int getCost()
    {
    	return cost;
    }
    
    //Get total cost
    public int getTotalCost(){
        return this.cost + this.estimatCost;
    }
    
    //Return type with string
    public String getType() {
		if(type == 1)
		{
			return "Manhattan distance";
		}
		else
		{
			return "Hamming distance";
		}
    }
    
    //To print result steps
    public void printStates()
    {
    	
        if (originalPuzzle != null)
        {
        	originalPuzzle.printStates();
        }
        
        System.out.println();
        
        if( cost == 0)
        {
        	System.out.println("Initial ");
        } 
        else
        {
        	System.out.println("Step " + cost);
        }
        System.out.println(this.toString());
    }
    
    // print 1d array to 2d format
    public String toString()
    {
    	String result = "";
        
        for (int i = 0; i < puzzle.length; i++)
        {
        	result += puzzle[i] + "   ";
        	
            if ((i+1) % 3 == 0 && i != 0 & i != 8)
            {
            	result += "\n";
            }
        }
        return "\n" + result;
    }
   
    //Custom compare method
    public int compareTo(Node puzzle){
        if (this.getTotalCost() < puzzle.getTotalCost()){
            return -1;
        }

        if (this.getTotalCost() > puzzle.getTotalCost()){
            return 1;
        }

        return 0;
    }

    // hashCode() and equals() both generate by Eclipse IDE
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(puzzle);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (!Arrays.equals(puzzle, other.puzzle))
			return false;
		return true;
	}

}