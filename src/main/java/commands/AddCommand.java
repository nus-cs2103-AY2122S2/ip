package commands;

import tasks.Task;

import tasklist.TaskList;
import tasklist.TaskListException;

import ui.Ui;

public class AddCommand extends Command {
    public static final String ADD_TODO_COMMAND = "todo";
    public static final String ADD_DEADLINE_COMMAND = "deadline";
    public static final String ADD_EVENT_COMMAND = "event";

    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            taskList.add(this.taskToAdd);
            ui.showAddTask(this.taskToAdd, taskList.size());
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
