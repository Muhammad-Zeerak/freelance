//KIT107 Assignment 3
/**
 *	Symbol ADT
 *
 *	@author <Insert names and student IDs>
 *	@version <Insert date>
 *	
 *	This file holds the Symbol ADT which represents
 *	pieces within the two-dimensional grid.
 *	A Symbol consists of a representation of a queen plus its
	location.
 *	
 *	YOU NEED TO MAKE CHANGES TO THIS FILE!
*/


import java.awt.*;
import java.awt.image.*;


public class Symbol implements SymbolInterface, Cloneable
{
	// finals (i.e. all objects have their own value)
	protected final boolean TRACING = false;		// do we want to see trace output?
	
	// properties
	protected Image icon;	// the representation of the queen
	protected Location loc;	// the location of the symbol in the board
	
	
	/**
	 *	Symbol
	 *	Constructor method 1.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object is created empty with no location or
	 *					icon
	 *	Informally: intialises the instance variable of the newly
	 *				created Symbol object to empty
	*/
	public Symbol()
	{
      	trace("Symbol: Constructor starts");

		setIcon(null);
		setLocation(null);

      	trace("Symbol: Constructor ends");
	}
	
	
	/**
	 *	Symbol
	 *	Constructor method 2.
	 *	Pre-condition: l is a defined Location object
	 *	Post-condition: the Symbol object is empty (no icon) but with
	 *					the given location
	 *	Informally: intialises the instance variables of the newly
	 *				created Symbol object to empty but with the
	 *				given location
	 *
	 *	@param l Location for the position of the symbol
	*/
	public Symbol(Location l)
	{
      	trace("Symbol: Constructor starts");

		setIcon(null);
		setLocation(l);

      	trace("Symbol: Constructor ends");
	}
	
	
	/**
	 *	Symbol
	 *	Constructor method 3.
	 *	Pre-condition: the given Image and Location are defined 
	 *	Post-condition: the Symbol object is is initialised to the given
	 *					representation and location
	 *	Informally: intialises the instance variables of the newly
	 *				created Symbol object to the value supplied
	 *
	 *	@param i the icon (image) to use
	 *	@param l the location for the position of the symbol
	*/
	protected Symbol(Image i, Location l)
	{
     	trace("Symbol: Constructor starts");

		// Initialize symbol with provided image and location
		setIcon(i);
		setLocation(l);

      	trace("Symbol: Constructor ends");
	}
	
	
	/**
	 *	Test whether given symbol is empty.
	 *	Pre-condition: none
	 *	Post-condition: return true if the given symbol is undefined
	 *					and false otherwise
	 *	Informally: examine the Symbol to see if it is empty or not
	*/
	public boolean isEmpty()
	{
		trace("isEmpty: isEmpty starts and ends");

		// A symbol is empty if it has no icon
		return (icon == null);
	}
	
	
	/**
	 *	setIcon
	 *	Set method for "icon" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object's icon value is altered to
	 *					hold the given (i) value
	 *	Informally: assign the value of the parameter to the Symbol
	 *				object's icon instance variable
	 *
	 *	@param i the icon (image) to use
	*/
	public void setIcon(Image i)
	{	
		trace("setIcon: setIcon starts");

		// Store the provided image as the symbol's icon
		icon = i;

		trace("setIcon: setIcon ends");
	}
	
		
	/**
	 *	Set method for "loc" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object's location is altered to hold
	 *					the given (l) value
	 *	Informally: assign the value of the parameter to the Symbol
	 *				object's loc instance variable
	 *
	 *	@param l the location for the position of the symbol
	*/
	public void setLocation(Location l)
	{
		trace("setLocation: setLocation starts");

		// Store the provided location as the symbol's position
		loc = l;

		trace("setLocation: setLocation ends");

	}
	

	/**
	 *	Get method for "icon" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object's icon value is returned
	 *	Informally: examine the Symbol object's icon instance
	 *				variable returning its value
	 *
	 * @return Image the current symbol's icon
	*/
	public Image getIcon()
	{
		trace("getIcon: getIcon starts and ends");

		// Return the current icon image
		return icon;
	}
	
	
	/**
	 *	Get method for "loc" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object's loc value is returned
	 *	Informally: examine the Symbol object's loc instance
	 *				variable returning its value
	 *
	 * @return Location the current symbol's location
	*/
	public Location getLocation()
	{
		trace("getLocation: getLocation starts and ends");

		// Return the current location
		return loc;
	}
	
	
	/**
	 *	clone
	 *	Clone a symbol.
	 *	Pre-condition: none
	 *	Post-condition: the Symbol object is copied
	 *	Informally: copy the current Symbol
	 *
	 *	@return Object the copy of the Symbol
	*/
	public Object clone()
	{
		Symbol s;	// result
		
      	trace("clone: clone starts");

		// Create a new Symbol with the same icon and a cloned location
		s = new Symbol(icon, (Location)loc.clone());
		
      	trace("clone: clone ends");
		return s;
	}
	
	
	/**
	 *	showSymbol
	 *	Display method for Symbol
	 *	Pre-condition: the Display parameter is valid
	 *	Post-condition: the screen representation of the Symbol
	 *					object is displayed on the given Display at
	 *					the given location in the grid using the
	 *					Symbol object's icon
	 *	Informally: display the current symbol
	 *
	 *	@param s the Display to show the Symbol upon
	*/
	public void showSymbol(Display s)
	{
		int r;		// row component of location
		int c;		// column component of location
		Graphics g;	// the graphics context
		
      	trace("showSymbol: showSymbol starts");
		
		// where is this square
		r = getLocation().getRow();
		c = getLocation().getColumn();
		
		g=s.getGraphics();

		trace("showSymbol: symbol is " + toString());
		
		if (! isEmpty())
		{
			// not an empty symbol so draw the icon
			g.drawImage(icon,(c+1)*32+110,(r+1)*32+50,null);
		}

      	trace("showSymbol: showSymbol ends");
	}
	
	
	/**
	 *	toString
	 *	Convert the symbol to a printable representation.
	 *	Pre-condition: none
	 *	Post-condition: a String representation of the current
	 *					Symbol is returned
	 *	Informally: find the String equivalent of the current
	 *				symbol 
	 *
	 *	@return String the printable representation of the Symbol
	*/
	public String toString()
	{
      	trace("toString: toString starts");

		if (isEmpty())
		{
			// empty symbol
      		trace("toString: toString ends (empty)");
			return "   ";
		}
		else
		{
			// non-empty symbol
     		trace("toString: toString ends (non-empty)");
      		return " Q ";
		}
	}


	/**
	 *	trace
	 *	Provide trace output.
	 *	Pre-condition: none
	 *	Post-condition: if trace output is desired then the given String
	 *					parameter is shown on the console
	 *	Informally: show the given message for tracing purposes
	 *
	 *	@param s the String to be displayed as the trace message
	*/
	protected void trace(String s)
	{
		if (TRACING)
		{
			System.out.println("Symbol: " + s);
		}
	}
}