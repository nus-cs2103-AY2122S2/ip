package duke.common;

/**
 * Provides utilities used when processing commands given by user.
 */
public class Utils {
    /**
     * Checks if the add command specifically has either todo, event or deadline.
     * @param type Type is the first word input by user.
     * @return If add command has a valid type.
     */
    public static boolean isValidType(String type) {
        if (type.equalsIgnoreCase("todo")
                || type.equalsIgnoreCase("event")
                || type.equalsIgnoreCase("deadline")) {
            return true;
        }

        return false;
    }

    /**
     * Checks if an input of event or deadline task has incompleted data provided by user.
     * @param taskArr TaskArr is an array broken down from the user input.
     * @return If a new deadline or event has missing data in it.
     */
    public static boolean isMissingData(String[] taskArr) {
        if (taskArr.length < 3 && !taskArr[0].equalsIgnoreCase("todo")) {
            return true;
        }

        return false;
    }

    /**
     * Checks if user's input excluding the command itself, specifically the arguments, are numeric.
     * @param arg arg is the argument provided by user.
     * @return If user input argument is numeric.
     */
    public static boolean isNumeric(String arg) {
        if (arg == null) {
            return false;
        }

        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
