import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		connect4 newGame = new connect4 (4);
		//connect4.printBoard();
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter 1 for player first or enter 0 for AI first: ");
		int firstHand = input.nextInt();
		
		do {
			if(firstHand == 1)
			{
				 while(!newGame.isFinish()) {
					 newGame.getMove();						
					 newGame.checkGameOver();						
					 newGame.makemove();											
					 newGame.checkGameOver();
				 }				
			}
			else if(firstHand ==  0)
			{
				while(!newGame.isFinish()) {
					newGame.makemove();
					newGame.checkGameOver();
					
					if(newGame.isFinish())
					{
						System.out.print("Game Over");
						break;
					}
					
					newGame.getMove();
					newGame.checkGameOver();
				}			
			}
				
		} while(newGame.isFinish());
		
    }	
	
}
