package duke.chatbot.command;

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

    /**
     * Gets the index of task to operate on from args passed to
     * command, and parses it to an int.
     *
     * @return Index of task command should operate on.
     * @throws IllegalArgumentException If the index is invalid for current list.
     */
    protected int getTaskIndex() throws IllegalArgumentException {
        try {
            return Integer.parseInt(super.getArgs()) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non-number passed as index for task list");
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
