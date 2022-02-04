package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of showing all the tasks currently stored.
 */
public class ListCommand extends Command {
    /**
     * Constructor for this class.
     *
     * @param commandEnum CommandEnum indicating a specific type of command.
     * @param fullCommand Full information required for execution.
     */
    public ListCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    /**
     * Returns whether this command is a terminal command.
     *
     * @return False as ListCommand is not a terminal command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of returning all the tasks currently stored by the user.
     *
     * @param tasks TaskList responsible for returning all the tasks.
     * @param ui Ui responsible for printing out the final messages displayed to the user.
     * @param storage Storage.
     * @throws BotException When there are unnecessary terms attached other than list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length > 1) {
            throw new BotException("As an IstjBot, I cannot understand more than list.");
        }

        String list = tasks.tasksToString();

        return ui.showTasks(list);
    }
}
