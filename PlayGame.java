import java.util.*;
/**
 * The playgame class represents the act of playing a chess game.It
 * combines all functionality involved such as pieces and movement of pieces
 * @param none
 * 
 * 
 * @author (Abubakar) 
 * @version (4/11/2016)
 */
public class PlayGame
{// instance variables - replace the example below with your own
       
    private static Scanner input = new Scanner(System.in);
   /**
     * Constructor for objects of class PlayGame
     * @param none
     */
    public PlayGame()
    {
        // initialise instance variables
      
   }
   /**
    * Prints the introduction of the game
    */
   public static void introduction()
   {
       System.out.println("Welocme to this amazing miniature representation of a chess game. The rows and columns are indexed from 0-7.Player1 goes first(the player with the uppercase pieces) Enjoy");
    }
    /**
     * checks if the coordinates inputted are outofbounds 
     */
   public static boolean checkOutofBounds(int x, int y)
   {
       if( x <0 || x> 7 || y <0 || y>7)
       {
           return true;
        }
        return false;
    }
    /**
     * checks if a piece is at the specified location
     */
   private static boolean checkPiece(ChessLocation location, ChessGame game)
   {
        return game.getBoard().isPieceAt(location);
       
    }
   
   public static void quitGame(String turn)
    {
        System.out.println(turn + "looses");
        System.exit(0);
        
    }
    /**
     * checks the owner of the piece being moved
     */
    public static boolean checkOwnerShip(String turn,ChessGame game, ChessLocation location)
    {
        if(game.getBoard().getPieceAt(location).getPlayer().equals(turn))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean move(ChessGame game, String turn)
    {
        System.out.println("Enter the row number of piece you want to move like this 5");
        int x = input.nextInt();
        System.out.println("Enter a column number of piece you want to move like this 5");
        int y = input.nextInt();
        while(checkOutofBounds(x,y))
        {
            System.out.println("out of bounds");
            System.out.println("Enter a row number of piece you want to move");
            x= input.nextInt();
            System.out.println("Enter a column number of piece you want to move");
            y = input.nextInt();
        }
        while(checkPiece(new ChessLocation(x,y),game) == false)
        {
            
            System.out.println("There is no piece at that location! Please check and type your input again");
            System.out.println("Enter a row number of piece you want to move");
            x = input.nextInt();
            System.out.println("Enter a column number of piece you want to move");
            y = input.nextInt(); 
        }
        if(checkOwnerShip(turn,game,new ChessLocation(x,y)) == false)
        {
            System.out.println("This piece does not belong to you");
            return false;
        }
        System.out.println("Enter destination row");
        int new_x = input.nextInt();
        System.out.println("Enter destination column");
        int new_y = input.nextInt();
        while(checkOutofBounds(new_x,new_y))
        {
            System.out.println("out of bounds");
            System.out.println("Enter destination row");
            new_x = input.nextInt();
            System.out.println("Enter destination column");
            new_y = input.nextInt();
        }
        return game.getBoard().getPieceAt(new ChessLocation(x,y)).moveTo(new ChessLocation(new_x,new_y));
        
    }


    /**
    * this method instansiates the whole program
    */
    
    public static void main(String[] args)
    {
        introduction();
       ArrayList<King> c = new ArrayList<>();
       ChessGame game_one = new ChessGame("player1","player2"); // initializes a new instance of ChessGame
       game_one.getBoard().printboard();
       String turn = "player1";
       boolean d;
       while(true)
       {
           c = game_one.checkKings();
           System.out.println("its " + turn + "'s turn");
            System.out.println("Do you want to make a move or quit? if making a move type move if quiting type quit if resetting type reset");
            String second_response = input.nextLine(); // accepts input from the user on wether to quit or not
            
            if(second_response.equals("reset"))
            {
                System.out.println("Resetting");
                game_one = new ChessGame("player1","player2");
                turn = "player1";
            }
            else if(second_response.equals("move"))
            {
                 d = move(game_one,turn);
                 if(d && turn.equals("player1"))
                 {
                     
                     turn = "player2";
                     c = game_one.checkKings();
                     checkKingCapture(c);
                     checkCheck(turn,c);
                    }   
                    else if(d && turn.equals("player2"))
                    {
                     turn = "player1";
                     c = game_one.checkKings();
                     checkKingCapture(c);
                     checkCheck(turn,c);
                    }
                    else
                    {
                        System.out.println("Invalid move");
                    }
                 game_one.getBoard().printboard();
                 second_response = input.nextLine();
            }
            else
            {
                System.out.println(turn + " lost.");
                System.exit(0);
            }
        }
        
    }
    public static void checkKingCapture(ArrayList<King> p)
    {
        if(p.size() <2)
        {
            King a = p.get(0);
            System.out.println(a.getPlayer() + " wins. End of game");
            
            System.exit(0);
        }
    }
    public static void checkCheck(String turn, ArrayList<King> p)
    {
        Piece b;

        for(King a : p)
        {
            if(a.getPlayer().equals(turn))
            {
                if(a.check()!=null && a.anyMovesLeft()== false)
                {
                    System.out.println(a.getPlayer() + "has lost");
                    System.exit(0);
                }
            }
        }
    }

}
