package exceptions;

public class EmptyDescriptionException extends DukeException {
	private String type;

	public EmptyDescriptionException(String type) {
		this.type = type;
	}

	@Override
	public String getMessage() {
		return "☹ OOPS!!! The description of a " + type + " cannot be empty.";
	}
}
