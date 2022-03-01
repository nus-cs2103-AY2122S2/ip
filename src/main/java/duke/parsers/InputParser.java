package duke.parsers;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownInputException;

import java.util.Arrays;

/**
 * Handles the parsing of the input.
 * All input should be parsed via InputParser.
 */
public class InputParser {
    static private final InputType[] ZERO_VALUE_TYPE = new InputType[]{
            InputType.LIST, InputType.BYE, InputType.SORT, InputType.SORTEVENT, InputType.SORTDEADLINE};
    static private final InputType[] SINGLE_VALUE_TYPE = new InputType[]{
            InputType.TODO, InputType.FIND, InputType.MARK, InputType.UNMARK, InputType.DELETE};
    /**
     * parse the input of string into an InputType and the values of the input.
     * @see InputType
     * @param input the string of input
     * @return an Object[] where the first element is an InputType and the second element is an array of values.
     * @throws EmptyDescriptionException
     * @throws UnknownInputException
     */
    static public Object[] parseInput(String input) throws EmptyDescriptionException, UnknownInputException {
        InputType type = InputType.NONE;
        String[] value = new String[]{ input };
        for(InputType inputType: InputType.values()) {
            if (inputType == InputType.NONE) {
                continue;
            }

            if (input.startsWith(inputType.label)) {
                boolean isZeroValueType = Arrays.stream(ZERO_VALUE_TYPE).anyMatch(x -> x == inputType);
                if (isZeroValueType) {
                    value = new String[]{};
                    type = inputType;
                    return new Object[]{type, value};
                }

                boolean isSingleValueType = Arrays.stream(SINGLE_VALUE_TYPE).anyMatch(x -> x == inputType);
                if (isSingleValueType) {
                    String description = input.substring(inputType.label.length()).trim();
                    if (description.isBlank()) {
                        throw new EmptyDescriptionException("only blank characters found after command :(");
                    }
                    System.out.println("HAHAHA");
                    value = new String[]{description};
                    type = inputType;
                    return new Object[]{type, value};
                }

                if (inputType == InputType.DEADLINE) {
                    int datetimeIndex = input.indexOf("/by");
                    String description = input.substring(inputType.label.length() + 1, datetimeIndex - 1).trim();
                    String datetime = input.substring(datetimeIndex + 4).trim();
                    value = new String[]{description, datetime};
                    type = inputType;
                    return new Object[]{type, value};
                }

                if (inputType == InputType.EVENT) {
                    int datetimeIndex = input.indexOf("/at");
                    String description = input.substring(inputType.label.length() + 1, datetimeIndex - 1).trim();
                    String datetime = input.substring(datetimeIndex + 4).trim();
                    value = new String[]{description, datetime};
                    type = inputType;
                    return new Object[]{type, value};
                }
            }
        }

        throw new UnknownInputException("Sorry, Duke doesn't know the command: '" + input + "' :(");
    }
}
