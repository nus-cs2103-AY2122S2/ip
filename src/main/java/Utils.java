public class Utils {
    public static boolean isValidType(String type) {
        if (type.equalsIgnoreCase("todo") ||
                type.equalsIgnoreCase("event") ||
                type.equalsIgnoreCase("deadline")) {
            return true;
        }

        return false;
    }

    public static boolean isMissingData(String[] taskArr) {
        if (taskArr.length < 3 && !taskArr[0].equalsIgnoreCase("todo")) {
            return true;
        }

        return false;
    }

    public static boolean isNumeric(String arg, Ui ui) {
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
