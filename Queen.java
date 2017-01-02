import java.lang.Math;
/**
 * Represents the queen piece in a chessgame.
 * 
 * @author (Abubakar) 
 * @version (26/10/2016)
 */
public class Queen extends Piece
{
    /**
     * Constructor for objects of class Queen
     */
    public Queen(String player, ChessLocation c_location, ChessGame game_id)
    {
        // initialise instance variables
       super(player,c_location,game_id);
       if(player.equalsIgnoreCase("player1"))
       {
           super.char_representation = "Q";
        }
        else
        {
            super.char_representation = "q";
        }
       

    }
    /**
     * checks if a certain move is legal for a queen piece. returns true if so
     */
    public boolean canMoveTo(ChessLocation new_location)
    {
        ChessLocation x = new_location.getDifference(this.getLocation());
        if(x.getRow() == x.getColumn())
        {
            return true;
        }
        else if(new_location.getColumn() == this.getLocation().getColumn() && new_location.checkEqual(this.getLocation()) != true )
        {
            return true;
        }
        else if(new_location.getRow() == this.getLocation().getRow() && new_location.checkEqual(this.getLocation()) != true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    /**
     * moves the queen piece on a chessboard to a new location provided.
     */
    public boolean moveTo(ChessLocation new_location)
    {     
        if(canMoveTo(new_location))
        {
            return super.moveTo(new_location);
        }
        else
        {
            return false;
        }

    }
    /**
     * updates the locations this piece can threaten from its current location
     */
    protected void updateThreateningLocations(ChessLocation newLocation)
    {
        super.clearThreateningLocations();
        for(int x = 0; x < 8; x++)
        { 
            for(int y =0; y < 8; y++)
            {

                if((canMoveTo(new ChessLocation(x,y))) && !(super.checkLineofSight(this.getLocation(),new ChessLocation(x,y))) && !(this.getLocation().checkEqual(new ChessLocation(x,y))))
                {
                    super.addThreateningLocations(new ChessLocation(x,y));
                }
            }
        }
    }
   
}
    

