package siri;

import java.io.IOException;

//AddTodoCommand.java reused from Brigette Santoso E0564307
/**
 * Handles the creation and adding of todo tasks into list
 */
public class AddTodoCommand extends Command {
    protected String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        Task task = new Todo(description, "T");
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
