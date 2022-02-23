package sparrow.logic.parser;

import sparrow.commons.exceptions.ParseException;
import sparrow.logic.commands.DeleteCommand;

public class DeleteCommandParser implements Parser<DeleteCommand> {
    /**
     * Parses the arguments in the context of a DeleteCommand and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteCommand parse(String args) throws ParseException {
        int index = ParserUtil.parseIndex(args);
        return new DeleteCommand(index);
    }
}
