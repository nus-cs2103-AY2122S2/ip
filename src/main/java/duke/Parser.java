package duke;

/**
 * Contains methods to parse user input to be understood based on the
 * type of command made by user.
 * Provides method to get the length (in terms of words separated by " ")
 * of user input.
 */
public class Parser {

    /**
     * Parses the user input to return the first word to determine the type of command.
     *
     * @param nextInput Next line of user input read in by the scanner in main class.
     * @return Returns the first word which is the command type of user input.
     */
    public static String getCommand(String nextInput) {
        String[] words = nextInput.split(" ", 2);
        return words[0];
    }

    /**
     * Parses the user input to return the number of words in the user input separated by " ".
     *
     * @param nextInput Next line of user input read in by the scanner in main class.
     * @return Returns the number of words in the user input separated by " ".
     */
    public static int getLength(String nextInput) {
        String[] words = nextInput.split(" ");
        return words.length;
    }

    /**
     * Parses the user input command for a deadline task to retrieve
     * the description and date.
     *
     * @param nextInput Next line of user input read in by the scanner in main class.
     * @return Returns a String[] of the description and date for a deadline task.
     */
    public static String[] getDeadlineDetails(String nextInput) {
        return nextInput.split(" ", 2)[1].split(" - by: ");
    }

    /**
     * Parses the user input command for a todo task to retrieve
     * the description.
     *
     * @param nextInput Next line of user input read in by the scanner in main class.
     * @return Returns a String[] of the description for a todo task.
     */
    public static String getTodoDetails(String nextInput) {
        return nextInput.split(" ", 2)[1];
    }

    /**
     * Parses the user input command for an event task to retrieve
     * the description and date.
     *
     * @param nextInput Next line of user input read in by the scanner in main class.
     * @return Returns a String[] of the description and date for an event task.
     */
    public static String[] getEventDetails(String nextInput) {
        return nextInput.split(" ", 2)[1].split(" - at: ");
    }

    /**
     * Parses the user input command for marking or unmarking a task to retrieve the
     * task id required.
     *
     * @param nextInput Next line of user input read in by the scanner in main class.
     * @return Returns an integer of the task id to be marked or unmarked.
     */
    public static int getTaskId(String nextInput) {
        return Integer.valueOf(nextInput.split(" ", 2)[1]);
    }
}
