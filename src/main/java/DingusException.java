public class DingusException extends IllegalArgumentException {
	private String message;

	public DingusException(String s) {
		super(s);
		this.message = s;
	}

	@Override
	public String toString() {
		return this.message;
	}

}
