package stevie.parser.types;

import stevie.command.Command;
import stevie.exception.ParserException;
import stevie.exception.TaskException;

public interface Parser {
    Command parse() throws ParserException, TaskException;
}
