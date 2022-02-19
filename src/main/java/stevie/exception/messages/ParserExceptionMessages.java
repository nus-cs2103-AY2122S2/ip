package stevie.exception.messages;

/**
 * ParserExceptionMessages contains exception messages relating to errors caused during
 * parsing operations.
 */
public enum ParserExceptionMessages {
    AddEventParseError("Event task requires a name and date!"),
    AddDeadlineParseError("Deadline task requires a name and date!"),
    AddTodoParseError("Todo task requires a name!"),
    DateParseError("Date format is unacceptable!"),
    IndexParseError("Index must be an integer!"),
    InvalidCommandParseError("Oops! Your instructions were unclear!");

    private final String message;

    ParserExceptionMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
