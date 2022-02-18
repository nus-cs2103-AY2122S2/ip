package duke.Commands;

import duke.Tasks.TaskList;
import duke.Tasks.ToDo;
import duke.util.Storage;
import duke.util.Ui;
import duke.Exceptions.DukeException;

import java.io.IOException;

/**
 * ToDoCommand is a subclass of DukeCommand that is created when the user inputs "todo"
 */
public class ToDoCommand extends DukeCommand {

    public ToDoCommand(String description) {
        super(description);
    }

    /**
     * Creates a todo object and adds it into the list
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        ToDo toDoTask = new ToDo(this.commandBody);
        tasks.add(toDoTask);
        storage.save(tasks);
        return ui.showSuccessfulAdd(toDoTask, tasks.getSize());
    }
}
