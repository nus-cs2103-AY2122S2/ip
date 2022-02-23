package sparrow.logic.parser;

import sparrow.logic.commands.DeadlineCommand;

public class DeadlineCommandParser {
    /**
     * Parses the arguments in the context of a DeadlineCommand and returns a DeadlineCommand object for execution.
     *
     */
    public DeadlineCommand parse(String args) {
        String[] argsArr = args.split(" /by ");
        String description = argsArr[0];
        String by = argsArr[1];
        return new DeadlineCommand(description, by);
    }
}
