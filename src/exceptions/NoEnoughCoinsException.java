package exceptions;

public class NoEnoughCoinsException extends Exception {

	private static final long serialVersionUID = 1L;
	//Method
	public NoEnoughCoinsException() {
		super("You dont have the enough coins to buy this gun");
	}
}
