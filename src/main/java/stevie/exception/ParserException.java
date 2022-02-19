package stevie.exception;

import stevie.exception.messages.ParserExceptionMessages;

/**
 * stevie.exception.ParserException wraps all checked exceptions that is related to
 * user inputs to the stevie.Stevie chat bot, that is impossible for stevie.parser.Parser to parse.
 */
public class ParserException extends StevieException {
    public ParserException(ParserExceptionMessages message) {
        super(message.toString());
    }
}
