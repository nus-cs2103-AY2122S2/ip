package Duke;

public class Parser {
    /**
     * Splits command and description.
     *
     * @param cmd Command to be split.
     * @return String array containing command and description.
     */
    public static String[] parseCmdAndDes(String cmd) {
        return cmd.split(" ", 2);
    }

    /**
     * Splits description and deadline.
     *
     * @param cmd Description to be split.
     * @return String array containing description and deadline.
     */
    public static String[] splitDeadlineAndTime(String cmd) {
        return cmd.split(" /by ", 2);
    }

    /**
     * Splits description and event time.
     *
     * @param cmd Description to be split.
     * @return String array containing description and event time.
     */
    public static String[] splitEventAndTime(String cmd) {
        return cmd.split(" /at ", 2);
    }

    /**
     * Splits date and time.
     *
     * @param dateTime Date and time to be split.
     * @return String array containing date and time.
     */
    public static String[] splitDateAndTime(String dateTime) {
        return dateTime.split(" ", 2);
    }
}
