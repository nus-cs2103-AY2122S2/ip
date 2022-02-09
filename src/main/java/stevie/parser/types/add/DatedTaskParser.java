package stevie.parser.types.add;

import java.util.Date;

import stevie.exception.ParserException;
import stevie.parser.DateParser;

abstract class DatedTaskParser extends TaskParser {

    public DatedTaskParser(String input, String regexString) {
        super(input, regexString);
    }

    protected String[] splitAndTrimInput(String splitRegex) {
        String[] split = input.split(splitRegex, 2);
        split[0] = split[0].trim();
        split[1] = split[1].trim();
        return split;
    }

    protected boolean isValidSplits(String[] splitInput) {
        return isNotEmpty(splitInput[0]) && isNotEmpty(splitInput[1]);
    }

    protected Date parseDate(String dateString) throws ParserException {
        return DateParser.parse(dateString);
    }
}
