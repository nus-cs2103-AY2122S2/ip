package exceptions;

public class IncorrectValueException extends DukeException {
	public IncorrectValueException() {

	}

	@Override
	public void printError() {
		printFormatted(new String[]{"☹ OOPS!!! The value input is incorrect"});
	}
}
