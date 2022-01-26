package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import funbox.task.ToDo;

public class ToDoCommand extends Command {
    String description;

    public ToDoCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        if (description.equals("")) {
            throw new FunBoxExceptions("`todo` command is missing a field!");
        } else {
            ToDo todo = new ToDo(this.description, "todo");
            taskList.add(todo);
            ui.printTask(taskList.getSize(), todo);
            storage.writeTaskToStorage(todo, ui);
        }
    }
}
