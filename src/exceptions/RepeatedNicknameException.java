package exceptions;

public class RepeatedNicknameException extends Exception{
	private static final long serialVersionUID = 1L;
	//Method
		public RepeatedNicknameException() {
			super("The nickname already exist");
		}
}
