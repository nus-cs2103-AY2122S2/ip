package core;

public class InputValidator {
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";

    public static InputType determineInputType(String inputData) {
        if (inputData.toLowerCase().startsWith(MARK_COMMAND)) {
            return InputType.MARK;
        } else if (inputData.toLowerCase().startsWith(UNMARK_COMMAND)) {
            return InputType.UNMARK;
        } else if (inputData.equalsIgnoreCase(LIST_COMMAND)) {
            return InputType.LIST;
        } else {
            return InputType.ADD;
        }
    }
}
