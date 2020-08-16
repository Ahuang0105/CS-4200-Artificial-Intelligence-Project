import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class puzzleSolver {
	
	private int totalNode;
	static long nano_startTime;
	static long nano_endTime;

	//A* algorithm 8 puzzle solver
    public void eightPuzzle (Node puzzle) throws Exception {
    	
    	totalNode = 0;
    	//Use priority to store node
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        //Add root node to priority
        frontier.add(puzzle);
        //Calculate use time
        nano_startTime = System.nanoTime();  
        //Use Hash set to track visited node
    	HashSet<Node> visitedNode = new HashSet<>();
    	//Use Hash map to remove duplicated node
    	HashMap<Integer, Integer> vistedMap = new HashMap<>();        
        
        while (!frontier.isEmpty()) 
        {
        	//Remove the lowest cost node and use a temporary node to check it   	        	
        	Node temp = frontier.poll();
            
            //if  the node is in goal state
            if (temp.isSolved()) {
            	
            	nano_endTime = System.nanoTime();

            	//Print all steps and results
            	temp.printStates();          
            	System.out.println(" ");
                System.out.println("| Heuristic function: " + temp.getType() + " | "
                +"Search Cost: " + totalNode + " | " + "Time: " + (nano_endTime - nano_startTime) + " ns |");
                //Use HashMap to remove duplicated node 
                vistedMap.put(temp.getCost(), totalNode);
   
                return;
            }
                        
            //If temporary node is not in goal state then create possible child and add to queue
            ArrayList<Node> child = temp.getChild();
            //Add all child to queue
            for (Node i : child) {
            	//Only add non visited node to the frontier
            	if(!visitedNode.contains(i)) {
                frontier.add(i);
                totalNode++;
            	}
            }     
            //Add removed lowest cost node to visited node list
            visitedNode.add(temp);
        }
        
        System.out.println("Can't solve the puzzle");
        return;        
    }

}
