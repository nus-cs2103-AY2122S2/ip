package bobby.command;

import bobby.exception.BobbyException;
import bobby.Storage;
import bobby.task.TaskList;
import bobby.task.ToDo;
import bobby.exception.ToDoException;
import bobby.Ui;

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
     * Carries out the respective command's actions.
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @throws BobbyException if an invalid command is given by the user's input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommand.substring(4).isBlank()) {                   // nothing after command
            throw new ToDoException("todo");
        }
        ToDo newToDo = new ToDo(fullCommand.substring(5));
        ui.todoMessage(newToDo);
        tasks.addTask(newToDo);
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
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
