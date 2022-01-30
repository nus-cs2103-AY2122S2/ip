package duke.Command;

import duke.*;
import duke.DukeExceptions.DukeException;
import duke.DukeExceptions.TodoException;
import duke.Task.Task;
import duke.Task.Todo;

import java.io.IOException;

public class TodoCommand extends Command {

    public TodoCommand(String input) {
        super(input);
    }


    /**
     * Add a todo task to the task list, and save it to the data file
     *
     * @param taskList currentTaskList
     * @param ui ui class that helps to print suitable command
     * @param storage storage that manage saving and loading data
     * @throws DukeException an error message
     * @throws IOException error saving to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new TodoException("☹ OOPS!!! The description of a todo cannot be empty.(please insert again)");
        }
        String todoDesription = input.substring(5);
        Task todo = new Todo(todoDesription);
        taskList.add(todo);
        ui.showTodoTaskAdded(todo, taskList);
        storage.saveFile(taskList);
    }
}
