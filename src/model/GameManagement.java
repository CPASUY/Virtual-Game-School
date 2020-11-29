package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.RepeatedNicknameException;


public class GameManagement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;
	private Player game;
	private User rootUsers;
	private Log rootLogs;
	private User currentUser;
	private int contUsers;
	//Method
	public GameManagement() {
		contUsers=0;
	}
	/**
	 * Adds the log of the current session
	 */
	public void addLog() {

		LocalDate date = LocalDate.now();

		if (rootLogs == null) {
			rootLogs = new Log(currentUser, date);
		} else {
			addLog(rootLogs, currentUser,date);
		}
	}
	/**
	 * Adds a log
	 * 
	 * @param curr Current log
	 * @param user user
	 * @param sessionTime sessionTime of the current user
	 * @param date Date of the log
	 */
	private void addLog(Log c, User us,LocalDate d) {

		if (d.toString().compareTo(c.getDate().toString())<0 || d.toString().compareTo(c.getDate().toString())==0  ) {

			Log left = c.getLeft();

			if (left == null) {
				c.setLeft(new Log(currentUser,d));
				c.getLeft().setParent(c);
			} else {
				addLog(left, currentUser,d);
			}

		} else {

			Log right = c.getRight();

			if (right == null) {
				c.setRight(new Log(currentUser,d));
				c.getRight().setParent(c);
			} else {
				addLog(right, currentUser,d);
			}
		}
	}
	/**
	 * Returns a User given a username 
	 * @param nickname user's nickname
	 * @return User User
	 */
	public User searchUser(String nickname) {
		return searchUser(nickname,rootUsers);
	}
	/**
	 * Returns a User recursively
	 * @param n user's nickname
	 * @param current User
	 * @return User user
	 */
	public User searchUser(String n,User current) {
		User user = null;
		if (current == null) {
			user=null;
		} else {
			if (n.equals(current.getNickname()) ) {
				user = current;
			} else if (n.compareTo(current.getNickname()) <= 0) {
				user = searchUser(n,current.getLeft());
			} else {
				user = searchUser(n,current.getRight());
			}

		}

		return user;
	}
	/**
	 * Adds a new user
	 * @param n nickname of the new user
	 * @param s score of the new user
	 * @param p positon of the new user
	 * @param d defeats of the new user
	 * @param ls last stage of the new user
	 * @param mc moodleCoins of the new user
	 * @throws RepeatedNicknameException user Already exist
	 */
	public void addUser(String n, double s, int p, int d, int ls, double mc) throws RepeatedNicknameException {
		User found = searchUser(n);
		if (found == null) {
			if (rootUsers == null) {
				rootUsers = new User(n,s,p,d,ls,mc);
				contUsers++;
			} else {
				User newU = new User(n,s,p,d,ls,mc);
				addUser(rootUsers,newU);
			}
		} else {
			throw new RepeatedNicknameException();
		}
	}
	
	/**
	 * Adds a new user recursively
	 * @param current username current of the binary three
	 * @param newU new user
	 */
	private void addUser(User current,User newU) {

		if (newU.getNickname().compareTo(current.getNickname()) <= 0) {
			
			User left = current.getLeft();

			if (left == null) {
				current.setLeft(newU);
				current.getLeft().setParent(current);
				contUsers++;
			} else {
				addUser(left,newU);
			}

		} else {

			User right = current.getRight();

			if (right == null) {
				current.setRight(newU);
				current.getRight().setParent(current);
				contUsers++;
			} else {
				addUser(right,newU);
			}
		}
	}
	/**
	 * Generate an array of users
	 * @return User[] user list
	 */
	private User[] generateUserArray() {
		int cont = 0;
		User[] array= new User[contUsers];
		if(rootUsers!=null) {
			array = generateUserArray(array,rootUsers,cont);
		}
		return array;
	}
	/**
	 * Generate an array of users
	 * @return User[] user list
	 */
	private User[] generateUserArray(User[] array,User current,int cont) {
		array[cont] = current;
		cont++;
		if(current.getLeft()!=null) {
			array = generateUserArray(array,current.getLeft(),cont);
		}

		if(current.getRight()!=null){
			array = generateUserArray(array,current.getRight(),cont);
		}
		return array;
	}
	/**
	 * Generate an arraylist of users and order with selection sort
	 * @return ArrayList<User> user list
	 */
	public ArrayList<User> selectionSortByMoodleCoins(){
		User[] users = generateUserArray();
		ArrayList<User> listUsers = new ArrayList<User>();
		for (int s=0; s<users.length;s++) {
			User l =users[s];
			int wich = s;
			for (int m = s+1; m < users.length; m++) {
				if(users[m].getMoodleCoins()<l.getMoodleCoins()) {
					l = users[m];
					wich = m;
				}
			}
			User temp = users[s];
			users[s] = l;
			users[wich] = temp;
			listUsers.add(l);
		}
		return listUsers;
	}
	/**
	 * Generate an arraylist of users and order with bubble sort
	 * @return ArrayList<User> user list
	 */
	public ArrayList<User> bubbleSortByLastStage(){
		User[] users = generateUserArray();
		ArrayList<User> listUsers = new ArrayList<User>();
		for(int s=0; s<users.length; s++){
			for(int m=0; m<users.length; m++){
				if(users[m].getLastStage()<users[m+1].getLastStage()){
					User temp=users[m+1];
					users[m+1]=users[m];
					users[m]=temp;
				}
			}
		}
		for (int i = 0; i < users.length; i++) {
			listUsers.add(users[i]);
		}
		return listUsers;
	}
	/**
	 * Generate an arraylist of users and order with insertion sort
	 * @return ArrayList<User> user list
	 */
	public ArrayList<User> insertionSortByDefeats(){
		User[] users = generateUserArray();
		ArrayList<User> listUsers = new ArrayList<User>();
		User temp;
	    for(int i=1; i<users.length; i++){
	      for(int j=i; j>0 && users[j-1].getDefeats() < users[j].getDefeats() ; j--){
	        temp=users[j];
	        users[j]=users[j-1];
	        users[j-1]=temp;
	      }
	    }  
	    for (int i = 0; i < users.length; i++) {
			listUsers.add(users[i]);
		}
		return listUsers;
	}
}
