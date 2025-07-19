//KIT107 Assignment 3
/**
 *	Square ADT Interface
 *
 *	@author Julian Dermoudy
 *	@version May 2025
 *	
 *	This file holds the Square ADT which represents
 *	a physical space within a grid.   A Square in a
 *	grid consists of a location, a background, and
 *	a symbol (an empty symbol indicates an empty
 *	square).
 *	
 *	This file is complete.
*/


import java.awt.Color;


public interface SquareInterface
{
	//public Square(Location l);
	//public Square(Location l, Symbol s);
	public boolean isEmpty();
	public Location getLocation();
	public void setLocation(Location l);
	public Symbol getSymbol();
	public void setSymbol(Symbol s);
	public Color getBackground();
	public void setBackground(Color b);
	public Object clone();
	public void showSquare(Display s, int w);
	public String toString();
}
