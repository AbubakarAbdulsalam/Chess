
/**
 * Represents the bishop piece in a chessgame.
 * 
 * @author (Abubakar) 
 * @version (26/10/2016)
 */
public class Bishop extends Piece
{ 
    /**
     * Constructor for objects of class Bishop
     */
    public Bishop(String player,ChessLocation location, ChessGame game_no)
    {
        // initialise instance variables
        super(player,location,game_no);
        if(player.equalsIgnoreCase("player1"))
        {
            super.char_representation ="B";
        }
        else
        {
            super.char_representation = "b";
        }
    }
    /**
     * checks if a this piece can move to the location being attempted. returns true if so.
     */
    public boolean canMoveTo(ChessLocation new_location)
    {
        if(this.getLocation().checkEqual(new_location) == true)
        {
            
            return false;
        }
        else
        {
            if(Math.abs(new_location.getRow() - this.getLocation().getRow()) == Math.abs(new_location.getColumn() - this.getLocation().getColumn()))
            {
               return true;
            }
            
            else
            {
                
                return false;
            }
        }
    }
    /**
     * moves a bishop piece from a certain location to the new location given
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
     * updates the spots this piece cna currently threaten
     */
    protected void updateThreateningLocations(ChessLocation newLocation)
    {
        super.clearThreateningLocations();
        for(int x=0; x<8; x++)
        {
            for(int y=0; y<8; y++)
            {
                if((canMoveTo(new ChessLocation(x,y))) && !(super.checkLineofSight(this.getLocation(),new ChessLocation(x,y))) &&!(this.getLocation().checkEqual(new ChessLocation(x,y))))
                {
                    super.addThreateningLocations(new ChessLocation(x,y));
                    
                }
            }
        }
    }
}
