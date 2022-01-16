package duke.command;
import java.io.IOException;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;

/**
 * Command represents the instructions interpreted by the parser.
 * It is to be executed by Duke.
 */
public abstract class Command {

    /**
     * Execute the action of the command.
     *
     * @param task TaskList stored in Duke.
     * @param ui Ui of Duke.
     * @param storage File storage of Duke.
     * @throws IOException If error in IO operation with Duke storage.
     */
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws IOException;

    /**
     * Returns whether Duke should exit after the command is executed.
     *
     * @return True if Duke should exit afterwards.
     */
    public abstract boolean isExit();
}
