package duke.util;

public final class NumericCheck {

    private NumericCheck () {
    }

    /**
     * Checks if the supplied string is numeric
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
