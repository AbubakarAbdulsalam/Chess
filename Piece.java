import java.util.*;
/**
 * Piece is a superclass encompassing all the available pieces on a chessboard.
 * 
 * @author (Abubakar) 
 * @version (26/10/2016)
 */
public abstract  class Piece implements ChessPieceInterface
{
    
    private String Player; // represents the player the piece belongs to
    private ChessLocation location; // represents the current location of a piece
    protected String char_representation; // represents the character representation of the piece printed to screen
    private ChessGame game;  // represents the game the piece belongs to
    private ArrayList<ChessLocation> threateningLocations;

    /**
     * Constructor for objects of class Piece
     */
    public Piece(String player, ChessLocation c_location,ChessGame game_id)
    {
        // initialise instance variables
        Player = player;
        location = null;
        this.game = game_id;
        game.getBoard().placePieceAt(this,c_location);
        threateningLocations = new ArrayList<>();
        
    }
    /**
     * returns an arrayList of locations this piece can threaten
     */
    public ArrayList<ChessLocation> getThreats()
    {
        return threateningLocations;
    }
    /**
     * adds to the arraylist of the locations this piece can threaten
     */
    public void addThreateningLocations(ChessLocation newLocation)
    {
        threateningLocations.add(newLocation);
    }
    /**
     * clears the threateningLocations for this piece
     */
    public void clearThreateningLocations()
    {
        if(threateningLocations != null)
        {
            threateningLocations.clear();
        }   
        
    }
    /**
     * gets the player the piece belongs to
     */
    public String getPlayer()
    {
        return Player;
    }
    /**
     * gets the current location of the piece
     */
    public ChessLocation getLocation()
    {
        return location;
    }
    /**
     * gets the game the piece belongs to
     */
    public ChessGame getGame()
    {
        return game;
    }
    /**
     * returns the character representation of the piece
     */
    public String getCharRep()
    {
        return char_representation;
    }
    /**
     * sets the location of a piece to the new_location provided
     */
    public void setLocation(ChessLocation new_location)
    {
        location = new_location;
    }
    /**
     * checks the legality of a move returns true if legal
     */
    public abstract boolean canMoveTo(ChessLocation newLocation);
    /**
     * updates all locations this piece can currently threaten
     */
    protected abstract void updateThreateningLocations(ChessLocation location);
    /**
     * moves a piece to a new location on the chessboard
     */
    public boolean moveTo(ChessLocation new_location)
    {

        if(this.checkLineofSight(this.getLocation(), new_location) ==true)
        {
            System.out.println("Sorry you can not perform this move though its valid but there is a piece in the way");
            return false;
        }
        else if(game.getBoard().isPieceAt(new_location)== true)
        {
            if (game.getBoard().getPieceAt(new_location).Player.equals(this.Player))
            {
                System.out.println("Your piece is there");
                return false;
            }
            else
            {
               getGame().getBoard().removePiece(this.getLocation());
               getGame().getBoard().placePieceAt(this, new_location);
               this.setLocation(new_location);
               return true;
            }
            
        }
        else
        {
            getGame().getBoard().removePiece(this.getLocation());// removes the piece at its current location
            getGame().getBoard().placePieceAt(this, new_location);
            this.setLocation(new_location);
            return true;
        }
    }
    /**
     * checks vertical line of sight for a piece moving vertically returns true if there is a piece in the way
     */
    private boolean verticalLineofSight(ChessLocation initial, ChessLocation fianlp)
    {
            // if moving vertically upwards
        if(initial.getRow() > fianlp.getRow())
        {
            // checks all spots vertically upwards till the new destination spot for pieces
            for(int x= initial.getRow()-1; x >=fianlp.getRow()+1; x--)
            {
                if(game.getBoard().isPieceAt(new ChessLocation(x,initial.getColumn()))==true)
                {
                    return true; // if one of the spots contains a piece
                }
            }
            return false; // if all spots are empty
        }
        else  // if not vertically upwards then we check vertically downward
        {
            //checks all spots vertically downwards till the new destination spot for pieces
            for(int x = initial.getRow()+1; x <= fianlp.getRow()-1; x++)
            {
                if(game.getBoard().isPieceAt(new ChessLocation(x,initial.getColumn()))==true)
                {
                    return true; // if one of the spots contains a piece
                }
            }
            return false; //if no piece in the way
        }
        
    }
     /**checks horizontal line of sight for a piece moving horizontally returns true if there is a piece in the way
      * 
      */
    private  boolean horizontalLineofSight(ChessLocation initial, ChessLocation finalp)
    {
        if(initial.getColumn() > finalp.getColumn())
        {
            // checks all spots to the left of current location stopping at the new location
            for(int y = initial.getColumn()-1; y>=finalp.getColumn()+1; y--)
            {
                if(game.getBoard().isPieceAt(new ChessLocation(initial.getRow(),y)) == true)
                {
                    return true; // if piece in way
                }
            }
            return false; // if no piece in way
        }
        else //if movement is to the right
        {
            for(int y = initial.getColumn() +1; y <=finalp.getColumn()-1; y++)
            {
                if(game.getBoard().isPieceAt(new ChessLocation(initial.getRow(),y))==true)
                {
                    return true; // if a piece is in way
                }
            }
            return false; // if nothing in the way
        }
    }
    /**
     *  checks diagonal line of sight for a piece moving diagonally returns true if there is a piece in the way
     */
    private boolean diagonalLineofSight(ChessLocation initial, ChessLocation finalp)
    {
        if(finalp.getRow() - initial.getRow() == 0)
        {
            return false;
        }
        int slope = (finalp.getColumn() - initial.getColumn()) / (finalp.getRow() - initial.getRow());
        if(slope == -1 ) // if slope is negative 
        {
            if(initial.getRow() > finalp.getRow() && initial.getColumn() < finalp.getColumn()) // if moving diagonally upwards to the right
            {
                int y = initial.getColumn() +1;                                // starts from spot after initial and keeps incrementing column
                for(int x = initial.getRow() -1; x >= finalp.getRow()+1; x--)    // and decreasing row till the final destination
                {
                    
                    if(game.getBoard().isPieceAt(new ChessLocation(x,y)) == true)
                    {
                        return true; // if piece in way
                    }
                    y = y+1;
                }
                return false;
            }
            
            else
            { // movement is diagonally downwards to the left
                int x = initial.getRow() +1;                                     //starts from spot after initial and keeps incrementing row
                for(int y =initial.getColumn()-1; y >= finalp.getColumn()+1; y--)  // and decrementing column till final destination
                {
                    if(game.getBoard().isPieceAt(new ChessLocation(x,y)) == true)
                    {
                        return true;
                    }
                    x++;
                }
                return false;
            }
        }
        else if(slope == 1)// if slope is positive
        { 
            if(initial.getRow() > finalp.getRow() && initial.getColumn() > finalp.getColumn()) //if movement is diagonally upwards to the left
            {
                int y =initial.getColumn() -1;                              //starts after initial spot and decrements both row and column till final destination
                for(int x = initial.getRow() -1; x >= finalp.getRow()+1; x--)
                {
                    if(game.getBoard().isPieceAt(new ChessLocation(x,y)) == true)
                    {
                        return true;
                    }
                    y--;
                }
                return false;
            }
            else  //movement is diagonally downwards to the right
            {  
                int y = initial.getColumn() +1;                             // starts at spot after inital and continuosly increases both til final destination
                for(int x = initial.getRow() +1; x <= finalp.getRow()-1; x++)
                {
                    if(game.getBoard().isPieceAt(new ChessLocation(x,y))==true)
                    {
                        return true;
                    }
                    y++;
                }
                return false;
            }
        }
        else
        {
            return false;
        }

    }
    /**
     * check if there is a piece between a piece's initial location and where its trying to move to
     */
    
    protected boolean checkLineofSight(ChessLocation initial, ChessLocation finalp)
    {
         // if moving vertically
        if(initial.getColumn() == finalp.getColumn() && initial.checkEqual(finalp) != true)
        {   
            
            return verticalLineofSight(initial,finalp);
        }
        //if movement is horizontal
        else if(initial.getRow() == finalp.getRow() && initial.checkEqual(finalp)!=true)
        {
            
            return horizontalLineofSight(initial,finalp);
        }
        else // movement is diagonal
        {

                return diagonalLineofSight(initial,finalp);
        }
        
    }
}
   
