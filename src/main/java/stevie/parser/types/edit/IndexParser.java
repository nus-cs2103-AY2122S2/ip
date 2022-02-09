package stevie.parser.types.edit;

import stevie.exception.ParserException;
import stevie.exception.messages.ParserExceptionMessages;
import stevie.parser.types.Parser;

abstract class IndexParser implements Parser {
    private static final String nonIntegerRegex = "[^\\d.]";
    private final String input;

    public IndexParser(String input) {
        this.input = input;
    }

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
