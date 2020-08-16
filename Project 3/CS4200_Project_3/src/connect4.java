import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class connect4 {
	
	static int [][] board =  { {0,0,0,0,0,0,0,0},
		                       {0,0,0,0,0,0,0,0},
		                       {0,0,0,0,0,0,0,0},
		                       {0,0,0,0,0,0,0,0},
		                       {0,0,0,0,0,0,0,0},
		                       {0,0,0,0,0,0,0,0},
		                       {0,0,0,0,0,0,0,0},
		                       {0,0,0,0,0,0,0,0} };
	static String abc = "ABCDEFGH";
	int maxdepth;
	static boolean finish = false;
	static ArrayList<String> playerMove = new ArrayList<String>();
	static ArrayList<String> aIMove = new ArrayList<String>();
	static long end;
	static long start;
	
	public connect4(int maxdepth) {
		this.maxdepth = maxdepth;
	}
	
	//Get user input 
	public void getMove()
	{	
		int i = 0;
		int j = 0;
		String input;
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		
		//Convert string to 2 integers
		do {
			System.out.print("Please Enter your move: " );

		    input = keyboard.nextLine();
		    
		    if(input.length() > 1) {
		    	j = Character.getNumericValue(input.charAt(1));  
		    }
		    
		    switch (Character.toLowerCase(input.charAt(0))) {
		    
		    case 'a': 
		    	i = 0;      	    	      
		    	break;
		    case 'b':
		    	i = 1;    
		    	break;
		    case 'c': 
		    	i = 2;      	    	      
		    	break;
		    case 'd':
		    	i = 3;    
		    	break;
		    case 'e': 
		    	i = 4;      	    	      
		    	break;
		    case 'f':
		    	i = 5;    
		    	break;
		    case 'g': 
		    	i = 6;      	    	      
		    	break;
		    case 'h':
		    	i = 7;    
		    	break;
		    }
		    
		    if(input.length() > 2 && input.length() < 1) {
		    	System.out.println("Wrong input  " );
		    }
		  
		    if(i > 7 || j > 7) {
		    	System.out.println("Out of bound  " );
		    }
		    else if(!isMoveValid(i, j-1, input.length())) {
		    	System.out.println("Please choose other location  " );
		    }
		    
		} while (! isMoveValid(i, j-1, input.length() )  );
	    
		//Store player input
	    playerMove.add(input);

	    board[i][j-1] = 2;
	}
	
	//Check if user input is valid
	public boolean isMoveValid(int i, int j, int k)
	{
		if(i > 7 || j > 7 || i < 0 || j < 0 || k > 2 || k < 1) {
			return false;
		}
		if(board[i][j] == 0 ) {
			return true;
		}
		else
			return false;
	}
	
	
	//Time cut off in 5 second
	public static boolean cutOff( ) {
		if (System.currentTimeMillis() > end) {
			return true;
		}
		//System.out.println("No cut off " );
		return false;
	}

	//AI move
	public void makemove()
	{
		String move = null;
	    int best = Integer.MIN_VALUE; 
	    int depth = maxdepth; 
	    int score; 
	    int mi = 0;
	    int mj = 0;
	    
	    //Timer to set cut off at 5 second
	    start = System.currentTimeMillis();
	    end = start + (5*1000); // 5 seconds * 1000 ms/se
	    	    
	    for (int i = 0; i < 8; i++)
	    {
	        for (int j = 0; j < 8; j++)
	        {
	            if (board[i][j] == 0)
	            {
	            	board[i][j] = 1; // make move on board
	            	//Change this to alpha beta
	                score = min(depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
	                
	                if (score > best) { mi = i; mj = j; best = score; }
	                board[i][j] = 0; // undo move
	            }
	        }
	    }
	    
	    switch (mi) {
	    
	    case 0: 
	    	move = "a" + (mj+1);      	    	      
	    	break;
	    case 1:
	    	move = "b" + (mj+1);  
	    	break;
	    case 2: 
	    	move = "c" + (mj+1);      	    	      
	    	break;
	    case 3:
	    	move = "d" + (mj+1);    
	    	break;
	    case 4: 
	    	move = "e" + (mj+1);      	    	      
	    	break;
	    case 5:
	    	move = "f" + (mj+1);    
	    	break;
	    case 6: 
	    	move = "g" + (mj+1);      	    	      
	    	break;
	    case 7:
	    	move = "h" + (mj+1);    
	    	break;
	  }
	        	    
	    //Store AI move
	    aIMove.add(move);
	    board[mi][mj] = 1;
	}
	
	//This mixmax algorithm is a modified version from the professor's example C++ code
	public static int min(int depth, int alpha, int beta)
	{
	    int best = Integer.MAX_VALUE;
	    int score = 0;
	    
	    if (check4Winner() != 0) 
	    	return (check4Winner());
	    
	    if (depth == 0 || cutOff( ) ) 
	    	return (evaluate());
	    
	    for (int i = 0; i < 8; i++)
	    {
	        for (int j = 0; j < 8; j++)
	        {
	            if (board[i][j] == 0)
	            {
	            	board[i][j] = 2; // make move on board
	                score = Math.min(best, max(depth - 1, alpha, beta));

	                if (score < best) {
	                	best = score;
	                }
	                board[i][j] = 0; // undo move
	                
	                if(score <= alpha ) {
	                	return(score);
	                }
	                beta = Math.max(beta, best);
	            }
	        }
	    }
	    return(best);
	}
	
	//This mixmax algorithm is a modified version from the professor's example C++ code
	static int max(int depth, int alpha, int beta)
	{
	    int best = Integer.MIN_VALUE;
	    int score = 0;
	    if (check4Winner() != 0) 
	    	return (check4Winner());
	    
	    if (depth == 0 || cutOff( ) ) 
	    	return (evaluate());
	    
	    for (int i = 0; i < 8; i++)
	    {
	        for (int j = 0; j < 8; j++)
	        {
	            if (board[i][j] == 0)
	            {
	            	board[i][j] = 1; // make move on board
	                score = Math.max(best, min(depth - 1, alpha, beta));
	                                           	                
	                if (score > best) 
	                	best = score;
	                board[i][j] = 0; // undo move
	                
	                if(score >= beta ) {
	                	return(score);
	                }
	                alpha = Math.max(alpha, best);
	            }
	        }
	    }
	    return(best);
	}
	
	//A simple method to print out 2d array
	public static void printBoard()
	{	
		int p = playerMove.size();
		int a = aIMove.size();
		
		System.out.print("   1  2  3  4  5  6  7  8   ");
		System.out.println(" Player vs. Oponent ");
		
		for(int i =0; i < 8; i++) {
			for(int j =0; j < 8; j++) {
				if(board[i][j] == 0) {
					if(j == 0) {					
						System.out.print( abc.charAt(i) +"  - ");					
					}
					else {
						System.out.print(" - ");					
					}					
				}
				if(board[i][j] == 1) {
					if(j == 0) {					
						System.out.print( abc.charAt(i) +"  X ");
					}
					else
						System.out.print(" X ");
				}
				if(board[i][j] == 2) {
					if(j == 0) {					
						System.out.print( abc.charAt(i) +"  O ");
					}
					else
						System.out.print(" O ");
				}
				
				if(j == 7 && p > 0) 
				{													
					System.out.print( "      " +  (i+1)  +". " +playerMove.get(i));						
					p --;
					if(a > 0)
					{
						System.out.print( "  " + aIMove.get(i));
						a--;
					}
				}
			}
			System.out.println();
		}
		
		for(int i =0; i < p; i++) 
		{			
			System.out.print( "                                " +  (i+9)  +". " +playerMove.get(i +8));									
			p --;
							
			if(a > 0){					
				System.out.print( "  " + aIMove.get(i +8));				
				a--;	
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}
	
	// Check who is the winner
	public void checkGameOver()
	{		
		printBoard();	
		if(check4Winner() == -5000) {
			System.out.println("Your win");
			finish = true;
		}
		if(check4Winner() ==  5000) {
			System.out.println("Computer win");
			finish = true;
		}
		if(check4Winner() == 1) {
			System.out.println("Draw");
			finish = true;
		}
	}
	
	//Check if the game is finish
	public boolean isFinish() {
		return finish;
	}
	
	//Check is there is already a 4 in a line on board
	public static int check4Winner() {
		
		// horizontalCheck Human
	    for (int j = 0; j < 8-3 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 2 && board[i][j+1] == 2 && board[i][j+2] == 2 && board[i][j+3] == 2){
	                return -5000;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i<8-3 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 2 && board[i+1][j] == 2 && board[i+2][j] == 2 && board[i+3][j] == 2){
	                return -5000;
	            }           
	        }
	    }
	    
	    // horizontalCheck 
	    for (int j = 0; j<8-3 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 1 && board[i][j+1] == 1 && board[i][j+2] == 1 && board[i][j+3] == 1){
	                return 5000;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i<8-3 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 1 && board[i+1][j] == 1 && board[i+2][j] == 1 && board[i+3][j] == 1){
	                return 5000;
	            }           
	        }
	    }
	    
	    for(int i =0; i < 8; i++) {
			for(int j =0; j < 8; j++) {
				if(board[i][j] == 0) {
					return 0;					
				}

			}
		}
		
		return 1; //draw	
		
	}
	
	//My AI strategy is very simple, I am trying to let the AI know that blocking human's move is the priority task to do.
	//If human got connected move on board the AI should start blocking it. Even human only got 1 move on the table, the AI 
	//should start blocking it too. (Human is negative score and AI is positive score) Still has bug
	public static int evaluate()
	{	
		int score = 0;
		int pcSocore = 0;

        /////////////////////////////////////////////////////////////////////////////////////////////////
		// XOOX horizontalCheck  
	    for (int j = 0; j < 8-3 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 1 && board[i][j+1] == 2 && board[i][j+2] == 2 && board[i][j+3] == 1){
	            	score -=200;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i<8-3 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 1 && board[i+1][j] == 2 && board[i+2][j] == 2 && board[i+3][j] == 1){
	            	score -=200;
	            }           
	        }
	    }
	    
	    // horizontalCheck 
	    for (int j = 0; j<8-3 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 2 && board[i][j+1] == 1 && board[i][j+2] == 1 && board[i][j+3] == 2){
	            	pcSocore += 200;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i<8-3 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 2 && board[i+1][j] == 1 && board[i+2][j] == 1 && board[i+3][j] == 2){
	            	pcSocore += 200;
	            }           
	        }
	    }
	    
		/////////////////////////////////////////////////////////////////////////////////////////////////
		//(3 in a line ) (XOO) Huamn
		for (int j = 0; j < 8-2 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 1 && board[i][j+1] == 2 && board[i][j+2] == 2 ){
	            	pcSocore += 100 ;
	            }           
	        }
	    }
	    // verticalCheck (3 in a line)
	    for (int i = 0; i<8-2 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 1 && board[i+1][j] == 2 && board[i+2][j] == 2 ){
	            	pcSocore += 100;
	            }           
	        }
	    }
	    
		//(3 in a line ) (OOX)
		for (int j = 0; j < 8-2 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 2 && board[i][j+1] == 2 && board[i][j+2] == 1 ){
	            	pcSocore += 100 ;
	            }           
	        }
	    }
	    // verticalCheck (3 in a line)
	    for (int i = 0; i<8-2 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 2 && board[i+1][j] == 2 && board[i+2][j] == 1 ){
	            	pcSocore += 100;
	            }           
	        }
	    }
	    
        /////////////////////////////////////////////////////////////////////////////////////////////////
	    // (OXX) horizontalCheck AI 
	    for (int j = 0; j<8-2 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 1 && board[i][j+1] == 2 && board[i][j+2] == 2 ){
	            	score -= 100;
	            }           
	        }
	    }
	    // verticalCheck (3 in a line)
	    for (int i = 0; i<8-2 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 1 && board[i+1][j] == 2 && board[i+2][j] == 2 ){
	            	score -= 100;
	            }           
	        }
	    } 
	    //(XXO)
	    // horizontalCheck AI (3 in a line)
	    for (int j = 0; j<8-2 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 2 && board[i][j+1] == 2 && board[i][j+2] == 1 ){
	            	score -= 100;
	            }           
	        }
	    }
	    // verticalCheck (3 in a line)
	    for (int i = 0; i<8-2 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 2 && board[i+1][j] == 2 && board[i+2][j] == 1 ){
	            	score -= 100;
	            }           
	        }
	    }
	    
	    /////////////////////////////////////////////////////////////////////////////////////////////////
	    //(2 in a line ) horizontalCheck (XO) 
	    for (int j = 0; j < 8-1 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 1 && board[i][j+1] == 2 ){
	            	score -= 10 ;
	            }           
	        }
	    }
	    
	    // verticalCheck AI (XO)
	    for (int i = 0; i<8-1 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 1 && board[i+1][j] == 2 ){
	            	pcSocore += 10;
	            }           
	        }
	    }
	    
	    // verticalCheck  (OX)
	    for (int i = 0; i<8-1 ; i++ ){
	        for (int j = 0; j<8; j++){
	            if (board[i][j] == 2 && board[i+1][j] == 1 ){
	            	pcSocore += 10;
	            }           
	        }
	    }

	    
	    // horizontalCheck (OX)
	    for (int j = 0; j<8-1 ; j++ ){
	        for (int i = 0; i<8; i++){
	            if (board[i][j] == 2 && board[i][j+1] == 1 ){
	            	score -= 10;
	            }           
	        }
	    }
	    
		return pcSocore+score;
	}

}
