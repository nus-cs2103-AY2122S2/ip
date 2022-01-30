public class InputParser {
    static Object[] parseInput(String input) {
        InputType type = InputType.NONE;
        String value = input;
        for(InputType inputType: InputType.values()) {
            if (input.startsWith(inputType.label)) {
                value = input.substring(inputType.label.length() + 1);
                type = inputType;
                return new Object[]{type, value};
            }
        }
        return new Object[]{type, value};
    }
}
