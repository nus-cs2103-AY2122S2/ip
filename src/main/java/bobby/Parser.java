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

    /**
     * Converts user input into commands, passes the commands to TaskList
     * to be executed, or terminates the Bobby program.
     * @param tasks TaskList that handles the execution of commands.
     * @param userInput user input to be parsed.
     * @param bobby instance of Bobby to be terminated if BYE command given.
     */
    public static String parse(TaskList tasks, String userInput, Bobby bobby) {
        String result = "Error, result uninitialized.";
        String command = splitInputToCommand(userInput);
        try {
            switch (Commands.valueOf(command.toUpperCase())) {
            case BYE:
                result = Ui.printExit();
                bobby.terminate();
                break;
            case LIST:
                result = tasks.list();
                break;
            case MARK:
                try {
                    int i = splitInputToIndex(userInput);
                    result = tasks.mark(i);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case UNMARK:
                try {
                    int k = splitInputToIndex(userInput);
                    result = tasks.unmark(k);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case TODO:
                try {
                    result = tasks.addToDo(userInput);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case DEADLINE:
                try {
                    result = tasks.addDeadline(userInput);
                } catch (BobbyException e) {
                    System.out.println(e);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
                break;
            case EVENT:
                try {
                    result = tasks.addEvent(userInput);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case DELETE:
                try {
                    result = tasks.delete(userInput);
                } catch (BobbyException e) {
                    System.err.println(e);
                }
                break;
            case FIND:
                String[] queries = inputs[1].split(" ");
                if (queries.length > 1) {
                    result = Ui.findError();
                } else {
                    String query = inputs[1];
                    result = tasks.find(query);
                }
                break;
            default:
                result = "Bobby did not understand you. Please use valid inputs.";
            }
        } catch (IllegalArgumentException e) {
            result = "Bobby did not understand you. Please use valid inputs.";
            System.out.println("Bobby does not understand you. Please use valid inputs.");
        }
        return result;
    }
}
