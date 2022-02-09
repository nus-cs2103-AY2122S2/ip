package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.task.TaskList;
import bobby.task.ToDo;


/**
 * Represents a 'todo' command
 */
public class ToDoCommand extends Command {
    /** The name of the ToDo task */
    private String taskName;

    /**
     * Creates a ToDoCommand object.
     *
     * @param taskName Name of the ToDo task
     */
    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Bobby's reply to the command.
     * @throws BobbyException if an invalid command is given by the user's input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ToDo newToDo = new ToDo(taskName);
        tasks.addTask(newToDo);
        storage.saveTasks(tasks.getTaskList());
        return ui.todoMessage(newToDo) + "\n" + ui.printNumTasks(tasks);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are ToDoCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ToDoCommand;
    }
}
