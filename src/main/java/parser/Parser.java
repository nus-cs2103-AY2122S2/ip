package parser;

import exceptions.*;
import commands.*;

public class Parser {

	public Parser(){}

	public static Command processInput(String input) throws DukeException {
		Command cmd = null;
		String[] inputSplit = input.split(" ", 2);
		String commandType = inputSplit[0].toLowerCase();
		switch (commandType) {
		case "bye":
			cmd = new ByeCommand();
			break;
		case "list":
			cmd = new ListCommand();
			break;
		case "mark":
		case "unmark":
		case "delete":
			String detail = inputSplit[1];
			if (!isInteger(detail)) {
				throw new IncorrectValueException();
			}
			int inputVal = Integer.parseInt(detail);
			switch (commandType) {
			case "mark":
				cmd = new MarkCommand(inputVal);
				break;
			case "unmark":
				cmd = new UnMarkCommand(inputVal);
				break;
			case "delete":
				cmd = new DeleteCommand(inputVal);
				break;
			}
			break;
		case "todo":
		case "event":
		case "deadline":
			if (inputSplit.length < 2) {
				throw new EmptyDescriptionException(commandType);
			}
			cmd = new AddCommand(commandType, inputSplit[1]);
			break;
		default:
			throw new UnknownCommandException();
		}
		return cmd;
	}

	private static boolean isInteger(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
