package sparrow.logic.parser;

import sparrow.logic.commands.EventCommand;

public class EventCommandParser {
    /**
     * Parses the arguments in the context of an EventCommand and returns an EventCommand object for execution.
     *
     */
    public EventCommand parse(String args) {
        String[] argsArr = args.split(" /at ");
        String description = argsArr[0];
        String at = argsArr[1];
        return new EventCommand(description, at);
    }
}
