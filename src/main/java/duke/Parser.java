package duke;

/**
 * Class for parsing through the input.
 */
public class Parser {

    /**
     * Method to split the string input with a forward slash.
     * @param input user input.
     * @return String[] array after splitting.
     */
    public static String[] splitForwardSlash(String input) {
        return input.split("/");
    }

    /**
     * Method to split the string input with a white space.
     * @param input user input.
     * @return String[] array after splitting.
     */
    public static String[] splitSpace(String input) {
        return input.split(" ");
    }

    /**
     * Method to split the string input with a bracket.
     * @param input user input.
     * @return String[] array after splitting
     */
    public static String[] splitBracket(String input) {
        return input.split("]");
    }

    /**
     * Method to change string to int type.
     * @param input user input.
     * @return int after parsing through string.
     */
    public static int stringToInt(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Method to make the description of tasks.
     * @param text user input.
     * @param len length of user input.
     * @return full description of the task.
     */
    public static String makeDesc(String[] text, int len) {
        String newText = "";
        for (int i = 1; i < len; i++) {
            newText = newText + text[i] + " ";
        }
        return newText.trim();
    }
}
