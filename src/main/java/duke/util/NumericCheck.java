package duke.util;

/**
 * Checks whether the input can be parsed to number
 */
public class NumericCheck {

    /**
     * Checks whether the input string can be parsed into integer
     *
     * @param string The input string
     * @return True if the string can be parsed into an integer and false otherwise
     */
    public static boolean isNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }
}
