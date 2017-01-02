import java.util.*;
/**
 * Represents the entity of a chessgame.
 * 
 * @author (Abubakar) 
 * @version (26/10/2016)
 */
public class ChessGame
{
    
    private String player1;
    private String player2;
    private ChessBoard board;
    private ArrayList<King> arrayKings;

    /**
     * Constructor for objects of class ChessGame
     */
    public ChessGame(String player1, String player2)
    {
        // initialise instance variables
        arrayKings = new ArrayList<>();
        board = new ChessBoard();
        this.player1 = player1;
        this.player2 = player2;
        new Queen(player1, new ChessLocation(0,3),this);
        new Queen(player2,new ChessLocation(7,3),this);
        new Bishop(player1, new ChessLocation(0,2), this);
        new Bishop(player1, new ChessLocation(0,5), this);
        new Bishop(player2, new ChessLocation(7,5), this);
        new Bishop(player2, new ChessLocation(7,2), this);
        new Knight(player1, new ChessLocation(0,1), this);
        new Knight(player2, new ChessLocation(7,1), this);
        new Knight(player1, new ChessLocation(0,6), this);
        new Knight(player2, new ChessLocation(7,6), this);
        new King(player1, new ChessLocation(0,4),this);
        new King(player2, new ChessLocation(7,4),this);
        new Rook(player1, new ChessLocation(0,0), this);
        new Rook(player1, new ChessLocation(0,7), this);
        new Rook(player2, new ChessLocation(7,7), this);
        new Rook(player2, new ChessLocation(7,0), this);
        new Pawn(player1, new ChessLocation(1,0), this);
        new Pawn(player2, new ChessLocation(6,0),this);
        new Pawn(player1, new ChessLocation(1,1), this);
        new Pawn(player2, new ChessLocation(6,1), this);
        new Pawn(player1, new ChessLocation(1,2), this);
        new Pawn(player2, new ChessLocation(6,2), this);
        new Pawn(player1, new ChessLocation(1,3), this);
        new Pawn(player2, new ChessLocation(6,3), this);
        new Pawn(player1, new ChessLocation(1,4), this);
        new Pawn(player2, new ChessLocation(6,4), this);
        new Pawn(player1, new ChessLocation(1,5), this);
        new Pawn(player2, new ChessLocation(6,5), this);
        new Pawn(player1, new ChessLocation(1,6), this);
        new Pawn(player2, new ChessLocation(6,6), this);
        new Pawn(player1, new ChessLocation(1,7), this);
        new Pawn(player2, new ChessLocation(6,7), this);
        
    }
    /**
     * returns the board field
     */

    public ChessBoard getBoard()
    {
        // returns the currrent board
 
        return board;
    }
    /**
     * returns an arraylist of the kings in this game
     */
    public ArrayList<King> checkKings()
    {
        arrayKings.clear();
        King a;
        for(int x =0; x<8; x++)
        {
            for(int y=0; y<8; y++)
            {
                if(this.getBoard().getPieceAt(new ChessLocation(x,y))!=null)
                {
                    Piece p = this.getBoard().getPieceAt(new ChessLocation(x,y));
                    if(p instanceof King)
                    {
                        a = (King)p;
                        arrayKings.add(a);
                    }
                }
            }
        }
        return arrayKings;
    }
    
}
