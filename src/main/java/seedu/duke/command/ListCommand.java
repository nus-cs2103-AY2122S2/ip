package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Created when user wants to check the {@link Task} in {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList tasks, Storage storage) {
        return tasks;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        return "Here is your to-do:\n" + taskList.printTasks();
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
