package stevie.parser.types.edit;

import stevie.command.Command;
import stevie.command.MarkCommand;
import stevie.exception.ParserException;

public class MarkParser extends IndexParser {
    private final boolean isDone;

    /**
     * Constructor for MarkParser.
     *
     * @param input  string to be parsed as index
     * @param isDone mark task as done if true else mark as undone
     */
    public MarkParser(String input, boolean isDone) {
        super(input);
        this.isDone = isDone;
    }

    /**
     * Parses string into integer and passes it to constructor for a MarkCommand.
     *
     * @return command that marks a task from task list based off the index given and
     * boolean variable.
     * @throws ParserException if string cannot be parsed as integer
     */
    @Override
    public Command parse() throws ParserException {
        return new MarkCommand(isDone, getIndex());
    }
}
