package duke;

/**
 * A class responsible for breaking down commands into the command type,
 * and constructing the commands to be executed.
 */
public class Parser {

    /**
     * Parses the user input into Command objects.
     *
     * @param command A string containing the user input.
     * @param ui Ui for reading user input and displaying output.
     * @return Command to be executed.
     */
    public static Command parse(String command, Ui ui) {
        assert !command.equals("") : "command should not be an empty string!";
        command = command.strip().replaceAll(" +", " ");
        String firstWord = command.split(" ")[0];
        String description;
        switch (firstWord) {
        case "bye":
            try {
                if (!command.equals("bye")) {
                    throw new DukeException("No words should be after 'bye'.");
                }
                return new ExitCommand(firstWord);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
            break;
        case "list":
            try {
                if (!command.equals("list")) {
                    throw new DukeException("No words should be after 'list'.");
                }
                return new ListCommand(firstWord);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
            break;
        case "delete":
            try {
                if (command.equals("delete")) {
                    throw new DukeException("Please type what you want to delete!");
                }
                description = command.substring(firstWord.length() + 1);
                int taskNum = Integer.parseInt(description);
                return new DeleteCommand(firstWord, taskNum);
            } catch (NumberFormatException e) {
                ui.showMessage("Please input an integer!");
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
            break;
        case "mark":
            try {
                if (command.equals("mark")) {
                    throw new DukeException("Please type what you want to mark!");
                }
                description = command.substring(firstWord.length() + 1);
                int taskNum = Integer.parseInt(description);
                return new MarkCommand(firstWord, taskNum);
            } catch (NumberFormatException e) {
                ui.showMessage("Please input an integer!");
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
            break;
        case "unmark":
            try {
                if (command.equals("unmark")) {
                    throw new DukeException("Please type what you want to unmark!");
                }
                description = command.substring(firstWord.length() + 1);
                int taskNum = Integer.parseInt(description);
                return new UnmarkCommand(firstWord, taskNum);
            } catch (NumberFormatException e) {
                ui.showMessage("Please input an integer!");
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
            break;
        case "deadline":
            try {
                description = command.substring(firstWord.length() + 1);
                description = description.split(" /by ")[0];
                String time = command.split(" /by ")[1];
                return new AddCommand(firstWord, description, time);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMessage("Eh? Did you mistype the format?");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Uh-oh! There's no deadline here!");
            }
            break;
        case "todo":
            try {
                if (command.equals("todo")) {
                    throw new DukeException("Uh-oh! There is nothing to do here!");
                }
                description = command.substring(firstWord.length() + 1);
                return new AddCommand(firstWord, description);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
            break;
        case "event":
            try {
                description = command.substring(firstWord.length() + 1);
                description = description.split(" /at ")[0];
                String time = command.split(" /at ")[1];
                return new AddCommand(firstWord, description, time);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMessage("Eh? Did you mistype the format?");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Uh-oh! There's no event here!");
            }
            break;
        case "find":
            try {
                if (command.equals("find")) {
                    throw new DukeException("Uh-oh! There is nothing to find here!");
                }
                description = command.substring(firstWord.length() + 1);
                return new FindCommand(firstWord, description);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
            break;
        default:
            // pass through
            break;
        }
        return new InvalidCommand("Please try again!");
    }
}
