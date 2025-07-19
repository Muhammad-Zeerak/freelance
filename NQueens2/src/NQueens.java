//KIT107 Assignment 3
/**
 *	Graphical User Interface and Solution
 *
 *	@author Julian Dermoudy
 *	@version May 2025
 *	
 *	This file defines the GUI which is the delegator
 *	class.  The class is a Frame that contains methods to
 *	construct, display, and manage the graphical user
 *	interface and interaction from the user.
 *
 *  Initially the constructor will be called from main()
 * 	and this will initialise the GUI.  Whenever the user
 * 	interacts with the GUI, actionPerformed() is
 * 	automatically called.  Whenever the screen (window)
 *  needs to be updated, paint() is automatically called.
 * 
 *  Please note: you do not need to understand what
 *  happens in this file as it is outside the curriculum
 *  of KIT107.
 *	
 *	THIS FILE IS COMPLETE BUT YOU SHOULD MODIFY SOME FINAL VARIABLES (LIMIT, STANDARD_SIZE, AND TRACING)
 *		DURING DEVELOPMENT
*/


import java.awt.*;
import java.awt.event.*;


public class NQueens extends Frame implements NQueensInterface, ActionListener
{
	// finals
	protected final boolean TRACING = false;										// do we want to see trace output?
	protected final int LIMIT = Integer.MAX_VALUE;													// *** maximum number of levels to create -- CHANGE THIS TO Integer.MAX_VALUE ONCE GAME IS WORKING
	protected final Image ICON = Toolkit.getDefaultToolkit().getImage("crown.png");	// picture of Queen
	
	protected final int LOW_DIMENSION=4;	// minimum for grid size range
	protected final int HIGH_DIMENSION=19;	// maximum for grid size range
	protected final int MIN_LOCATION=1;		// minimum row/column number
	protected final int STANDARD_SIZE=4;	// *** dimension of Chess board
	
	// GUI components
	private Label prompt1, prompt2, prompt3;  			// read-only text containers
	private TextField field1, field2, field3, field4;	// read-write text containers
	private Button doitBF, doitDF;						// buttons to initiate solve
	
	// algorithmic values and settings
   	protected Stack dfst;				// an intermediate stack used for the depth-first search
   	protected Queue bfst;				// an intermediate queue used for the breadth-first search
   	protected Display window;			// the screen variable used for display
   	protected boolean started;			// used to indicate that the frame has been initialised
	protected GameTree game;			// game tree representing the current and future states of the game
	protected Grid board;				// initial board
	protected int dim;					// dimension value for the game
	protected int startR;				// commencing row number
	protected int startC;				// commencing column number
	protected boolean depthFirst;		// is the game tree to be generated via Depth-First?
	protected Symbol queen;				// queen symbol
  

  	/**
	 *	NQueens
	 *	Constructor method.
	 *	Pre-condition: the given String value contains the name
	 *				   for the frame (window) being created
	 *	Post-condition: a frame is created showing two buttons
	 *					and one drop-down menu
	 *	Informally: construct the window for displaying the
	 *				game and the game controls
	 *	
	 *	@param a the title for the application's window
	*/
	public NQueens(String t)
   	{  
		final int MINIMUM = 1;	// minimum row and column number

		Location loc;	// starting Location

      	trace("NQueens: Constructor starts");
      	
		started = false; // GUI not yet ready
		dim = STANDARD_SIZE; // make board the default size until changed
   		
     	window=new Display();
      	
      	// create frame and window listener
      	setLayout(new FlowLayout());
      	setTitle(t);
      	setVisible(true);
      	addWindowListener(
      		new WindowAdapter() {
      			public void windowClosing(WindowEvent e) {
      				dispose();
      				System.exit(0);
      			}
      		}
      	);
     	setSize(610, 410);
     	setResizable(false);
     	
     	// initialise GUI widgets
     	trace("NQueens: Adding GUI widgets");
		prompt1 = new Label("Start in row:");
		field1 = new TextField(2);
		prompt2 = new Label("and column:");
		field2 = new TextField(2);
		prompt3 = new Label("on a board of side:");
		field3 = new TextField(2);
		field4 = new TextField(50);
		field1.setText(MINIMUM + "");
		field2.setText(MINIMUM + "");
		field3.setText(STANDARD_SIZE + "");
		field4.setText("");
		field4.setEditable(false);
		doitDF = new Button("Solve DF");
		doitBF = new Button("Solve BF");
		add(prompt1);
		add(field1);
		add(prompt2);
		add(field2);
		add(prompt3);
		add(field3);
		add(doitDF);
		add(doitBF);
		add(field4);

		// add event handlers
    	trace("NQueens: Adding event handlers");
		field1.addActionListener(this);
		field2.addActionListener(this);
		field3.addActionListener(this);
		doitDF.addActionListener(this);
		doitBF.addActionListener(this);
	
		//initialise instance variables
    	trace("NQueens: Initialising game settings");
		startR = Integer.parseInt(field1.getText());
		startC = Integer.parseInt(field2.getText());
		loc = new Location(startR, startC);
		queen = new Symbol(ICON, loc);
		dim = Integer.parseInt(field3.getText());
		board = new Grid(dim, loc, queen);
		window = new Display();
      	window.setGraphics(getGraphics());

		// reveal all
		trace("NQueens: Display it all and wait!");
      	setVisible(true);
      	repaint();
	
      	trace("NQueens: Contructor ends");
   	}
  

	/**
	 *	paint
	 *	Paint method.
	 *	Pre-condition: the given Graphics value is a valid
	 *				   graphics context and corresponds to the
	 *				   context stored within the "window"
	 *				   instance variable
	 *	Post-condition: the grid at the root of the tree
	 *					referred to by the "game" instance
	 *					variable is displayed.  
	 *	Informally: display the 'current' grid
	 *
	 *	@param g the current Graphics context
	*/
	public void paint(Graphics g)
	{
		Grid b;	// the grid at the top of the game

		trace("paint: paint starts");
		
		window.setGraphics(getGraphics());

		if (started)
		{	// either solving or solved
			trace("paint: solving/solved so show (partial) solution");
		
			b = (Grid)game.getData();
			trace("paint: solution is: " + b.toString());
			b.showGrid(window);
		}
		else
		{	// not yet started so show starting point
			board.showGrid(window);
			trace("paint: yet to start solving");
		}
	    
	    trace("paint: paint ends");
   	}
   
   
	/**
	 *	actionPerformed
	 *	Handle mouse-clicks
	 *	Pre-condition: the ActionEvent parameter indicates whether
	 *				   the "dfs" or "bfs" buttons were pressed
	 *	Post-condition: the game tree is generated using either a
	 *					depth- or breadth-first approach and the
	 *					solution displayed
	 *	Informally: initiate generation of the game tree
	 *
	 *	@param e the ActionEvent generated by the user
	*/
	public void actionPerformed(ActionEvent e)
   	{		
		final int MINIMUM = 1;	// minimum row and column number

		Grid g;			// grid from game tree representing (partial) solution
		int potY, potX;	// row and column of potential starting Location
		int potDim;		// potential dimension of grid
		Location loc;	// starting location

		trace("actionPerformed: begins");

		showStatus("");
		if ((e.getSource() == field1) || (e.getSource() == field2))	// new starting location
		{
			trace("actionPerformed: input of new start position");
			started = false;
			potY = Integer.parseInt(field1.getText());
			potX = Integer.parseInt(field2.getText());
			if (((potY < MINIMUM) || (potY > dim)) || ((potX < MINIMUM) || (potX > dim)))
			{
				// new starting location is invalid, revert to original
				showStatus("Cannot change location, must be within " + MINIMUM + " and " + dim);
				field1.setText(Integer.toString(startR));
				field2.setText(Integer.toString(startC));
			}
			else
			{
				// new starting location is valid, so use it to update starting board
				startR = potY;
				startC = potX;
				loc = new Location(startR,startC);
				board = new Grid(dim,loc,queen);
			}
		}

		if (e.getSource() == field3)	// new board size
		{
			trace("actionPerformed: input of new dimension");
			started = false;
			potDim = Integer.parseInt(field3.getText());
			if ((startR > potDim) || (startC > potDim))
			{
				// new dimension is smaller than before and no longer contains starting position
				showStatus("Cannot change dimension, grid must contain location and tour");
			}
			else
			{
				if ((potDim < LOW_DIMENSION) || (potDim > HIGH_DIMENSION))
				{
					// new dimension is invalid, revert to original
					showStatus("Cannot change dimension beyond " + LOW_DIMENSION + "-" + HIGH_DIMENSION);
					field3.setText(Integer.toString(dim));
				}
				else
				{
					// new dimension is valid, so use it to update starting board
					dim = potDim;
					loc = new Location(startR, startC);
					board = new Grid(dim, loc, queen);
				}
			}
		}

		if ((e.getSource() == doitBF) || (e.getSource() == doitDF)) // solve!
		{
			trace("actionPerformed: one of the start buttons pressed");

			// update settings and create game tree etc.
			startR = Integer.parseInt(field1.getText());
			startC = Integer.parseInt(field2.getText());
			loc = new Location(startR, startC);
			dim = Integer.parseInt(field3.getText());

			dfst = new Stack();
			bfst = new Queue();
			board = new Grid(dim,loc,queen);
			game = new GameTree(board, MINIMUM);
			
			started = true;

			// find the solution if one exists
			showStatus("Searching for a solution...");
			trace("actionPerformed: searching for a solution...");
			if (e.getSource() == doitDF)
			{
				// depth-first
				game = game.buildGameDF(dfst, queen, (LIMIT < dim ? LIMIT : dim));
			}
			else
			{
				// breadth-first
				game = game.buildGameBF(bfst, queen, (LIMIT < dim ? LIMIT : dim));
			}

			if (game.isEmpty())
			{
				// resultant game is empty hence there is no solution
				board = new Grid(dim, loc, queen);
				showStatus("No solution!");
				trace("actionPerformed: no solution");
				game = new GameTree(board, MINIMUM);
			}
			else
			{
				// resultant game is non-empty hence solution determined
				showStatus("Solution found");
				g = (Grid)game.getData();
				trace("actionPerformed: " + g.toString());
			}
		}

		// show it!
		repaint();

		trace("actionPerformed: actionPerformed ends");
   	}
   	

	protected void showStatus(String s)
	/*
		display status method.
		Pre-condition: field4 is defined
		Post-condition: field4 has been filled with the given
						String
		Informally: show a status message in a text field
	*/
	{
		field4.setText(s);
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
			System.out.println("NQueens: " + s);
		}
	}
}
