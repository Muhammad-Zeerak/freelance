//KIT107 Assignment 3
/**
 *	Square ADT
 *
 *	@author <Insert names and student IDs>
 *	@version <Insert date>
 *	
 *	This file holds the Square ADT which represents
 *	a physical space within a grid.  A Square in a
 *	grid consists of a location, a background colour,
 *	and a symbol.
 *	
 *	YOU NEED TO MAKE CHANGES TO THIS FILE!
*/


import java.awt.*;


public class Square implements SquareInterface, Cloneable
{	
	//finals
	protected final boolean TRACING = false;		// do we want to see trace output?

	// properties
	protected Location loc;			// the location of the current square within the grid
	protected Symbol symbol;		// the symbol of the current square
	protected Color background;		// the background of the current square
	
	
	/**
	 *	Square
	 *	Constructor method 1.
	 *	Pre-condition: the given location value is defined
	 *	Post-condition: the Square object's "loc" field is the value
	 *					given, its "symbol" field is the 'empty' (default),
	 *					symbol, and its "background" field is set to white
	 *					or black as appropriate -- top-left square is white
	 *	Informally: creates an empty square at the given location
	 *
	 *	@param l the Location to associate with the Square
	*/
	public Square(Location l)
	{		
      	trace("Square: Constructor starts");

		// Initialize square with given location and empty symbol
		initialiseSquare(l, new Symbol(l));

      	trace("Square: Constructor ends");
	}
	
	
	/**
	 *	Square
	 *	Constructor method 2.
	 *	Pre-condition: the given location and symbol values are defined
	 *	Post-condition: the Square object's "loc" field is the value
	 *					given, its "symbol" field is the given symbol, and
	 *					its "background" field is set to white or black as
	 *					appropriate -- top-left square is white
	 *	Informally: creates a square at the given location with the given
	 *					symbol
	 *
	 *	@param l the Location to associate with the Square
	 *	@param s the Symbol to store in the Square
	*/
	public Square(Location l, Symbol s)
	{
      	trace("Square: Constructor starts");

		// Initialize square with given location and symbol
		initialiseSquare(l, s);

      	trace("Square: Constructor ends");
	}
	

	/**
	 *	initialiseSquare
	 *	Set up the square.
	 *	Pre-condition: the given location and symbol are valid
	 *	Post-condition: the current square is initialised with the
	 *					given location and symbol, and the
	 *					appropriate background colour
	 *	Informally: initialise the current square
	 *
	 *	@param l the Location to associate with the Square
	 *	@param s the Symbol to store in the Square
	*/
	protected void initialiseSquare(Location l, Symbol s)
	{
		trace("Square: Constructor starts");

		setLocation(l);
		setSymbol(s);
		if ((l.getRow() + l.getColumn()) % 2 == 0)
		{
			// top-left or an even number of squares away
			setBackground(Color.WHITE);
		}
		else
		{
			// an odd number of squares away from top-left
			setBackground(Color.BLACK);
		}

      	trace("Square: Constructor ends");
	}	


	/**
	 *	isEmpty
	 *	Check whether square is occupied.
	 *	Pre-condition: none
	 *	Post-condition: true is returned if the value of the Square
	 *					object's symbol field is returned
	 *	Informally: return whether the current square is occupied
	 *
	 *	@return boolean whether or not the square doesn't contain a
	 *				players' symbol
	*/
	public boolean isEmpty()
	{		
      	trace("isEmpty: isEmpty starts and ends");

		// Square is empty if its symbol is empty
		return symbol.isEmpty();
	}
	
	
	/**
	 *	getLocation
	 *	Get method for "loc" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the value of the Square object's
	 *					location field is returned
	 *	Informally: return the current square's location
	 *
	 *	@return Location the Location associated with the square
	*/
	public Location getLocation()
	{
      	trace("getLocation: getLocation starts and ends");

		return loc;
	}
	
	
	/**
	 *	setLocation
	 *	Set method for "loc" instance variable.
	 *	Pre-condition: the given Location value is defined and
	 *				   valid within the enclosing grid
	 *	Post-condition: the value of the Square object's loc
	 *					field is altered to contain the given
	 *					Location value
	 *	Informally: update the Square object's Location to the
	 *				given value
	 *
	 *	@param l the Location to associate with the Square
	*/
	public void setLocation(Location l)
	{
      	trace("setLocation: setLocation starts");

		loc = l;

      	trace("setLocation: setLocation ends");
	}
	
	
	/**
	 *	getSymbol
	 *	Get method for "symbol" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the value of the Square object's
	 *					symbol field is returned
	 *	Informally: return the current square's symbol
	 *
	 *	@return Symbol the Symbol stored within the Square
	*/
	public Symbol getSymbol()
	{
      	trace("getSymbol: getSymbol starts and ends");

		return symbol;
	}
	
	
	/**
	 *	setSymbol
	 *	Set method for "symbol" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the value of the Square object's symbol
	 *					field is altered to contain the given
	 *					Symbol value
	 *	Informally: update the Square object's Symbol to the
	 *				given value
	 *
	 *	@param s the Symbol to store within the Square
	*/
	public void setSymbol(Symbol s)
	{
      	trace("setSymbol: setSymbol starts");

		symbol = s;

      	trace("setSymbol: setSymbol ends");
	}

	/**
	 * 	Get method for "background" instance variable.
	 * 	Pre-condition: none
	 * 	Post-condition: the value of the Square object's
	 * 					background field is returned
	 * 	Informally: return the current square's background
	 */
	public Color getBackground()
	{
      	trace("getBackground: getBackground starts and ends");

		return background;
	}

	/**
	 * 	Set method for "background" instance variable.
	 * 	Pre-condition: none
	 * 	Post-condition: the value of the Square object's
	 * 					background field is altered to
	 * 					contain the given value
	 * 	Informally: update the Square object's background to
	 * 				the given value
	 * 	@param b the color
	 */
	public void setBackground(Color b)
	{		
		trace("setBackground: setBackground starts");
		
		background = b;

		trace("setBackground: setBackground ends");
	}
	
	
	/**
	 *	clone
	 *	Copy the square.
	 *	Pre-condition: the current Square object is validly defined
	 *	Post-condition: the Square object is copied
	 *	Informally: copy the current Square
	 *
	 *	@return Object the copy of the current Square
	*/
	public Object clone()
	{
		Square s;	// the new Square
		
      	trace("clone: clone starts");

		// Create a new Square with cloned location and symbol
		s = new Square((Location)loc.clone(), (Symbol)symbol.clone());

      	trace("clone: clone ends");
		return s;
	}


	/**
	 *	showSquare
	 *	Display the square.
	 *	Pre-condition: the Display parameter is correctly defined
	 *				   and the int is a sufficiently large
	 *				   positive number
	 *	Post-condition: a visual representation of the Square
	 *					object is displayed on the given Display at
	 *					the position related to its location using
	 *					the Square object's symbol and the given
	 *					width
	 *	Informally: display the current square
	 *
	 *	@param s the Display upon which the square should be shown
	 *	@param w the desired number of pixels for the side length
	 *				of the Square
	*/
	public void showSquare(Display s, int w)
	{
		int r;		// row component of location
		int c;		// column component of location
		Graphics g;	// the graphics context
		
      	trace("showSquare: showSquare starts");
		
		// where is this square
		r = getLocation().getRow();
		c = getLocation().getColumn();
		
		g = s.getGraphics();
		
		trace("showSquare: square is " + toString());	
		
		//draw the square in the appropriate colour
		g.setColor(getBackground());
		g.fillRect((c+1) * 32 + 110, (r+1) * 32 + 50, w, w);
		//draw the symbol
		getSymbol().showSymbol(s);
		
		//reset back to black 
		g.setColor(Color.BLACK);
		
      	trace("showSquare: showSquare ends");
	}
	
	
	/**
	 *	toString
	 *	Convert the Square to a printable representation
	 *	Pre-condition: none
	 *	Post-condition: a String representation of the current
	 *					Square is returned
	 *	Informally: find the String equivalent of the current
	 *				square
	 *
	 *	@return String the printable representation of the Square
	*/
	public String toString()
	{
		String s;	// the result

      	trace("toString: toString starts");
      	
		if (isEmpty())
		{
			// an empty square
			trace("toString: empty square");
			s = "   ";
		}
		else
		{
			// a non-empty square
			trace("toString: non-empty square");
			s = getSymbol().toString();
		}

      	trace("toString: toString ends");
		return s;
	}


	/**
	 *	trace
	 *	Provide trace output.
	 *	Pre-condition: none
	 *	Post-condition: if trace output is desired then the given String
	 *					parameter is shown on the console
	 *	Informally: show the given message for tracing purposes
	 *
	 *	@param s the String to display as the trace message
	*/
	protected void trace(String s)
	{
		if (TRACING)
		{
			System.out.println("Square: " + s);
		}
	}
}
