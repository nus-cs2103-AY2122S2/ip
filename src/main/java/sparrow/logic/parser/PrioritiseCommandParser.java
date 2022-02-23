package sparrow.logic.parser;

import sparrow.commons.exceptions.ParseException;
import sparrow.logic.commands.PrioritiseCommand;
import sparrow.model.Priority;

public class PrioritiseCommandParser implements Parser<PrioritiseCommand> {
    /**
     * Parses the arguments in the context of a DeleteCommand and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public PrioritiseCommand parse(String args) throws ParseException {
        String[] argsArr = args.split(" ");
        int index = ParserUtil.parseIndex(argsArr[0]);
        Priority priority = ParserUtil.parsePriority(argsArr[1]);
        return new PrioritiseCommand(index, priority);
    }
}
