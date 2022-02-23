package duke.parsers;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownInputException;

/**
 * Handles the parsing of the input.
 * All input should be parsed via InputParser.
 */
public class InputParser {
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
                if (inputType == InputType.BYE || inputType == InputType.LIST) {
                    value = new String[]{};
                } else if (inputType == InputType.TODO || inputType == InputType.MARK
                        || inputType == InputType.UNMARK || inputType == InputType.DELETE || inputType == InputType.FIND) {
                    try {
                        String description = input.substring(inputType.label.length() + 1).trim();
                        if (description.isBlank()) {
                            throw new EmptyDescriptionException("only blank characters found after command :(");
                        }
                        value = new String[]{description};
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new EmptyDescriptionException("No additional description :(");
                    }
                } else if (inputType == InputType.DEADLINE) {
                    int datetimeIndex = input.indexOf("/by");
                    String description = input.substring(inputType.label.length() + 1, datetimeIndex - 1).trim();
                    String datetime = input.substring(datetimeIndex + 4).trim();
                    value = new String[]{description, datetime};
                } else if (inputType == InputType.EVENT) {
                    int datetimeIndex = input.indexOf("/at");
                    String description = input.substring(inputType.label.length() + 1, datetimeIndex - 1).trim();
                    String datetime = input.substring(datetimeIndex + 4).trim();
                    value = new String[]{description, datetime};
                }

                type = inputType;
                return new Object[]{type, value};
            }
        }

        throw new UnknownInputException("Sorry, Duke doesn't know the command: '" + input + "' :(");
    }
}
