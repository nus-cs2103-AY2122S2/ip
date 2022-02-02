package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.chatbot.Ui;

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
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here is your to-do:");
        tasks.printTasks();
        return tasks;
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
