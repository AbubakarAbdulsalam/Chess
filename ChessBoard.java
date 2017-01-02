/**
 * This class represents a chessboard. it has one field to represent the actual boardand hold pieces.
 * 
 * @author (Abubakar) 
 * @version (26/10/2016)
 */
public class ChessBoard
{
    
    private Piece gameboard[][];

    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        // initialise instance variables
        gameboard = new Piece[8][8];
        for( int i=0; i<gameboard.length; i++)
        {
            
            for(int j=0; j<gameboard.length; j++)
            {
                this.gameboard[i][j] = null;
             
            }
        }
    }
    /**
     * returns the piece at the specified location
     */
    public Piece getPieceAt(ChessLocation location)
    {
        return gameboard[location.getRow()][location.getColumn()];
    }
   
    /**
     * places a piece on the chessboard
     */
    public void placePieceAt(Piece chesspiece, ChessLocation location)
    {

        gameboard[location.getRow()][location.getColumn()] = chesspiece;
        chesspiece.setLocation(location);
    }
    /**
     * checks if a certain spot on the chessboard is occupied
     */
    public boolean isPieceAt(ChessLocation location)
    {
        if(gameboard[location.getRow()][location.getColumn()] != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * removes a piece from the chessboard
     */
    public void removePiece(ChessLocation location)
    {
        gameboard[location.getRow()][location.getColumn()] = null;
    }
    /**
     * prints the chessboard
     */
    public void printboard()
    {
        int k = 0;
        
        
        for(int i=0; i<gameboard.length; i++)
        {
            for(int j=0; j<gameboard.length; j++)
            {
                if(k<7)
                {
                    if(gameboard[i][j] instanceof Piece)
                    {
                        System.out.print(gameboard[i][j].char_representation + "  ");
                        
                    }
                    else
                    {
                        System.out.print('-' + "  ");
                        
                    }
                    k++;
                }
                else
                {
                    if(gameboard[i][j] instanceof Piece)
                    {
                        System.out.println(gameboard[i][j].char_representation);
                        
                    }
                    else
                    {
                        System.out.println('-');
                        
                    }
                    k=0;
                    
                    
                }

            }
        }
    }
    
}
