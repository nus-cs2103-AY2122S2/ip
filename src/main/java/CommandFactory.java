/**
 * Decides which type of command to generate given user input
 */
public class CommandFactory {

    /**
     * Extracts the command portion of the input provided by the user (usually first word)
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
     * Generates the appropriate command type given the input
     * @param input  Input from the user
     * @return Value of parent type Command
     */
    public Command makeCommand(String input) {
        String commandWord = isolateCommand(input);

        if (commandWord.equals("bye")) {
            return new ByeCommand();
        } else {
            return new EchoCommand(input);
        }
    }
}
