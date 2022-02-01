package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.task.Deadline;

/**
 * Represents a command that will add a Deadline Task to the TaskList upon execution..
 */
public class AddDeadlineCommand extends Command {
    private String task;
    private String by;

    /**
     * A constructor to store the name of the Task and the actual deadline date.
     *
     * @param task The name of the task.
     * @param by The actual deadline of the task in yyyy-mm-dd format.
     */
    public AddDeadlineCommand(String task, String by) {
        super();
        this.task = task;
        this.by = by;
    }

    /**
     * Executes the command by adding a deadline task into the TaskList.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(task, by);
        taskList.add(deadline);
        String response = "Got it. I've added this task:" + "\n";
        response += deadline.toString() + "\n";
        response += "Now you have " + taskList.numOfTasks() + " tasks in the list." + "\n";
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return response;
    }

    /**
     * Returns True if it is an exit command and false otherwise.
     * @return A boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
