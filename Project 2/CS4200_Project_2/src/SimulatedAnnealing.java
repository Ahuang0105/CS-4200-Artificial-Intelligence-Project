import java.util.Random;

public class SimulatedAnnealing {
	
	static long nano_startTime;
	static long nano_endTime;
	static long finishTime;
	static int cost = 0;
	
	public boolean simulatedAnnealing (Node myBoard, int token) throws Exception {
	   	
    	//Set up my current node
    	Node current = myBoard;
    	Node neighbor;  	
    	Random rand = new Random();
    	//Calculate use time
        nano_startTime = System.nanoTime();
    	
    	double temperature = 2500; 
    	double probability;
    	double deltaE;
    	cost = 0;
    	
        for(int i = 0; i < 2500; i++)
        {
        	//let temperature gradually decrease
        	temperature --;
        	       	  
        	//If temperature is down to zero return result
        	if(temperature == 0)
        	{      
        		//Only when token is 1 then display board
        		if(current.isSolved()) {
        			if(token == 1) {
        				System.out.println("Puzzle solved ");
                		current.printBoard();
        			}
        			nano_endTime = System.nanoTime();
            		finishTime = (nano_endTime - nano_startTime);
            		return true;
        		}
        			
        		else {
        			if(token == 1) {
        				System.out.println("Puzzle not solved ");
                		current.printBoard();
        			}
        			nano_endTime = System.nanoTime();
            		finishTime = (nano_endTime - nano_startTime);
        			return false;
        		}
        	}
        	
        	//randomly pick a successor
        	neighbor = current.getRandomSuccessor(rand.nextInt(8));
        	deltaE = current.totalHit() - neighbor.totalHit();
        	probability = Math.exp(deltaE / temperature);
        	//Increase cost
        	cost++;
        	       	
        	if(deltaE > 0) {     		
        		current = neighbor;
        	} 
        	
        	//When probability is greater than 0.9999 the success rate increase
        	//If change 0.9999 to lower number the success rate will decrease
        	else if(probability > 0.9999) {
        		current = neighbor;
        	}
        	
        	//By adding this statement the total cost decrease from 9999 to around 1000
        	//if(current.isSolved()) {
    			//if(token == 1) {
    				//System.out.println("Puzzle solved ");
            		//current.printBoard();
    			//}
    			//nano_endTime = System.nanoTime();
        		//finishTime = (nano_endTime - nano_startTime);
        		//return true;
    		//}
        	       	    	    
        }
        nano_endTime = System.nanoTime();
		finishTime = (nano_endTime - nano_startTime);
		return false;              
    }
	
	public long getTime() {
    	return finishTime;
    }
	public int getCost() {
        return cost;
    }

}
