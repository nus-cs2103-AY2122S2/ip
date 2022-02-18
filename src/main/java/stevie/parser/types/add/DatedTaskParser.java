package stevie.parser.types.add;

import java.util.Date;

import stevie.exception.ParserException;
import stevie.parser.DateParser;

/**
 * Parser for a task that requires the handles of date time. Extend
 * DatedTaskParser to create parsers to parse add commands for dated tasks.
 */
abstract class DatedTaskParser extends TaskParser {

    /**
     * Constructor for DateTaskParser.
     * @param input raw string to be parsed
     * @param regexString string to be used to split raw string
     */
    public DatedTaskParser(String input, String regexString) {
        super(input, regexString);
    }

    /**
     * Split string into its parameters
     * @param splitRegex string to be used to split
     * @return string array of split inputs
     */
    protected String[] splitAndTrimInput(String splitRegex) {
        String[] split = input.split(splitRegex, 2);
        split[0] = split[0].trim();
        split[1] = split[1].trim();
        return split;
    }

    /**
     * Check if each string in the split input is non-empty.
     * @param splitInput split input to be checked
     * @return true if both strings are non-empty, else false.
     */
    protected boolean isValidSplits(String[] splitInput) {
        return isNotEmpty(splitInput[0]) && isNotEmpty(splitInput[1]);
    }

    /**
     * Parses raw string into date object.
     * @param dateString raw string input
     * @return parsed date object
     * @throws ParserException if string cannot be parsed into date object
     */
    protected Date parseDate(String dateString) throws ParserException {
        return DateParser.parse(dateString);
    }
}
