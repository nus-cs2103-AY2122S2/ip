public class DogeException extends Exception {
	private final String message;

	public DogeException(String s) {
		super(s);
		this.message = s;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.message;
	}

}
