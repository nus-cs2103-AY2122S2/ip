package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.task.Todo;

public class TodoCommand extends Command {
    private final Todo todo;
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(todo);
//        ui.showAdd(todo, taskList);
        String message = ui.messageForAdd(todo, taskList);
        storage.updateList(taskList);
        return message;
    }
}
