package sparrow.logic.parser;

import sparrow.commons.exceptions.ParseException;
import sparrow.logic.commands.ToDoCommand;

public class ToDoCommandParser implements Parser<ToDoCommand> {
    private static final String EMPTY_DESCRIPTION_MESSAGE = "The description of a to-do cannot be empty.";
    /**
     * Parses the arguments in the context of a DeleteCommand and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ToDoCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(EMPTY_DESCRIPTION_MESSAGE);
        }
        return new ToDoCommand(args);
    }
}
