package Exceptions;

public class OutOfRangeException extends DukeException {
	public OutOfRangeException() {

	}

	@Override
	public void printError() {
		printFormatted(new String[]{"☹ OOPS!!! The value input is not in the list"});
	}
}
