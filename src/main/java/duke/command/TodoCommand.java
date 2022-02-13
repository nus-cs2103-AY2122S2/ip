package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.TodoException;
import duke.extension.CheckDuplicate;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.Todo;

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
     * @return a response to user input
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String result = "";
        String[] parts = input.split(" ");
        if (parts.length == 1) {
            return "☹ OOPS!!! The description of a todoTask cannot be empty.(please insert again)";
        }
        String todoDesription = input.substring(5);
        Task todoTask = new Todo(todoDesription);
        Task duplicated = CheckDuplicate.checkDuplicate(todoTask, taskList);
        if (!duplicated.equals(todoTask)) {
            result += "This task has been added before!\n";
            result += duplicated.toString();
            result += "\n please enter another task";
            return result;
        }
        taskList.add(todoTask);
        result += ui.showTodoTaskAdded(todoTask, taskList);
        storage.saveFile(taskList);
        return result;
    }
}
