package duke.command;

import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * TodoCommand Class
 */
public class TodoCommand extends Command<String> {

    private TaskList taskList;
    private String inputText;
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
        this.taskList = list;
        this.inputText = description;
        this.storage = storage;
        this.ui = ui;
        runCommand();
    }


    /**
     * add todo task to task list
     */
    @Override
    public void runCommand() {
        ToDo freshToDo = toDoCreator();
        taskList.addTask(freshToDo);
        storage.writeToFile(taskList);
        ui.showAddTodo(freshToDo, taskList);
    }

    /**
     * Creates a ToDo object based on whether priority was specified
     * @return ToDo object
     */
    public ToDo toDoCreator() {
        if (inputText.contains("/pri")) {
            String[] splicedString = inputText.split(" /pri ");
            String description = splicedString[0].substring(5);
            String priority = splicedString[1];
            ToDo freshToDo = new ToDo(description, priority);
            return freshToDo;
        } else {
            String splicedString = inputText.substring(5);
            ToDo freshTodo = new ToDo(splicedString, "");
            return freshTodo;
        }
    }
}
