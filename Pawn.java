
/**
 * Write a description of class Pawn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pawn extends Piece
{
    // instance variables
    public boolean first_move;
    private int initial_row;
    /**
     * Constructor for objects of class Pawn
     */
    public Pawn(String player, ChessLocation location, ChessGame game)
    {
        // initialise instance variables
        super(player,location,game);
        first_move = false;
        initial_row = location.getRow();
        if(player.equalsIgnoreCase("player1"))
        {
            super.char_representation = "P";
        }
        else
        {
            super.char_representation = "p";
        }
      
    }
    /**
     * checks the legality of the move being attempted and returns true if so.
     */
    public boolean canMoveTo(ChessLocation newLocation)
    {
      
        ChessLocation x = newLocation.getDifference(this.getLocation());
        int y = newLocation.getRow() - this.getLocation().getRow();
        Piece piece = getGame().getBoard().getPieceAt(newLocation);
        if((initial_row == 1) &&( first_move == false) &&( x.getRow() == 2 )&& (x.getColumn() == 0) && (y >0) && piece == null)
        {
            return true;
        }
        else if(initial_row == 1 && x.getRow()==1 && x.getColumn() == 0 && y>0 && piece == null)
        {
            return true;
        }
        else if(initial_row ==6 && x.getRow()==1 && x.getColumn()==0 && y<0 && piece == null)
        {
            return true;
        }
        else if(initial_row==6 && x.getRow()==2 && x.getColumn()==0 && first_move == false && y <0 && piece == null)
        {
            return true;
        }
        else if(initial_row == 1 && x.getRow() ==1 && x.getColumn()==1 && y >0 && piece != null && !(piece.getPlayer().equals(this.getPlayer())))
        {
            return true;
        }
        else if(initial_row == 6 && x.getRow()==1 && x.getColumn()==1 && y <0 && piece!=null &&!(piece.getPlayer().equals(this.getPlayer())) )
        {
            return true;
        }
    
        else
        {
            
            return false;
        }
    }
    
    
    /**
     * moves a pawn to the specified location
     */
    public boolean moveTo(ChessLocation new_location)
    {
        if(canMoveTo(new_location))
        {
            if(super.moveTo(new_location))
            {
                first_move = true;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        
    
    }
    /**
     * updates the locations this piece can currently threaten 
     */
    protected void updateThreateningLocations(ChessLocation  location)
    {
        super.clearThreateningLocations();
        for(int x=0; x<8; x++)
        {
            for(int y=0; y<8; y++)
            {
                if((canMoveTo(new ChessLocation(x,y))) && !(super.checkLineofSight(this.getLocation(),new ChessLocation(x,y))) && !(this.getLocation().checkEqual(new ChessLocation(x,y))))
                {
                    super.addThreateningLocations(new ChessLocation(x,y));
                }
            }
        }
    }
}
