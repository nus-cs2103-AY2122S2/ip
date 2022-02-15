package duke.extract;

/**
 * Extract for Duke. Handles description, date and time extraction from user input.
 *
 * @author yl-ang
 */
public class Extract {

    /**
     * Extracts the task description from the user input.
     *
     * @param data Data the full user input.
     * @param start Starting string to extract from.
     * @param timeCommand Stopping string to extract to.
     * @return Extracted task description from user input.
     */
    public static String extractDesc(String data, String start, String timeCommand) {
        int startIndex = data.indexOf(start) + start.length();
        if (timeCommand == null) {
            return data.substring(startIndex);
        }
        int endIndex = data.indexOf(timeCommand);
        return data.substring(startIndex, endIndex - 1);
    }

    /**
     * Extracts the task date and time from the user input.
     *
     * @param data Full user input.
     * @param timeCommand Starting time and date to extract from.
     * @param descriptionCommand Stopping string to extract to.
     * @return Extracted date dnd time from user input.
     */
    public static String extractTime(String data, String timeCommand, String descriptionCommand) {

        int startIndex = data.indexOf(timeCommand) + timeCommand.length();
        if (descriptionCommand == null) {
            return data.substring(startIndex);
        }
        int endIndex = data.indexOf(descriptionCommand);
        return data.substring(startIndex, endIndex - 1);
    }
}
