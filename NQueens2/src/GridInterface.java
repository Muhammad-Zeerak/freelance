//KIT107 Assignment 3
/**
 *	Grid ADT Interface
 *
 *	@author Julian Dermoudy
 *	@version May 2025
 *	
 *	This file holds the Grid ADT which represents the
 *	n-Queens board.  The Grid consists of a dimension
 *	(i.e. the number of rows/columns), and a two-
 *	dimensional array (i.e. a table/matrix) of the
 *	Squares in the board.
 *	
 *	This file is complete.
*/


public interface GridInterface
{
	//public Grid();
	//public Grid(int d)
	//public Grid(int d, Location l, Symbol s) throws IllegalGridException;
	public Object clone();
	public void setSquare(Location l, Square s) throws IllegalGridException;
	public Square getSquare(Location l) throws IllegalGridException;
	public void setDimension(int d);
	public int getDimension();
	public void occupySquare(Location l, Symbol s) throws IllegalGridException;
	public boolean squareOccupied(Location l) throws IllegalGridException;
	public boolean validMove(Location l);
	public boolean solved();
	public boolean clash(Location l);
	public String toString();
	public void showGrid(Display s);
}
