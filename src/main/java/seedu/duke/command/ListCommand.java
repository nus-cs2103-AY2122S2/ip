package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here is your to-do:");
        tasks.printTasks();
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
