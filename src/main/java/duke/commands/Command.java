package duke.commands;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.util.Arrays;

public abstract class Command {

    public static Command of(Keywords keyword, String[] tokens) throws ChiException {
        switch(keyword.toString()) {
        case "ADD" :
            return new AddCommand(tokens);
        case "DELETE" :
            return new DeleteCommand(Arrays.copyOfRange(tokens, 1, tokens.length ));
        case "MARK" :
            return new MarkCommand(Arrays.copyOfRange(tokens, 1, tokens.length ));
        case "UNMARK":
            return new UnmarkCommand(Arrays.copyOfRange(tokens, 1, tokens.length ));
        case "FIND":
            return new FindCommand(Arrays.copyOfRange(tokens, 1, tokens.length ));
        case "LIST":
            return new ListCommand();
        default:
            throw new ChiException("Could not create command nyan!");
        }
    }

    public abstract String execute(TaskList tl, Storage sge) throws ChiException;


}
