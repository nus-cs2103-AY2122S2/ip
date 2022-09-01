package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import funbox.task.ToDo;

/**
 * Deal with handling command for ToDo.
 */
public class ToDoCommand extends Command {
    String description;

    /**
     * Constructor class for ToDoCommand.
     *
     * @param description The description of the task.
     */
    public ToDoCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Execute the command which add ToDo task.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions If description == ""
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        String result;
        assert ui != null : "ui should not be null";
        assert storage != null : "ui should not be null";

        if (description.equals("")) {
            throw new FunBoxExceptions("`todo` command usage: todo <task>");
        }

        ToDo todo = new ToDo(description, "todo");
        taskList.add(todo);

        result = ui.taskPrefix().concat(ui.printTask(taskList.getSize(), todo));
        storage.writeTaskToStorage(todo);

        return result;
    }
}
