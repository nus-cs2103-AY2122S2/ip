import java.util.InputMismatchException;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     *
     * Returns the type of the command (bye delete etc)
     *
     */
    public String getType(String command) {
        return command.split(" ")[0];
    }

    /**
     *
     * Returns event task from command
     *
     */
    public Task parseEvent(String command) {
        // Extracts name and time to be done by from event command,
        // eg. event project meeting /at Mon 2-4pm
        String temp = command.substring(6);
        String name = temp.split("/")[0].trim();
        String startAt = temp.split("/")[1].substring(3).trim();
        return new TaskEvents(false, name, startAt);
    }

    /**
     *
     * Returns deadlines task from command
     *
     */
    public Task parseDeadlines(String command) {
        // Extract name and time to be done by from deadline command,
        // eg. deadline return book /by Sunday.
        String temp = command.substring(9);
        String name = temp.split("/")[0].trim();
        String doneBy = temp.split("/")[1].substring(3).trim();
        return new TaskDeadlines(false, name, doneBy);
    }

    /**
     *
     * Returns deadlines task from command
     *
     */
    public Task parseTodos(String command) {
        // Extract name from todo command, eg. todo borrow book
        String name = command.substring(5);
        return new TaskToDos(false, name);
    }

    /**
     *
     * Returns index to delete from delete command
     *
     */
    public static int getDeleteIndex(String command) {
        // Extract name from todo command, eg. todo borrow book
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }
}