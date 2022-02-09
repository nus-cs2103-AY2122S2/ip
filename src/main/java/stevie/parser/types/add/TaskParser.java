package stevie.parser.types.add;

import java.util.regex.Pattern;

import stevie.parser.types.Parser;

abstract class TaskParser implements Parser {
    protected String input;
    private String regexString;
    public TaskParser(String input, String regexString) {
        this.input = input;
        this.regexString = regexString;
    }
    protected boolean validateInput() {
        return Pattern.matches(regexString, input);
    }

    protected boolean isNotEmpty(String input) {
        return input.length() > 0;
    }
}
