package Duke;

import exceptions.DukeException;
import exceptions.InvalidCommandException;

public class Parser {
	public static Command parse(String input) {
		String[] tokens = input.split(" ");
		String commandStr = tokens[0];
		Command command = null;

		try {
			enums.Command inputCommand = enums.Command.valueOf(commandStr.toUpperCase());

			int sizeOfInputArr = tokens.length;

			String name = "";
			for (int i = 1; i < sizeOfInputArr - 1; i++) {
				name = name.concat(tokens[i]);
				name = name.concat(" ");
			}
			name = name.concat(tokens[sizeOfInputArr - 1]); // to eliminate white space at the end

			try {
				switch (inputCommand) {
				case BYE:
					command = new ByeCommand();
					break;
				case LIST:
					command = new ListCommand();
					break;
				case MARK:
					String markStr = tokens[1];
					int taskNumMark = Integer.parseInt(markStr) - 1;
					command = new MarkCommand(taskNumMark);
					break;
				case UNMARK:
					String unmarkStr = tokens[1];
					int taskNumUnmark = Integer.parseInt(unmarkStr) - 1;
					command = new UnmarkCommand(taskNumUnmark);
					break;
				case TODO:
					Todo todo = new Todo(name);
					command = new AddCommand(todo);
					break;
				case EVENT:
					String[] tokensEvent = input.split("/at ");
					String time = tokensEvent[1];

					String[] tokensNameEvent = name.split("/");
					String eventName = tokensNameEvent[0];
					Event event = new Event(eventName, time);
					command = new AddCommand(event);
					break;
				case DEADLINE:
					String[] tokensDeadline = input.split("/by ");
					String date = tokensDeadline[1];

					String[] tokensNameDeadline = name.split("/");
					String deadlineName = tokensNameDeadline[0];
					Deadline deadline = new Deadline(deadlineName, date);
					command = new AddCommand(deadline);
					break;
				case DELETE:
					int taskNum = Integer.parseInt(tokens[1]) - 1;
					command = new DeleteCommand(taskNum);
					break;
				default:
					throw new InvalidCommandException("\t☹ Woof Woof!!! This command is unidentifiable!!!");
				}
			} catch (DukeException e) {
				System.out.println("\t____________________________________________________________");
				System.out.println(e.getMessage());
				System.out.println("\t____________________________________________________________");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("\t____________________________________________________________");
			System.out.println("\t ☹ Woof Woof!!! You haven't given me enough information for this action!!!");
			System.out.println("\t____________________________________________________________");
		}
		return command;
	}
}
