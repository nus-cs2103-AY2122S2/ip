package commands;

import tasks.Task;

import ui.Ui;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasklist.TaskListException.TaskNotFoundException;

import java.util.NoSuchElementException;

public class UnmarkCommand extends Command {
    public static final String COMMAND = "unmark";

    private final int id;

    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            final Task taskToUnmark = taskList.getById(this.id).get();
            taskToUnmark.markAsUndone();
            taskList.update(this.id, taskToUnmark);
            ui.showUnmarkTask(taskToUnmark);
        } catch (TaskNotFoundException | NoSuchElementException ex) {
            ui.showError("Task to unmark doesn't exist");
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
