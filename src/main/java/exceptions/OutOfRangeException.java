package exceptions;

public class OutOfRangeException extends DukeException {
	public OutOfRangeException() {

	}

	@Override
	public String getMessage() {
		return "☹ OOPS!!! The value input is not in the list";
	}
}
