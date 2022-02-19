package stevie.parser.types.add;

import java.util.regex.Pattern;

import stevie.parser.types.Parser;

/**
 * Parser for a generic add task. Extend TaskParser to create parsers for add
 * command of other types of tasks.
 */
abstract class TaskParser implements Parser {
    protected String input;
    private String regexString;

    /**
     * Constructor for TaskParser.
     * @param input raw string to be parsed
     * @param regexString regex string to be checked against input
     *                    in order to validate the input string
     */
    public TaskParser(String input, String regexString) {
        this.input = input;
        this.regexString = regexString;
    }

    /**
     * Check if input string is in a parse-able format by matching it
     * against the given regex string.
     * @return true if input matches, else false
     */
    protected boolean validateInput() {
        return Pattern.matches(regexString, input);
    }

    /**
     * Check if a string is non-empty.
     * @param input string to be checked
     * @return true if string is not empty, else false
     */
    protected boolean isNotEmpty(String input) {
        return input.length() > 0;
    }
}
