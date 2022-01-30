public class InputParser {
    static Object[] parseInput(String input) {
        InputType type = InputType.NONE;
        String[] value = new String[]{ input };
        for(InputType inputType: InputType.values()) {
            if (inputType == InputType.NONE) {
                continue;
            }

            if (input.startsWith(inputType.label)) {
                if (inputType == InputType.BYE || inputType == InputType.LIST) {
                    value = new String[]{};
                } else if (inputType == InputType.TODO || inputType == InputType.MARK
                        || inputType == InputType.UNMARK) {
                    String description = input.substring(inputType.label.length() + 1);
                    value = new String[]{description};
                } else if (inputType == InputType.DEADLINE) {
                    int datetimeIndex = input.indexOf("/by");
                    String description = input.substring(inputType.label.length() + 1, datetimeIndex - 1);
                    String datetime = input.substring(datetimeIndex + 4);
                    value = new String[]{description, datetime};
                } else if (inputType == InputType.EVENT) {
                    int datetimeIndex = input.indexOf("/at");
                    String description = input.substring(inputType.label.length() + 1, datetimeIndex - 1);
                    String datetime = input.substring(datetimeIndex + 4);
                    value = new String[]{description, datetime};
                }

                type = inputType;
                return new Object[]{type, value};
            }
        }
        return new Object[]{type, value};
    }
}
