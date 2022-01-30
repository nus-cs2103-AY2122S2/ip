package duke.ui.command;

import duke.data.TaskList;

/**
 * Command which operates on the TaskList
 * maintained by ChatBot
 */
abstract class TaskListCommand extends Command {
    /** Task list maintained by ChatBot */
    private final TaskList taskList;

    public TaskListCommand(String name, String args, TaskList taskList) {
        super(name, args);
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
