package exceptions;

public class UnknownCommandException extends DukeException {
	public UnknownCommandException() {
	}

	@Override
	public String getMessage() {
		return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
	}
}
