package bobby;

import java.time.format.DateTimeParseException;

/**
 * The Parser class deals with converting user input into commands
 * understood by the program and executes them accordingly.
 */
public class Parser {

    private static String splitInputToCommand(String input) {
        return input.split(" ", 2)[0];
    }

    private static int splitInputToIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1]) - 1;
    }

    private static String[] splitInputToQuery(String input) {
        return input.split(" ", 2)[1].split(" ");
    }

    /**
     * Converts user input into commands, passes the commands to TaskList
     * to be executed, or terminates the Bobby program.
     *
     * @param tasks TaskList that handles the execution of commands.
     * @param userInput user input to be parsed.
     */
    public static String parse(TaskList tasks, String userInput) {
        String command = splitInputToCommand(userInput);
        String result;
        try {
            switch (Commands.valueOf(command.toUpperCase())) {
            case BYE:
                result = Ui.printExit();
                break;
            case LIST:
                result = tasks.list();
                break;
            case MARK:
                int i = splitInputToIndex(userInput);
                result = tasks.mark(i);
                break;
            case UNMARK:
                int k = splitInputToIndex(userInput);
                result = tasks.unmark(k);
                break;
            case TODO:
                result = tasks.addToDo(userInput);
                break;
            case DEADLINE:
                try {
                    result = tasks.addDeadline(userInput);
                } catch (DateTimeParseException e) {
                    result = Ui.dateFormatError();
                }
                break;
            case EVENT:
                result = tasks.addEvent(userInput);
                break;
            case DELETE:
                int j = splitInputToIndex(userInput);
                result = tasks.delete(j);
                break;
            case FIND:
                String[] queries = splitInputToQuery(userInput);
                if (queries.length > 1) {
                    result = Ui.findError();
                } else {
                    String query = queries[0];
                    result = tasks.find(query);
                }
                break;
            case UNDO:
                try {
                    tasks.undo();
                    result = Ui.printUndo();
                } catch (BobbyException e) {
                    result = Ui.printUndoError();
                }
                break;
            default:
                result = Ui.invalidInput();
            }
        } catch (IllegalArgumentException e) {
            result = Ui.invalidInput();
        } catch (BobbyException e) {
            result = e.toString();
        }
        assert !result.equals(null) : "result uninitialized";
        return result;
    }
}
