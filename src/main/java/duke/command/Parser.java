package duke.command;
import duke.DukeException;

/**
 * Helper class to parse user input. 
 */
public final class Parser {
    
    /**
     * Interprets user input & return correpsonding command. 
     * @param userInput user input String representing user command. 
     * @return A Command corrsponding to the user input. 
     * @throws DukeException Error
     */
    public static Command parse(String userInput) throws DukeException {
        //
        try {
            String[] userInputString = userInput.split(" ", 2);
            String commandString = userInputString[0];
            CommandType commandType = CommandType.getCommand(commandString);
            String description = userInputString.length > 1 ? userInputString[1] : "" ;

            switch(commandType) {
            case BYE:
                return new ExitCommand(commandType);
            case DELETE:
                return new DeleteCommand(commandType, description);
            case LIST:
                return new ListCommand(commandType);
            case MARK:
                return new MarkCommand(commandType, description, true);
            case UNMARK:
                return new MarkCommand(commandType, description, false);
            case TODO:
                // Fallthrough
            case EVENT:
                // Fallthrough
            case DEADLINE:
                return new AddCommand(commandType, description);
            }
        } catch (DukeException e) {
            // propagate the error higher up
            throw e;
        }

        return null;
    }

}
