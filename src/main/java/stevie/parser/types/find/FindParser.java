package stevie.parser.types.find;

import stevie.command.Command;
import stevie.command.FindCommand;
import stevie.exception.ParserException;
import stevie.exception.TaskException;
import stevie.parser.types.Parser;

public class FindParser implements Parser {
    private final String queryString;
    public FindParser(String input) {
        queryString = input;
    }
    private String trimInput() {
        return queryString.trim();
    }
    @Override
    public Command parse() throws ParserException, TaskException {
        return new FindCommand(trimInput());
    }
}
