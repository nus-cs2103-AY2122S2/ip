import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Decides which type of command to generate given user input
 */
public class CommandFactory {

    /**
     * Extracts the command portion of the input provided by the user (usually first word)
     *
     * @param input Input from the user
     * @return One word string
     */
    private String isolateCommand(String input) {
        int whiteSpaceIndex = input.indexOf(" "); // Index of first whitespace
        if (whiteSpaceIndex == -1) { // If the input string has no whitespaces (i.e. one word)
            return input;
        }
        return input.substring(0, whiteSpaceIndex);
    }

    /**
     * Extracts the command parameters portion of the input provided by the user (stuff after first word)
     *
     * @param input Input from the user
     * @return Multiple-word string
     */
    private String isolateParameters(String input) {
        int whiteSpaceIndex = input.indexOf(" "); // Index of first whitespace
        try {
            if (whiteSpaceIndex == -1) { // If the input string has no whitespaces (i.e. one word)
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("No input provided after command");
            System.exit(1);
        }

        return input.substring(whiteSpaceIndex + 1); // Return the rest of the word, starting from after whitespace
    }

    /**
     * Generates the appropriate command type given the input
     *
     * @param input Input from the user
     * @return Value of parent type Command
     */
    public Command makeCommand(String input) {
        String commandWord = isolateCommand(input);
        String commandParameters = isolateParameters(input);
        try {
            if (commandWord.equals("bye")) {
                return new ByeCommand();
            } else if (commandWord.equals("list")) {
                return new ListCommand();
            } else if (commandWord.equals("mark")) {
                return new MarkCommand(commandParameters);
            } else if (commandWord.equals("unmark")) {
                return new UnmarkCommand(commandParameters);
            } else if (commandWord.equals("todo")) {
                return new AddTaskCommand(commandParameters, "todo");
            } else if (commandWord.equals("deadline")) {
                return new AddTaskCommand(commandParameters, "deadline");
            } else if (commandWord.equals("event")) {
                return new AddTaskCommand(commandParameters, "event");
            } else if (commandWord.equals("delete")) {
                return new DeleteCommand(commandParameters);
            } else {
                throw new DukeException();
            }

        } catch (DukeException e) {
            System.out.println("Invalid command given");
            return new ByeCommand();
        }
    }
}
