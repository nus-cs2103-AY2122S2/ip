package stevie.parser.types.edit;

import stevie.command.Command;
import stevie.command.DeleteCommand;
import stevie.exception.ParserException;
import stevie.exception.TaskException;

public class DeleteParser extends IndexParser {
    public DeleteParser(String input) {
        super(input);
    }

    @Override
    public Command parse() throws ParserException, TaskException {
        return new DeleteCommand(getIndex());
    }
}
