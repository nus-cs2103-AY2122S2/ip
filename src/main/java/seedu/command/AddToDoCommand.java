package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;
import seedu.task.ToDo;

/**
 * Adds task of type ToDo to task list based on details provided by user input.
 */
public class AddToDoCommand extends Command {
    private final ToDo toDoTask;

    public AddToDoCommand(String taskDetails) {
        assert taskDetails != null : "AddToDoCommand->AddToDoCommand: To do details cannot be null.";
        assert taskDetails.length() > 0 : "AddToDoCommand->AddToDoCommand: To do details cannot be empty.";

        this.toDoTask = new ToDo(taskDetails);
    }

    /**
     * Executes the add command to add a new ToDo task to the task list and write
     * the modified task list back to the storage.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return Display message if the task has been added to the list successfully.
     * @throws DukeException  If task list cannot be written back to storage location.
     */
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "AddToDoCommand->run: Tasks list cannot be null.";
        assert storage != null : "AddToDoCommand->run: Storage cannot be null.";

        taskList.add(toDoTask);
        try {
            storage.write(taskList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        }

        return "Got it. I've added this task:\n" + toDoTask.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
