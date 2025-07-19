//KIT107 Assignment 3
/**
 *	GameTree ADT Interface
 *
 *	@author Julian Dermoudy
 *	@version May 2025
 *	
 *	This file holds the GameTree ADT which is a
 *	general game tree.  The GameTree is built using
 *	TNode ADTs.  A GameTree object consists of a
 *	"root" field which refers to a TNode object.
 *	
 *	This file is complete.
*/


public interface GameTreeInterface
{
	//public GameTree();
	//public GameTree(Object o, int l);
	public boolean isEmpty();
	public void setData(Object o) throws EmptyGameTreeException;
	public Object getData() throws EmptyGameTreeException;
	public void setLevel(int l) throws EmptyGameTreeException;
	public int getLevel() throws EmptyGameTreeException;
	public void setChild(GameTree c) throws EmptyGameTreeException;
	public GameTree getChild() throws EmptyGameTreeException;
	public void setSibling(GameTree s) throws EmptyGameTreeException;
	public GameTree getSibling() throws EmptyGameTreeException;
	public void generateLevelDF(Stack s,Symbol m);
	public void generateLevelBF(Queue q,Symbol m);
	public GameTree buildGameDF(Stack s,Symbol m,int l);
	public GameTree buildGameBF(Queue q,Symbol m,int l);
	public String toString();
}
