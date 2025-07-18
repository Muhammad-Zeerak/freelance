//KIT107 Assignment 3
/**
 *	Location ADT Interface
 *
 *	@author Julian Dermoudy
 *	@version May 2025
 *	
 *	This file holds the Location ADT which represents
 *	indices to (positions within) the two-dimensional grid.
 *	A Location consists of a row number and a column number.
 *	
 *	This file is complete.
*/


public interface LocationInterface
{
	//public Location(int r, int c);
	public void setRow(int r);
	public void setColumn(int c);
	public int getRow();
	public int getColumn();
	public Object clone();
	public String toString();
}
