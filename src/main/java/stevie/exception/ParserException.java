package stevie.exception;

import stevie.exception.messages.ParserExceptionMessages;

public class ParserException extends StevieException {
    public ParserException(ParserExceptionMessages message) {
        super(message.toString());
    }
}
