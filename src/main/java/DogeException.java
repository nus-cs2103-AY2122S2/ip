public class DogeException extends Exception {
	private String message;

	public DogeException(String s) {
		super(s);
		this.message = s;
	}

	@Override
	public String toString() {
		return this.message;
	}

}
