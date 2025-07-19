//KIT107 Assignment 3
/**
 *	Symbol ADT Interface
 *
 *	@author Julian Dermoudy
 *	@version May 2025
 *	
 *	This file holds the Symbol ADT which represents
 *	pieces within the two-dimensional grid.
 *	A Symbol consists of a representation of the
 *	queen and Location.
 *	
 *	This file is complete.
*/


import java.awt.Image;
import java.awt.image.*;

 
public interface SymbolInterface
{
	//public Symbol();
	//public Symbol(Location l);
	//public Symbol(Image i, Location l);
	public boolean isEmpty();
	public void setIcon(Image i);
	public Image getIcon();
	public void setLocation(Location l);
	public Location getLocation();
	public Object clone();
	public void showSymbol(Display s);
	public String toString();
}

