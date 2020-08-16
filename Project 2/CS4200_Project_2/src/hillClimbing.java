public class hillClimbing {
	
	static long nano_startTime;
	static long nano_endTime;
	static long finishTime;
	static int cost;

    public boolean hillClimbingSolver (Node myBoard, int token) throws Exception {
   	
    	//Set up my current node
    	Node current = myBoard;
    	//When myBoard call getChild it will generate 56 child and return the lowest cost one
    	Node neighbor = myBoard.getChild();
    	cost += 56;
    	//Calculate use time
        nano_startTime = System.nanoTime();
        cost = 0;
    	
    	int i = 0; 	

    	//The loop will stop if neighbor cost is greater than current cost
        while(true)
        {     	      	
        	//If neighbor cost is greater than current exit the loop
        	if(current.getCost() <= neighbor.getCost())
        	{   		
        		if(token == 1) {
    				System.out.println("Puzzle not solved ");
            		current.printBoard();
    			}
        		nano_endTime = System.nanoTime();
        		finishTime = (nano_endTime - nano_startTime);
        		return false;
        	}
        	else  {
        		//move to best cost neighbor node
        		current = neighbor;
        		neighbor = current.getChild();
        		cost += 56;
        	}
        	//Check if current node is solved
        	if(current.isSolved())
        	{       		
        		if(token == 1) {
    				System.out.println("Puzzle solved ");
            		current.printBoard();
    			}
        		nano_endTime = System.nanoTime();
        		finishTime = (nano_endTime - nano_startTime);
        		return true;
        	}
     	
        	i++;
        	//Reset index
        	if( i == 8) {
        		i = 0;
        	}
        	    	    
        }                  
    }
    
    public long getTime() {
    	return finishTime;
    }
    
    public int getCost() {
        return cost;
    }

}
