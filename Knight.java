import java.lang.Math;
/**
 * Represents a Knight piece of a chess game.
 * 
 * @author (Abubakar) 
 * @version (7/10/2016)
 */
public class Knight extends Piece
{ 
    /**
     * Constructor for objects of class Knight
     */
    public Knight(String player, ChessLocation location, ChessGame game)
    {
        super(player,location,game);
        if(player.equalsIgnoreCase("player1"))
        {
            super.char_representation = "N";
        }
        else
        {
            super.char_representation = "n";
        }

    }
    /**
     * checks the legality of the move being attempted. returns true if legal.
     */
    public boolean canMoveTo(ChessLocation newLocation)
    {
        ChessLocation x = newLocation.getDifference(this.getLocation());
        if(x.getRow()==2 && x.getColumn()==1 && (getGame().getBoard().getPieceAt(newLocation)==null))
        {
            return true;
        }
        else if(x.getRow()==1 && x.getColumn()==2 && (getGame().getBoard().getPieceAt(newLocation)==null))
        {
            return true;
        }
        else if(x.getRow()==2 && x.getColumn()==1 &&!(getGame().getBoard().getPieceAt(newLocation).getPlayer().equals(this.getPlayer())))
        {
            return true;
        }
        else if(x.getRow()==1 && x.getColumn()==2 && !(getGame().getBoard().getPieceAt(newLocation).getPlayer().equals(this.getPlayer())))
        {
            return true;
        }
        else
        {
            
            return false;
        }
        
    }
    /**
     * moves a knight object to the specified location
     */
    public boolean moveTo(ChessLocation new_location)
    {
       
        if(canMoveTo(new_location))
        {
            getGame().getBoard().removePiece(this.getLocation());
            getGame().getBoard().placePieceAt(this,new_location);
            
            return true;
        }
        else
        {
            return false;
        }
        
    }
    /**
     * updates the locations this piece can currently threaten.
     */
    protected void updateThreateningLocations(ChessLocation newLocation)
    {
        super.clearThreateningLocations();
        for(int x = 0; x <8; x++)
        {
            for(int y =0; y < 8; y++)
            {
                if((canMoveTo(new ChessLocation(x,y))) && !(this.getLocation().checkEqual(newLocation)))
                {
                    super.addThreateningLocations(new ChessLocation(x,y));
                }
            }
        }
    }


}
