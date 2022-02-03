package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.task.Todo;

/**
 * Represents the logic of adding a <code>Todo</code>.
 */
public class TodoCommand extends Command {
    private final Todo todo;

    /**
     * Constructs a <code>TodoCommand</code>.
     * @param description The description of the <code>Todo</code>.
     */
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Executes the adding of a <code>Todo</code>,
     * updates the text file and returns the execution message.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code> to unmark.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(todo);
        //ui.showAdd(todo, taskList);
        String message = ui.messageForAdd(todo, taskList);
        storage.updateList(taskList);
        return message;
    }
}
