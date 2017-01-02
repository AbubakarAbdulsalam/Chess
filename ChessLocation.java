import java.lang.*;
/**
 * Accepts two integer values and create a 2D point that represents a spot on a chess board.
 * 
 * @author (Abubakar) 
 * @version (7/10/2016)
 */
public class ChessLocation
{
    
    private int row;
    private int column;

    /**
     * Constructor for objects of class ChessLocation
    Accepts two integer values and creates an object of ChessLocation
    *
    */


    public ChessLocation(int x, int y)
    
    {
        if( x >= 0 && x <8 && y >=0 && y<8)
        {
            this.row = x;
            this.column = y;
        }
        else throw new IllegalArgumentException("out of bounds");
   
        
    }
    public int getRow()
    /***
     * returns the row of an instance of ChessLocation
     */
    {
        int row = this.row;
        return row;
    }
        /***
     * returns the column of an instance of ChessLocation
     */
    public int getColumn()
    {
        int column = this.column;
        return column;
    }
    /**
     * sets the row of a location instance to x
     */
    public void setRow(int x)
    {
        if( x >= 0 && x <8)
        {
            this.row = x;
        }
        else
        {
            System.out.println("Column out of bounds");
        }
        
    }
    /**
     * sets the column of the location instance to y 
     */
    public void setColumn(int y)
    {
        if( y >= 0 && y < 8)
        {
            this.column = y;
        }
        else
        {
            System.out.println("Row out of bounds");
        }
        
    }
    /**
     * returns the difference in the location instances
     */
    public ChessLocation getDifference( ChessLocation second)
    {
        int x = Math.abs(this.getRow() - second.getRow());
        int y =Math.abs(this.getColumn() - second.getColumn());
        return new ChessLocation(x,y);
    }

    public void getLocation()
    {
        System.out.print("(" + this.getRow() +"," + this.getColumn() + ")");
    }
    /**
     * checks if two ChessLocation objects are equal
     */

    public boolean checkEqual(ChessLocation location2)
    {
        if(this.getRow() == location2.getRow() && this.getColumn() == location2.getColumn())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
