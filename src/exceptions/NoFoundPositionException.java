package exceptions;

public class NoFoundPositionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoFoundPositionException() {
		super("This position does not exist");
	}

}
