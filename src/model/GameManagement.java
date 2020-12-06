package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.NoFoundPositionException;
import exceptions.RepeatedNicknameException;


public class GameManagement implements Serializable {
	/**
	 * 
	 */
	/*The rute file of the serializable file
	 */
	public static final String USERS_FILE_NAME="data/Users.bbd";
	/*The rute file of the serializable file
	 */
	public static final String LOGS_FILE_NAME="data/Logs.bbd";
	/**
	 * Cosntan of serializable
	 */
	private static final long serialVersionUID = 1000L;
	/**
	 * The root user
	 */
	private User rootUsers;
	/**
	 * The root log
	 */
	private Log rootLogs;
	/**
	 * The counter of position user
	 */
	private int contPositionUser;
	private int cont;
	//Method
	public GameManagement() throws IOException {
		contPositionUser=0;
		try {
			loadRootLogs();
			loadRootUsers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Adds the log of the current session
	 * @param u user 
	 */
	public void addLog(User u) {
		LocalDate date = LocalDate.now();
		if (rootLogs == null) {
			rootLogs = new Log(u, date);
			
		} else {
			addLog(rootLogs, u,date);
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

			if (c.getLeft() == null) {
				c.setLeft(new Log(us,d));
				c.getLeft().setParent(c);
			} else {
				addLog(c.getLeft(),us,d);
			}

		} else {

			if (c.getRight() == null) {
				c.setRight(new Log(us,d));
				c.getRight().setParent(c);
			} else {
				addLog(c.getRight(),us,d);
			}
		}
	}
	/**
	 * Search an user by the nickname
	 * @param n nickname user
	 * @return listUsers
	 */
	public ArrayList<User> searchUser(String n){
		User u=searchUserByNickname(n);
		ArrayList<User> listUsers = new ArrayList<User>();
		if(u!=null) {
		listUsers.add(u);
		}
		return listUsers;
	}
	/**
	 * Returns a User given a username 
	 * @param nickname user's nickname
	 * @return User User
	 */
	public User searchUserByNickname(String nickname) {
		return searchUserByNickname(nickname,rootUsers);
	}
	/**
	 * Returns a User recursively
	 * @param n user's nickname
	 * @param current User
	 * @return User user
	 */
	public User searchUserByNickname(String n,User current) {
		User user = null;
		User found=null;
		User[] users =userBubbleSortbyNickname();
		int start = 0;
		int end = users.length- 1;
		int position = 0;
		boolean encontre = false;
		while (start <= end && !encontre) {
			position = (start + end) / 2;
			user= users[position];
			if ((user.getNickname()).equalsIgnoreCase(n)) {
				encontre = true;
				found=users[position];
			} else if (user.getNickname().compareTo(n) > 0) {
				end = position - 1;
			} else {
				start = position + 1;
			}
		}
		return found;
	}
	/**
	 * Adds a new user
	 * @param n nickname of the new user
	 * @param s score of the new user
	 * @param d defeats of the new user
	 * @param ls last stage of the new user
	 * @param mc moodleCoins of the new user
	 * @throws RepeatedNicknameException user Already exist
	 */
	public void addUser(String n, double s, int d, int ls, double mc) throws RepeatedNicknameException {
		User found = searchUserByNickname(n);
		if (found == null) {
			if (rootUsers == null) {
				rootUsers = new User(n,s,d,ls,mc);
				addLog(rootUsers);
			} else {
				User newU = new User(n,s,d,ls,mc);
				addLog(newU);
				addUser(rootUsers,newU);
			}
		} else {
			throw new RepeatedNicknameException();
		}
	}
	public User[] userBubbleSortbyNickname(){
		User[] users = generateUserArray();
		for (int i = users.length; i >0; i--) {
			for (int j = 0; j < i-1; j++) {
				int compare =users[j].getNickname().compareTo(users[j+1].getNickname());
				if(compare>0) {
					User temp = users[j];
					users[j] = users[j+1];
					users[j+1]=temp;
				}
			}
		}
		return users;
	}
	/**
	 * Adds a new user recursively
	 * @param current username current of the binary three
	 * @param newU new user
	 */
	private void addUser(User current,User newU) {
		if (current.getScore() >= newU.getScore()) {
			if (current.getLeft() == null) {
				current.setLeft(newU);
				newU.setParent(current);
			} else {
				addUser(current.getLeft(),newU);
			}
		} else {
			if (current.getRight() == null) {
				current.setRight(newU);
				newU.setParent(current);
			} else {
				addUser(current.getRight(),newU);
			}
		}

	}
	/**
	 * contUsers
	 * Method fot count the users int the bts
	 * @param u root
	 * @return int cont
	 */
	public int contUsers(User u) {
		if(u==null) {
			return 0;
		}
		else {
			return 1+contUsers(u.getLeft())+contUsers(u.getRight());
		}
	}
	/**
	 * Generate an array of users
	 * @return User[] user list
	 */
	private User[] generateUserArray() {
		cont=0;
		int contU = contUsers(rootUsers);
		User[] array= new User[contU];
		if(rootUsers!=null) {
			array = generateUserArray(array,rootUsers);
		}
		return array;
	}
	/**
	 * Generate an array of users
	 * @return User[] user list
	 */
	private User[] generateUserArray(User[] array,User current) {
		array[cont] = current;
		cont++;
		if(current.getLeft()!=null) {
			array = generateUserArray(array,current.getLeft());
		}
		if(current.getRight()!=null){
			array = generateUserArray(array,current.getRight());
		}
		return array;
	}
	/**
	 * Generate an arraylist of users and order with selection sort
	 * @return  listUsers list
	 */
	public ArrayList<User> selectionSortByMoodleCoins(){
		User[] users = generateUserArray();
		ArrayList<User> listUsers = new ArrayList<User>();
		for (int s=0; s<users.length;s++) {
			User l =users[s];
			int wich = s;
			for (int m = s+1; m < users.length; m++) {
				if(users[m].getMoodleCoins()>l.getMoodleCoins()) {
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
	 * @return  user list
	 */
	public ArrayList<User> bubbleSortByLastStage(){
		User[] users = generateUserArray();
		ArrayList<User> listUsers = new ArrayList<User>();
		 for(int s=0; s<users.length; s++){
		      for(int m=0; m<users.length-1; m++){
		        if(users[m].getStages()<users[m+1].getStages()){
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
	 * @return user list
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
	/**
	 * Returns a User given a position
	 * @param position user's position
	 * @return User User
	 * @throws NoFoundPositionException 
	 */
	public User searchUserByPosition(int position) throws NoFoundPositionException {
		return searchUserByPosition(position,rootUsers);
	}
	/**
	 * Returns a User recursively
	 * @param p user's position
	 * @param current User
	 * @return User user
	 */
	public User searchUserByPosition(int p,User current) throws NoFoundPositionException {
		User user = null;
		if (current == null) {
			throw new NoFoundPositionException();
		} else {
			if (p==current.getPosition()) {
				user = current;
			} else if (p <= current.getPosition()) {
				user = searchUserByPosition(p,current.getRight());
			} else {
				user = searchUserByPosition(p,current.getLeft());
			}
		}
		return user;
	}
	/**
	 * Search an user by the position
	 * @param pos position of the user
	 * @return  user list
	 * @throws NoFoundPositionException 
	 */
	public ArrayList<User> searchPosition(int pos) throws NoFoundPositionException{
		User u=searchUserByPosition(pos);
		ArrayList<User> listUsers = new ArrayList<User>();
		if(u!=null) {
		listUsers.add(u);
		}
		return listUsers;
	}
	/**
	 * Show the list of users
	 * @return user list
	 */
	public ArrayList<User> showList() {
		contPositionUser=0;
		ArrayList<User> listUsers = new ArrayList<User>();
		return showList(rootUsers,listUsers);
	}
	/**
	 * Show the list of users
	 * @param current current user
	 * @param u arraylist u
	 * @return u
	 */
	public ArrayList<User> showList(User current,ArrayList<User> u){
		if (current != null) {
			showList(current.getRight(),u);
			contPositionUser++;
			current.setPosition(contPositionUser);
			u.add(current);
			showList(current.getLeft(),u);
		}
		return u;
	}
	/**
	 * Show the list of logs
	 * @return log list
	 */
	public ArrayList<Log> showListLogs() {
		ArrayList<Log> listLogs= new ArrayList<Log>();
		return showListLogs(rootLogs,listLogs);
	}
	/**
	 * Show the list of logs
	 * @param c current log
	 * @param u arraylist u
	 * @return log list
	 */
	public ArrayList<Log> showListLogs(Log c,ArrayList<Log> u){
		if (c != null) {
			showListLogs(c.getRight(),u);
			u.add(c);
			showListLogs(c.getLeft(),u);
		}
		return u;
	}
	/**saveRoot
     * Method to save data users information
     * @throws IOException io
     */
	public void saveRootUsers() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE_NAME));
		oos.writeObject(rootUsers);
		oos.close();
	}
	/**loadRoot
     * Method to load data users information
     * @throws IOException io
     * @throws ClassNotFoundException file not found 
     */
	public void loadRootUsers() throws IOException, ClassNotFoundException{	
		File f=new File(USERS_FILE_NAME);
		if(f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			rootUsers=(User)ois.readObject();
			ois.close();
		}
	}
	/**saveRoot
     * Method to save data logs information
     * @throws IOException io
     */
	public void saveRootLogs() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream( LOGS_FILE_NAME));
		oos.writeObject(rootLogs);
		oos.close();
	}
	/**loadRoot
     * Method to load data logs information
     * @throws IOException io
     * @throws ClassNotFoundException file not found 
     */
	public void loadRootLogs() throws IOException, ClassNotFoundException{	
		File f=new File( LOGS_FILE_NAME);
		if(f.exists()) {
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(f));
			rootLogs=(Log)ois.readObject();
			ois.close();
		}
	}
	
}
