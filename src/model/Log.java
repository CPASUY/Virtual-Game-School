package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Log implements Serializable{
	//Constants
	private static final long serialVersionUID = 200L;
	//Atributes
	/**
	 * User of log
	 */
	private User user;
	/**
	 * Date of a game
	 */
	private LocalDate date;
	/**
	 * Parent node of the binary search tree
	 */
	private Log parent;

	/**
	 * Left node of the binary search tree
	 */
	private Log left;

	/**
	 * Right node of the binary search tree
	 */
	private Log right;
	/**
	 * Creates an instance of User
	 * @param u user 
	 * @param date date 
	 */
	public Log(User u,LocalDate d) {
		user = u;
		date = d;
	}
	/**
	 * Returns the user
	 * @return User user 
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Returns the Date
	 * 
	 * @return Date date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * Returns the parent node 
	 * @return Log parent node
	 */
	public Log getParent() {
		return parent;
	}

	/**
	 * Sets the parent node 
	 * @param parent Parent node
	 */
	public void setParent(Log parent) {
		this.parent = parent;
	}

	/**
	 * Returns the left node 
	 * @return Log Left node 
	 */
	public Log getLeft() {
		return left;
	}

	/**
	 * Sets the left node  
	 * @param left Left node
	 */
	public void setLeft(Log left) {
		this.left = left;
	}

	/**
	 * Returns the right node 
	 * @return Right node 
	 */
	public Log getRight() {
		return right;
	}

	/**
	 * Sets the right node
	 * @param right Right node 
	 */
	public void setRight(Log right) {
		this.right = right;
	}
}
