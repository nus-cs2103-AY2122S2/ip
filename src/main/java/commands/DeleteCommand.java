package commands;

import tasks.Task;

import ui.Ui;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasklist.TaskListException.TaskNotFoundException;

import java.util.Optional;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            final Optional<Task> taskToRemove = taskList.getById(this.id);
            taskList.remove(this.id);
            ui.showDeleteTask(taskToRemove.get(), taskList.size());
        } catch (TaskNotFoundException ex) {
            ui.showError("Task to remove doesn't exist");
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
