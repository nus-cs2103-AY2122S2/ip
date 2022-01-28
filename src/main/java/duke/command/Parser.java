package duke.command;
import duke.DukeException;

// helper class to parse user input
public final class Parser {
    
    // interpret user input & return correpsonding command 
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
                return new AddCommand(commandType, description);
            case EVENT:
                return new AddCommand(commandType, description);
            case DEADLINE:
                return new AddCommand(commandType, description);
            }
        } catch (DukeException e) {
            // TODO 
        }

        return null;
    }

}
