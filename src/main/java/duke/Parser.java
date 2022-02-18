package duke;

/**
 * Deals with making sense of the user command.
 *
 * @author sibinhho99-nus
 */
public class Parser {
    /**
     * Returns index to delete from delete command.
     */
    public static int getDeleteIndex(String command) {
        assert command.contains("delete");
        assert command.split(" ")[1].length() >= 1;

        // Gets index to delete from delete command
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    /**
     * Returns task name to find from find command.
     */
    public static String getTaskToFindName(String command) {
        int startOfNameIndex = 5;
        String nameToFind = command.substring(startOfNameIndex);

        assert command.contains("find");
        assert nameToFind.length() >= 1;

        return nameToFind;
    }

    /**
     * Returns the type of the command (bye delete etc).
     */
    public String getType(String command) {
        assert command.split(" ")[0].length() > 0;
        return command.split(" ")[0];
    }

    /**
     * Returns event Task object from command.
     */
    public Task parseEvent(String command) {
        assert command.contains("event");
        assert command.contains("/at");
        // Extracts name and time to be done by from event command,
        // eg. event project meeting /at Mon 2-4pm
        String temp = command.substring(6);
        String name = temp.split("/")[0].trim();
        String startAt = temp.split("/")[1].substring(3).trim();
        return new EventTask(false, name, startAt);
    }

    /**
     * Returns deadline Task object from command.
     */
    public Task parseDeadlines(String command) {
        assert command.contains("deadline");
        assert command.contains("/by");

        // Extract name and time to be done by from deadline command,
        // eg. deadline return book /by Sunday.
        String temp = command.substring(9);
        String name = temp.split("/")[0].trim();
        String doneBy = temp.split("/")[1].substring(3).trim();
        return new DeadlineTask(false, name, doneBy);
    }

    /**
     * Returns todo Task object from command.
     */
    public Task parseTodos(String command) {
        assert command.contains("todo");

        // Extract name from todo command, eg. todo borrow book
        String name = command.substring(5);
        return new TodoTask(false, name);
    }
}
