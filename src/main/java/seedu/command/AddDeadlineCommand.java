package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;
import seedu.task.Deadline;

/**
 * Adds task of type Deadline to task list based on details provided by user input.
 */
public class AddDeadlineCommand extends Command {
    private Deadline deadlineTask;

    public AddDeadlineCommand(String deadlineDetails) throws DukeException {
        assert deadlineDetails != null : "AddDeadlineCommand->AddDeadlineCommand: "
                + "Deadline details cannot be null.";
        assert deadlineDetails.length() > 0 : "AddDeadlineCommand->AddDeadlineCommand: "
                + "Deadline details cannot be empty.";

        try {
            String description = deadlineDetails.split(" /by ")[0];
            String dateTime = deadlineDetails.split(" /by ")[1];
            this.deadlineTask = new Deadline(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Sorry your deadline details is in the wrong format, please use: "
                    + "\n [deadline description] /by [DD-MM-YYYY]");
        }
    }

    /**
     * Executes the add command to add a new Deadline task to the task list and write
     * the modified task list back to the storage.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage Object to write tasks back to.
     * @return Display message that task has been added to the list successfully.
     * @throws DukeException  If task list cannot be written back to storage location.
     */
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "AddDeadlineCommand->run: Task list cannot be null.";
        assert storage != null : "AddDeadlineCommand->run: Storage cannot be null.";

        taskList.add(deadlineTask);
        try {
            storage.write(taskList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        }

        String result = "Got it. I've added this task:\n" + deadlineTask.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        return result;
    }
}
