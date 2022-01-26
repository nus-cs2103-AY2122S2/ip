package duke.commands;

import java.io.IOException;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.tasks.Todo;
import duke.ui.Ui;

public class AddTodoCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;

    public AddTodoCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
        execute();
    }

    public void execute() throws DukeException {
        String err = "Oh no! The description of todo cannot be empty... Try again :)\n";
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            throw new DukeException(err);
        }
        Todo curr = new Todo(description);
        list.addTask(curr);
        Ui.showAddResponse(curr.toString(), list.getSize());
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExit(){
        return false;
    }
}
