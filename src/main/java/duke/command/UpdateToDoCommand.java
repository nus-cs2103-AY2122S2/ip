package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a command that will update an Event Task to the TaskList upon execution.
 */
public class UpdateToDoCommand extends Command {
    private int taskNo;
    private String taskName;

    /**
     * A constructor to store the taskNo and the details to be updated.
     *
     * @param arguments The arguments that may or may not contain the details.
     * @throws DukeException If a task number was not provided or if there is nothing to update.
     */
    public UpdateToDoCommand(String arguments) throws DukeException {
        super();
        try {
            this.taskNo = Integer.parseInt(arguments.split(" ")[2]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a task number!");
        }
        String[] details = arguments.split("/details");
        if (details.length == 1) {
            throw new DukeException("Please enter details for me to update!");
        }
        this.taskName = details[1];
    }

    /**
     * Executes the command by updating the specified ToDo appropriately.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage  A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If the task type provided was not correct or if there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.getTask(taskNo);
        if (!(task instanceof ToDo)) {
            throw new DukeException("Please enter the correct task type!");
        }
        if (taskName == null) {
            taskList.update(taskNo, new ToDo(task.getTaskName()));
        } else {
            taskList.update(taskNo, new ToDo(taskName));
        }
        String response = "Got it. I've updated this task:" + "\n";
        response += taskList.getTask(taskNo) + "\n";
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return response;
    }

    /**
     * Returns true if it is an exit command and false otherwise.
     *
     * @return A boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
