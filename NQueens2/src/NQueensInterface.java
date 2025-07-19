//KIT107 Assignment 3
/**
 *	Interface for Graphical User Interface and Solution
 *
 *	@author Julian Dermoudy
 *	@version May 2025
 *	
 *	This file defines the GUI which is the delegator
 *	class.  The class is a Frame that contains methods to
 *	construct, display, and manage the graphical user
 *	interface and interaction from the user.
 *	
 *	This file is complete.
*/


import java.awt.*;
import java.awt.event.*;


public interface NQueensInterface
{
	//public NQueens(String t)
    public void paint(Graphics g);
	public void actionPerformed(ActionEvent e);
}
