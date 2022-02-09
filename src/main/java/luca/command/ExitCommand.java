package luca.command;

import luca.storage.Storage;
import luca.task.TaskList;
/**
 * Responsible for the functionality needed when exiting the chat bot.
 */
public class ExitCommand extends Command {

    /**
     * Constructor to create ExitCommand.
     */
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Outputs the exit message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Bye. See you again soon!";
    }
}
