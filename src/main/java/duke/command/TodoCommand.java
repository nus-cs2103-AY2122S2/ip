package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * TodoCommand Class
 */
public class TodoCommand extends Command<String> {

    private TaskList list;
    private String description;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for ToDoCommand
     * @param description unformatted string for todo
     * @param list task list to add this task to
     * @param storage storage of where this task will be added to
     * @param ui ui of subsequent system out to user
     */
    public TodoCommand(String description, TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.description = description;
        this.storage = storage;
        this.ui = ui;
        runCommand();
    }


    /**
     * add todotask to task list
     */
    @Override
    public void runCommand() {
        String splicedString = description.substring(5);
        ToDo freshTodo = new ToDo(splicedString);
        list.addTask(freshTodo);
        storage.writeToFile(list);
        ui.showAddTodo(freshTodo, list);
    }
}