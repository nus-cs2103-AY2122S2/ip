package stevie.parser.types.edit;

import stevie.exception.ParserException;
import stevie.exception.messages.ParserExceptionMessages;
import stevie.parser.types.Parser;

/**
 * Parser for a command that alters task list by an index. Extend IndexParser
 * to create parsers for command that edit task list using an index.
 */
abstract class IndexParser implements Parser {
    private static final String nonIntegerRegex = "[^\\d.]";
    private final String input;

    /**
     * Constructor for an IndexParser
     * @param input raw string to be parsed as integer
     */
    public IndexParser(String input) {
        this.input = input;
    }

    /**
     * Parses and return input string as an integer.
     * @return parsed integer
     * @throws ParserException if string cannot be parsed as an integer
     */
    protected int getIndex() throws ParserException {
        int index;
        try {
            index = Integer.parseInt(input
                    .replaceAll(nonIntegerRegex, "")) - 1;
        } catch (NumberFormatException ex) {
            throw new ParserException(ParserExceptionMessages.IndexParseError);
        }
        return index;
    }
}
