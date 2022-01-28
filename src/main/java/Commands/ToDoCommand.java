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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        Tasks.ToDo toDoTask = new Tasks.ToDo(this.commandBody);
        tasks.add(toDoTask);
        storage.save(tasks);
        ui.showSuccessfulAdd(toDoTask, tasks.getSize());
    }
}
