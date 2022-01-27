package core;

public class InputIdentifier {
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    public static InputType determineInputType(String inputData) {
        if (inputData.toLowerCase().startsWith(EVENT_COMMAND)) {
            return InputType.EVENT;
        } else if (inputData.toLowerCase().startsWith(DEADLINE_COMMAND)) {
            return InputType.DEADLINE;
        } else if (inputData.toLowerCase().startsWith(TODO_COMMAND)) {
            return InputType.TODO;
        } else if (inputData.toLowerCase().startsWith(MARK_COMMAND)) {
            return InputType.MARK;
        } else if (inputData.toLowerCase().startsWith(UNMARK_COMMAND)) {
            return InputType.UNMARK;
        } else if (inputData.equalsIgnoreCase(LIST_COMMAND)) {
            return InputType.LIST;
        } else if (inputData.toLowerCase().startsWith(FIND_COMMAND)) {
            return InputType.FIND;
        } else if (inputData.toLowerCase().startsWith(DELETE_COMMAND)) {
            return InputType.DELETE;
        } else {
            return InputType.UNKNOWN;
        }
    }
}
