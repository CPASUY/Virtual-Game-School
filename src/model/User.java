package model;

import java.io.Serializable;

public class User implements Serializable{
	//Constants
	private static final long serialVersionUID = 200L;
	//Atributes
	/**
	 * The user's nickname
	 */
	private String nickname;
	/**
	 * The user's score
	 */
	private double score;
	/**
	 * The user's position
	 */
	private int position;
	/**
	 * The user's defeats
	 */
	private int defeats;
	/**
	 * The user's las stage
	 */
	private int stages;
	/**
	 * The user's moodleCoins
	 */
	private double moodleCoins;
	/**
	 * The Parent User in the binary Tree
	 */
	private User parent;

	/**
	 * The Right User in the binary Tree
	 */
	private User right;
	/**
	 * The left User in the binary Tree
	 */
	private User left;

	//Methods
	/**
	 * Creates an instance of User
	 * @param n nickname of the User
	 * @param s score of the User
	 * @param p position of the User
	 * @param d defeats of the User
	 * @param ld last stage of the User
	 * @param mc moodleCoins of the User
	 */
	public User(String n, double s, int d, int ls, double mc) {
		super();
		nickname = n;
		score = s;
		position = 0;
		defeats = d;
		stages = ls;
		moodleCoins = mc;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * Returns the user's nickname
	 * @return String nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Returns the user's score
	 * @return double score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * Returns the user's position
	 * @return int position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * Returns the user's defeats
	 * @return int defeats
	 */
	public int getDefeats() {
		return defeats;
	}
	/**
	 * Returns the user's last stage
	 * @return int last stage
	 */
	public int getStages() {
		return stages;
	}
	/**
	 * Returns the user's moodleCoins
	 * @return double mooodlecoins 
	 */
	public double getMoodleCoins() {
		return moodleCoins;
	}
	/**
	 * Returns the parent node 
	 * @return Parent node 
	 */
	public User getParent() {
		return parent;
	}
	/**
	 * Sets the parent node
	 * @param Parent  node 
	 */
	public void setParent(User parent) {
		this.parent = parent;
	}
	/**
	 * Returns the right node 
	 * @return Right node 
	 */
	public User getRight() {
		return right;
	}
	/**
	 * Sets the right node
	 * @param right Right node 
	 */
	public void setRight(User right) {
		this.right = right;
	}
	/**
	 * Returns the left node 
	 * @return Left node 
	 */
	public User getLeft() {
		return left;
	}
	/**
	 * Sets the left node
	 * @param Left node 
	 */
	public void setLeft(User left) {
		this.left = left;
	}
}
