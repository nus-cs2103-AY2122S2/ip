package core;

public class InputValidator {
    private static final String LIST_COMMAND = "list";

    public static InputType determineInputType(String inputData) {
        if (inputData.equalsIgnoreCase(LIST_COMMAND)) {
            return InputType.LIST;
        } else {
            return InputType.ADD;
        }
    }
}
