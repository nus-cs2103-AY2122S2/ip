package commands;

import tasks.Task;

import ui.Ui;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasklist.TaskListException.TaskNotFoundException;

import java.util.NoSuchElementException;

public class MarkCommand extends Command {
    public static final String COMMAND = "mark";

    private final int id;

    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            final Task taskToMark = taskList.getById(this.id).get();
            taskToMark.markAsDone();
            taskList.update(this.id, taskToMark);
            ui.showMarkTask(taskToMark);
        } catch (TaskNotFoundException | NoSuchElementException ex) {
            ui.showError("Task to mark doesn't exist");
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
