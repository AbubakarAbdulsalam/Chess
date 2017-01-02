
/**
 * Write a description of class Rook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rook extends Piece
{
    


    /**
     * Constructor for objects of class Rook
     */
    public Rook(String player, ChessLocation location, ChessGame game)
    {
        
        // initialise instance variables
        super(player,location,game);
        if(player.equalsIgnoreCase("player1"))
        {
           super.char_representation = "R";
        }
        else
        {
            super.char_representation = "r";
        }
      
    }
    /**
     * checks if the piece can move to the location being requested. returns true if so.
     */
    public boolean canMoveTo(ChessLocation new_location)
    {
        if(new_location.getRow() == this.getLocation().getRow() && this.getLocation().checkEqual(new_location)!= true)
        {
            return true;
        }
        else if(new_location.getColumn() == this.getLocation().getColumn() && this.getLocation().checkEqual(new_location)!=true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * moves a rook to the specified location
     */
    public boolean moveTo(ChessLocation new_location)
    {
        if(canMoveTo(new_location))
        {
            return super.moveTo(new_location);
        }
        else
        {
            System.out.println("Invalid move for rook");
            return false;
        }
    }
    /**
     * updates the locations this piece can currently threaten
     */
    protected void updateThreateningLocations(ChessLocation newLocation)
    {
        super.clearThreateningLocations();
        for(int x=0; x<8; x++)
        {
            for(int y=0; y<8; y++)
            {
                if((canMoveTo(new ChessLocation(x,y)))&&!(super.checkLineofSight(this.getLocation(),new ChessLocation(x,y))) && !(this.getLocation().checkEqual(new ChessLocation(x,y))))
                {
                    super.addThreateningLocations(new ChessLocation(x,y));
                }
            }
        }
    }
}
