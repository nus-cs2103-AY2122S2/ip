package duke.commands;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.util.Arrays;

/**
 * An abstract class representing a command in Chi Bot.
 *
 * @author WJunHong-reused
 * Reused from https://github.com/mslevis/ip/blob/master/src/main/java/aoi/commands/Command.java
 * with modifications.
 */
public abstract class Command {

    /**
     * Returns a new command of some type based on the keyword supplied.
     * @param keyword The keyword used to match the command.
     * @param tokens The entire content of the user message.
     * @return A new command of the keyword type.
     * @throws ChiException If are some problems in creating the command.
     */
    public static Command of(Keywords keyword, String[] tokens) throws ChiException {
        switch(keyword.toString()) {
        case "ADD" :
            return new AddCommand(tokens);
        case "DELETE" :
            return new DeleteCommand(Arrays.copyOfRange(tokens, 1, tokens.length));
        case "MARK" :
            return new MarkCommand(Arrays.copyOfRange(tokens, 1, tokens.length));
        case "UNMARK":
            return new UnmarkCommand(Arrays.copyOfRange(tokens, 1, tokens.length));
        case "FIND":
            return new FindCommand(Arrays.copyOfRange(tokens, 1, tokens.length));
        case "LIST":
            return new ListCommand();
        default:
            throw new ChiException("Could not create command nyan!");
        }
    }

    /**
     * Executes an appropriate action to generate the response to a user's message.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A string response for respective command.
     * @throws ChiException If there is something wrong with format or content of message.
     */
    public abstract String execute(TaskList tl, Storage sge) throws ChiException;


}
