package exceptions;

public class IncorrectValueException extends DukeException {
	public IncorrectValueException() {

	}

	@Override
	public String getMessage() {
		return "☹ OOPS!!! The value input is incorrect";
	}
}
