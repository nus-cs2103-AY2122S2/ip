package duke;

/**
 * Decides which type of command to generate given user input
 */
public class Parser {

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
            return input.substring(whiteSpaceIndex + 1); // Return the rest of the word, starting from after whitespace
        } catch (Exception e) {
            return null;
        }
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
            } else if (commandWord.equals("list") || commandWord.equals("l")) {
                return new ListCommand();
            } else if (commandWord.equals("mark") || commandWord.equals("m")) {
                return new MarkCommand(commandParameters);
            } else if (commandWord.equals("unmark") || commandWord.equals("um")) {
                return new UnmarkCommand(commandParameters);
            } else if (commandWord.equals("todo") || commandWord.equals("t")) {
                return new AddTaskCommand(commandParameters, "todo");
            } else if (commandWord.equals("deadline") || commandWord.equals("d")) {
                return new AddTaskCommand(commandParameters, "deadline");
            } else if (commandWord.equals("event") || commandWord.equals("e")) {
                return new AddTaskCommand(commandParameters, "event");
            } else if (commandWord.equals("delete") || commandWord.equals("del")) {
                return new DeleteCommand(commandParameters);
            } else if (commandWord.equals("find") || commandWord.equals("f")) {
                return new FindCommand(commandParameters);
            } else {
                throw new DukeException();
            }

        } catch (DukeException e) {
            System.out.println("Invalid command given");
            return new ByeCommand();
        }
    }
}
