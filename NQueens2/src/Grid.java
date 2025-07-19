//KIT107 Assignment 3
/**
 *	Grid ADT
 *
 *	@author <Insert names and student IDs>
 *	@version <Insert date>
 *	
 *	This file holds the Grid ADT which represents
 *	the n-Queens board.  The Grid consists of a location
 *	(of	the current position of the solver), a dimension,
 *	a value (of the current board), and a two-dimensional
 *	array (table/matrix) of the squares in the board.
 *	
 *	YOU NEED TO MAKE CHANGES TO THIS FILE!
*/

import java.awt.*;


public class Grid implements GridInterface, Cloneable
{
	//finals
	protected final boolean TRACING = false;	// do we want to see trace output?

	// properties
	protected int dimension;					// size of the grid, i.e. number of rows and colums
	protected Square board[][];					// all the Squares within the grid

	
	/**
	 *	Grid
	 *	Constructor method 1.
	 *	Pre-condition: none
	 *	Post-condition: an 8x8 grid is created in which all
	 *					squares are empty
	 *	Informally: create an empty 8x8 grid
	*/
	public Grid()
	{
		final int DEFAULT_SIZE = 8;	// the default grid size

      	trace("Grid: Constructor starts");

		// initialise dimension
		dimension = DEFAULT_SIZE;
		// initialise board		
		initialiseGrid();

      	trace("Grid: Constructor ends");
	}
	
	
	/**
	 *	Grid
	 *	Constructor method 2.
	 *	Pre-condition: the given dimension is positive
	 *	Post-condition: a grid of given dimension where all
	 *					squares are empty is created
	 *	Informally: create an unoccupied grid of given dimension
	 *
	 *	@param d number of rows and columns in grid
	*/
	public Grid(int d)
	{
      	trace("Grid: Constructor starts");

		// Store the dimension and create empty grid
		dimension = d;
		initialiseGrid();
		
      	trace("Grid: Constructor ends");		
	}
	
	
	/**
	 *	Grid
	 *	Constructor method 3.
	 *	Pre-condition: the given dimension is positive, and the 
	 *				   Location, and Symbol values are defined
	 *				   and valid
	 *	Post-condition: a Grid of given Dimension is created
	 *					where all squares have no symbol but the
	 *					square at the given location is occupied
	 *					by the given symbol.  An exception is
	 *					thrown if the given location is not
	 *					within the bounds of the grid
	 *	Informally: create a grid of given dimension where all
	 *					squares are empty except the one at the
	 *					given location which is occupied by the
	 *					given symbol
	 *
	 *	@param d number of rows and columns in grid
	 *	@param l Location for first move
	 *	@param s Symbol for first move
	*/
	public Grid(int d, Location l, Symbol s) throws IllegalGridException
	{
      	trace("Grid: Constructor starts");

		// create an empty grid of required size
		dimension = d;
		initialiseGrid();

		// add the queen to the empty grid (unless it is illegal)
		if (validMove(l))
		{
			trace("Grid: occupying initial square");
			occupySquare(l,s);
		}
		else
		{
			trace("Grid: initial square not on grid");
			throw new IllegalGridException();
		}

      	trace("Grid: Constructor ends");
	}
	
	
	/**
	 *	initialiseGrid
	 *	Set up the grid.
	 *	Pre-condition: none
	 *	Post-condition: the two-dimensional array of Squares is
	 *					instantiated and filled with newly
	 *					created empty squares each with the
	 *					correct location
	 *	Informally: create an empty grid of known dimension
	*/
	protected void initialiseGrid()
	{
		final int MINIMUM = 1;	// minimum row and column number

		Location l;				// a location value to iterate through the squares
		
      	trace("initialiseGrid: initialiseGrid starts");
      	
		// instantiate the table (array of arrays)
		board = new Square[dimension][dimension];

		// initialise every square on the board
		for (int r = MINIMUM; r <= dimension; r++)
		{
			for (int c = MINIMUM; c <= dimension; c++)
			{
				l = new Location(r, c);
				board[r-1][c-1] = new Square(l);
			}
		}
		
      	trace("initialiseGrid: initialiseGrid ends");		
	}
	
	
	/**
	 *	clone
	 *	Copy the grid.
	 *	Pre-condition: the current Grid object is validly defined
	 *	Post-condition: the Grid object is copied and the copy
	 *					returned
	 *	Informally: copy the current Grid
	 *
	 *	@return Object the newly created copy of the grid
	*/
	public Object clone()
	{
		final int MINIMUM = 1;	// minimum row and column number

		Grid b;					// the new grid (i.e. the copy)
		Location l;				// a location value to iterate through the squares
		Symbol s;				// the symbol of the 'current' square
		
      	trace("clone: clone starts");

		// Create a new empty grid with same dimensions
		b = new Grid(dimension);

		// Copy all occupied squares from current grid to new grid
		for (int r = MINIMUM; r <= dimension; r++)
		{
			for (int c = MINIMUM; c <= dimension; c++)
			{
				l = new Location(r, c);
				s = getSquare(l).getSymbol();

				// If square is occupied, copy the symbol to new grid
				if (!s.isEmpty())
				{
					b.occupySquare(l, (Symbol)s.clone());
				}
			}
		}
				
      	trace("clone: clone ends");
		return b;
	}
	
	
	/**
	 *	setSquare
	 *	Set method for an element of the "board" instance variable.
	 *	Pre-condition: the given location and square are defined
	 *	Post-condition: the given square is assigned to an element
	 *					of the Grid object selected according to
	 *					the given location within the grid, or an
	 *					exception is thrown if the given location
	 *					is outside the bounds of the current Grid
	 *	Informally: insert the given square into the grid at the
	 *				appropriate location
	 *
	 *	@param l Location for the square
	 *	@param s Square to be stored within grid
	*/	
	public void setSquare(Location l, Square s) throws IllegalGridException
	{
		int r;	// row component of location
		int c;	// column component of location
		
      	trace("setSquare: setSquare starts");
      	
		// is it on the board?
		if (! validMove(l))
		{
			// no, it's not
			trace("setSquare: location not on the grid");
			throw new IllegalGridException();
		}
		else
		{
			// yes it is and so update the grid at the indicated location with the given square
			trace("setSquare: updating grid");
			s.setLocation(l);
			r = l.getRow();
			c = l.getColumn();
			
			board[r-1][c-1] = s;
		}

      	trace("setSquare: setSquare ends");
	}


	/**
	 *	getSquare
	 *	Get method for an element of the "board" instance variable.
	 *	Pre-condition: the given location is defined
	 *	Post-condition: the Square object at the appropriate
	 *					element of the "board" selected according
	 *					to the given Location value is returned, or
	 *					an exception is thrown if the given 
	 *					location is outside the bounds of the
	 *					current Grid
	 *	Informally: return the square of the grid at the given
	 *				location, an exception is thrown if the
	 *				location is not within the grid
	 *
	 *	@param l Location of desired square
	 *
	 *	@return Square the desired Square
	*/
	public Square getSquare(Location l) throws IllegalGridException
	{
		int r;	// row component of location
		int c;	// column component of location

      	trace("getSquare: getSquare starts");

		// is it on the board?
		if (! validMove(l))
		{
			// no, it's not
			trace("getSquare: location not on the grid");
			throw new IllegalGridException();
		}
		else
		{
			// yes it is and so obtain the square at the indicated location from the grid
			r = l.getRow();
			c = l.getColumn();
		
      		trace("getSquare: getSquare ends");
			return board[r-1][c-1];
		}
	}
		
		
	/**
	 *	setDimension
	 *	Set method for the "dimension" instance variable.
	 *	Pre-condition: the given int value is valid
	 *	Post-condition: the instance variable "dimension" is
	 *					assigned the given Dimension value
	 *	Informally: assign the given dimension to the Grid object
	 *
	 *	@param d the number of rows and columns in the grid
	*/
	public void setDimension(int d)
	{
      	trace("setDimension: setDimension starts");

		// Update the grid dimension
		dimension = d;
		
      	trace("setDimension: setDimension ends");
	}


	/**
	 *	getDimension
	 *	Get method for "dimension" instance variable.
	 *	Pre-condition: none
	 *	Post-condition: the value of the Grid object's dimension
	 *					property is returned
	 *	Informally: return the current grid's dimension
	 *
	 *	@return int the number of rows and columns in the grid
	*/
	public int getDimension()
	{
      	trace("getDimension: getDimension starts and ends");
		// Return the current grid dimension
		return dimension;
	}


	/**
	 *	occupySquare
	 *	Make a move.
	 *	Pre-condition: the given location and symbol are defined
	 *	Post-condition: the square at the position in the
	 *					grid indicated by the given Location
	 *					value is altered to the given Symbol, or
	 *					an exception is thrown if the given 
	 *					location is outside the bounds of the
	 *					current Grid
	 *	Informally: update the square at the nominated location
	 *				of the grid with the given symbol
	 *
	 *	@param l Location to place symbol at
	 *	@param s Symbol to place
	*/
	public void occupySquare(Location l, Symbol s)
	{
		Square q;	// square at given Location in current Grid
		Symbol m;	// clone of given Symbol
 
      	trace("occupySquare: occupySquare starts");

		// is it on the board?
		if (! validMove(l))
		{
			// no, it's not
			trace("occupySquare: location not on the grid");
			throw new IllegalGridException();
		}
		else
		{
			// Get the square at the specified location and place symbol
			q = getSquare(l);
			m = (Symbol)s.clone();
			m.setLocation(l);
			q.setSymbol(m);
		}
      	trace("occupySquare: occupySquare ends");
	}


	/**
	 *	squareOccupied
	 *	Check if a square is already taken
	 *	Pre-condition: the given location is defined
	 *	Post-condition: a boolean value is returned which
	 *					represents whether the symbol of
	 *					the square of the current Grid
	 *					object with the given Location
	 *					value is empty, or an exception is 
	 *					thrown if the given location is 
	 *					outside the bounds of the current
	 *					Grid
	 *	Informally: return whether or not the square at
	 *				the given location is occupied
	 *
	 *	@param l Location of square to check
	*/
	public boolean squareOccupied(Location l)
	{
      	trace("squareOccupied: squareOccupied starts and ends");
		// Square is occupied if it contains a non-empty symbol
		return !getSquare(l).isEmpty();
	}
	
	
	/**
	 *	validMove
	 *	Check if a location is in the grid.
	 *	Pre-condition: the given location is defined
	 *	Post-condition: true is returned if the given
	 *					Location is within the bounds of
	 *					the current Grid object, false is
	 *					returned if it is not
	 *	Informally: return whether or not the given
	 *				location lies within the current grid
	 *
	 *	@param l Location to consider
	 *	@return boolean whether or not Location is on the grid
	*/
	public boolean validMove(Location l)
	{
		final int MINIMUM = 1;	// minimum row and column number

		int r;	// row component of location
		int c;	// column component of location

      	trace("validMove: validMove starts");

		// Extract coordinates from the location
		r = l.getRow();
		c = l.getColumn();
		trace("validMove: validMove ends");

		return (r >= MINIMUM && r <= dimension && c >= MINIMUM && c <= dimension);
	}


	/**
	 *	solved
	 *	Check if the grid has the required number of 
	 *		symbols.
	 *	Pre-condition: none
	 *	Post-condition: true is returned if the grid has
	 *					all rows occupied, false is
	 *					returned if it does not
	 *	Informally: return whether or not moves are still
	 *				available on the grid
	 *
	 *	@return boolean whether or not all rows have a
	 *				non-empty symbol within them
	*/
	public boolean solved()
	{
		final int MINIMUM = 1;	// minimum row and column number

  		Location l;	// location to check
		boolean f;	// result
  
      	trace("solved: solved starts");

		// check every square on the grid
		for (int r = MINIMUM; r <= dimension; r++)
  		{
			f = false;	// assume current row is empty
  			for (int c = MINIMUM; c <= dimension; c++)
			{
				l = new Location(r,c);
  				if (squareOccupied(l))
    			{
					// this row is occupied
		      		trace("solved: found a symbol on row " + r);
    				f = true;
    			}
    		}

			if (! f)
			{
				// this row is not occupied
		      	trace("solved: solved ends with no symbol found on row " + r);
    			return false;
			}
		}
		
      	trace("solved: solved ends with a symbol on all rows");		
  		return true;
	}

	
	/**
	* 	rowClear
	*	Check whether the indicated row on the given board is clear of
	*		symbols
	*	Pre-condition: the given Location is within the current Grid
	*	Post-condition: true is returned if row r contains no queens, and
	*					false is returned otherwise
	*	Informally: Return whether or not a row is clear of queens
	*
	*	@param l Location containing row value to check
	*
	*	@return boolean whether the row is clear
	*/
	protected boolean rowClear(Location l)
	{
		final int MINIMUM = 1;	// minimum row and column number

		int r;			// row component of location
		Location l1;	// Location iterated through

		trace("rowClear: rowClear starts");

		r = l.getRow();
		for (int c = MINIMUM; c <= dimension; c++)
		{
			// check location given by row and column
			l1 = new Location(r, c);
			if (squareOccupied(l1))
			{
				// found a symbol and so row isn't clear
				trace("rowClear: rowClear finishes with false");
				return false;
			}
		}

		trace("rowClear: rowClear finishes with true");
		return true;
	}


	/**
	* 	columnClear
	*	Check whether the indicated column on the given board is clear of
	*		symbols
	*	Pre-condition: the given Location is within the current Grid
	*	Post-condition: true is returned if column c contains no queens,
	*					and false is returned otherwise
	*	Informally: Return whether or not a column is clear of queens
	*
	*	@param l Location containing column value to check
	*
	*	@return boolean whether the column is clear
	*/
	protected boolean columnClear(Location l)
	{
		final int MINIMUM = 1;	// minimum row and column number

		int c;			// column component of location
		Location l1;	// Location iterated through

		trace("columnClear: columnClear starts");

		// Extract column number from the given location
		c = l.getColumn();

		// Check each row in the target column for occupied squares
		for (int r = MINIMUM; r <= dimension; r++)
		{
			l1 = new Location(r, c);
			if (squareOccupied(l1))
			{
				// Found an occupied square in this column
				trace("columnClear: columnClear finishes with false");
				return false;
			}
		}

		// No occupied squares found in this column
		trace("columnClear: columnClear finishes with true");
		return true;
	}


	/**
	* 	diagonalsClear
	*	Check whether the diagonals from the given coordinate on the
	*		given board are clear of symbols
	*	Pre-condition: the given Location is within the current Grid
	*	Post-condition: true is returned if the diagonals of the
	*					given coordinate contain no queens, and
	*					false is returned otherwise
	*	Informally: Return whether or not the diagonals of a given
	*				coordinate are clear of queens
	*
	*	@param l Location to check the diagonals of
	*
	*	@return boolean whether the diagonals are clear
	*/
	protected boolean diagonalsClear(Location l)
	{
		final int MINIMUM = 1;	// minimum row and column number

		int r, c;		// row and column of given Location
		int x, y;		// row and column in same diagonals as r and c
		Location l1;	// Location iterated through

		trace("diagonalsClear: diagonalsClear starts");

		// extract starting point
		r = l.getRow();
		c = l.getColumn();

		// obtain top-left coordinate on this location's diagonals
		x = r - Math.min(r, c) + 1;
		y = c - Math.min(r, c) + 1;

		trace("diagonalsClear: found top-left, checking to bottom-right");
		// search diagonal
		while ((x <= dimension) && (y <= dimension))
		{
			l1 = new Location(x, y);
			if (squareOccupied(l1))
			{
				trace("diagonalsClear: diagonalsClear finishes with false");

				return false;
			}
			x++;
			y++;
		}

		trace("diagonalsClear: diagonalsClear left diagonal clear");

		// obtain top-right coordinate on this location's diagonals
		x = r - Math.min(r-1, dimension-c);
		y = c + Math.min(r-1, dimension-c);

		trace("diagonalsClear: found top-right, checking to bottom-left");
		// search diagonal
		while ((x <= dimension) && (y >= MINIMUM))
		{
			l1 = new Location(x, y);
			if (squareOccupied(l1))
			{
				trace("diagonalsClear: diagonalsClear finishes with false");

				return false;
			}
			x++;
			y--;
		}

		trace("diagonalsClear: diagonalsClear finishes with true");

		return true;
	}


	/**
	* 	clash
	*	Check whether the row, column, and/or diagonals from the given
	*		coordinate on the given board are clear of queens
	*	Pre-condition: the given Location is within the current Grid
	*	Post-condition: true is returned if the row, column, and/or 
	*					diagonals of the given Location contain
	*					queens, and falsee is returned otherwise
	*	Informally: Return whether or not the row, column, and
	*				diagonals of a given Location are not clear of
	*				queens
	*
	*	@param l Location to check the row, column, and diagonals of
	*
	*	@return boolean whether there is already a queen on the row,
	*				column,	and/or diagonals
	*/
	public boolean clash(Location l)
	{
		trace("clash: clash starts and finishes");

		return !(rowClear(l) && columnClear(l) && diagonalsClear(l));
	}

	
	/**
	 *	toString
	 *	Convert the grid to a String representation.
	 *	Pre-condition: none
	 *	Post-condition: a String representation of the grid
	 *					is returned
	 *	Informally: find a String representation of the grid
	 *
	 *	@return String representation of the grid
	*/
	public String toString()
	{
		final int MINIMUM = 1;	// minimum row and column number

		Location l;	// Location iterated through
		String s;	// result

	   	trace("toString: toString starts");

		// add top border
		s = "\n+";
		for (int k = MINIMUM; k <= dimension; k++)
		{
			s += "-+";
		}

		// add squares row by row, separating columns by "|"s and rows by "-"s
		s += "\n";
		// process row
		for (int r = MINIMUM; r <= dimension; r++)
		{
			s += "|";
			// process column
			for (int c = MINIMUM; c <= dimension; c++)
			{
				l = new Location(r,c);
				s += getSquare(l).toString();
				// add column separator
				s += "|";
			}
			s += "\n+";

			// add row separator
			for (int k = MINIMUM; k <= dimension; k++)
			{
				s += "-+";
			}
			s += "\n";
		}
		
      	trace("toString: toString ends");
		return s;
	}


	/**
	 *	showGrid
	 *	Display the grid.
	 *	Pre-condition: the Display parameter is correctly defined
	 *	Post-condition: the screen representation of the Grid
	 *					object is displayed on the given Display
	 *	Informally: display the current grid
	 *
	 *	@param s the Display upon which the grid should be shown
	*/
	public void showGrid(Display s)
	{
		final int WIDTH=32;		// the width and height of each square on the Display
		final int MINIMUM = 1;	// minimum row and column number

		Location l;	// Location iterated through
		Graphics g;	// the graphics context of the given Display
		Square q;	// the Square iterated through
		
      	trace("showGrid: showGrid starts");

		// get the graphcs context for painting
		g = s.getGraphics();

		// process all rows
		for (int r = MINIMUM; r <= dimension; r++)
		{
			// process all columns
			for (int c = MINIMUM; c <= dimension; c++)
			{
				// get the square at the location of the current row and column
				l = new Location(r, c);
				q = getSquare(l);

				// display the square at the specified size
				q.showSquare(s, WIDTH);
			}
		}

		// add a border
		g.setColor(Color.MAGENTA);
		g.drawRect(64+110, 64+50, WIDTH*dimension, WIDTH*dimension);
		g.setColor(Color.BLACK);

		trace("showGrid: grid is " + toString());

      	trace("showGrid: showGrid ends");
 	}
 	

	/**
	 *	trace
	 *	Provide trace output.
	 *	Pre-condition: none
	 *	Post-condition: if trace output is desired then the given String
	 *					parameter is shown on the console
	 *	Informally: show the given message for tracing purposes
	 *
	 *	@param s String to display as trace message
	*/
	protected void trace(String s)
	{
		if (TRACING)
		{
			System.out.println("Grid: " + s);
		}
	}
}
