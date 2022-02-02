package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.ToDoException;
import bobby.task.TaskList;
import bobby.task.ToDo;


/**
 * Represents a 'todo' command
 */
public class ToDoCommand extends Command {
    /** The full user input command */
    private String fullCommand;
    /** The full user input command in array form */
    private String[] fullCommandArr;

    /**
     * Creates a ToDoCommand object.
     *
     * @param fullCommand User input command
     * @param fullCommandArr User input command in array form, split by a whitespace
     */
    public ToDoCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
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
        if (fullCommand.substring(4).isBlank()) { // nothing after command
            throw new ToDoException("todo");
        }
        ToDo newToDo = new ToDo(fullCommand.substring(5));
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
