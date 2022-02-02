package Commands;

import Tasks.TaskList;
import Tasks.ToDo;
import Tasks.Task;
import util.Storage;
import util.Ui;


import Exceptions.DukeException;
import Exceptions.EmptyDescriptionException;

import java.io.IOException;

public class ToDoCommand extends DukeCommand {

    public ToDoCommand(String description) {
        super(description);
    }

    /**
     * Executes when the user inputs the keyword "todo"
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        Tasks.ToDo toDoTask = new Tasks.ToDo(this.commandBody);
        tasks.add(toDoTask);
        storage.save(tasks);
        return ui.showSuccessfulAdd(toDoTask, tasks.getSize());
    }
}
