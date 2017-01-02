import java.util.*;
/**
 * Represents the King piece in a chessgame
 * 
 * @author (Abubakar) 
 * @version (26/10/2016)
 */
public class King extends Piece
{
    
    /**
     * Constructor for objects of class King
     */
    public King(String player,ChessLocation c_location,ChessGame game_id)
    {
        // initialise instance variables
        super(player,c_location,game_id);
        if(player.equalsIgnoreCase("player1"))
        {
            super.char_representation = "K";
        }
        else
        {
            super.char_representation = "k";
        }
    }
    /**
     * checks if a move is valid for this piece. returns true if valid
     */
    public boolean canMoveTo(ChessLocation new_location)
    {
        ChessLocation x = new_location.getDifference(this.getLocation());
        if((x.getRow() == 1 && x.getColumn() == 0) || (x.getColumn() == 1 && x.getRow() == 0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * updates all the postitions this piece can threaten currently
     */
    public void updateThreateningLocations(ChessLocation location)
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
    /**
     * moves a king piece to the specified location
     */
    public boolean moveTo(ChessLocation new_location)
    {
        if(canMoveTo(new_location))
        {
            if(locationinDanger(new_location) == null)
            {
               return super.moveTo(new_location); 
            }
            else
            {
                System.out.println("can't move location endangered by " + locationinDanger(new_location).getCharRep());
                return false;
            }
            
        }
        else
        {
            return false;
        }
    }
    /**
     * checks if a certain location is in danger
     */
    public Piece locationinDanger(ChessLocation location)
    {
        for(int x=0; x<8; x++)
        {
            for(int y=0; y<8; y++)
            {
                Object chesspiece = getGame().getBoard().getPieceAt(new ChessLocation(x,y));
                if(chesspiece != null)
                {
                    Piece r = (Piece) chesspiece;
                    if(!(r.getPlayer().equalsIgnoreCase(super.getPlayer())))
                    {
                        r.updateThreateningLocations(location);
                        for(ChessLocation l : r.getThreats())
                        {
                            if(l.checkEqual(location))
                            {
                                return r;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    /**
     * returns the piece threaten the king at its current location
     */
    public Piece check()
    {
        Piece p = locationinDanger(this.getLocation());
        
        return p;
    }
    /**
     * checks if the king has anymoves left not threatened returns true if so.
     */
    public boolean anyMovesLeft()
    {
        for(int x=0; x<8; x++)
        {
            for(int y=0; y < 8; y++)
            {
                Piece c = locationinDanger(new ChessLocation(x,y));
                if((c == null) && (this.canMoveTo(new ChessLocation(x,y))) && !(super.checkLineofSight(this.getLocation(),new ChessLocation(x,y))))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
