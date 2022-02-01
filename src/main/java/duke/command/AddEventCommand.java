package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.task.Event;

/**
 * Represents a command that will add an Event Task to the TaskList upon execution..
 */
public class AddEventCommand extends Command {
    private String task;
    private String at;

    /**
     * A constructor to store the name of the Task and the actual event date.
     *
     * @param task The name of the task.
     * @param at The actual date of the event in yyyy-mm-dd format.
     */
    public AddEventCommand(String task, String at) {
        super();
        this.task = task;
        this.at = at;
    }

    /**
     * Executes the command by adding an Event task into the TaskList.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Event event = new Event(task, at);
        taskList.add(event);
        String response = "Got it. I've added this task:" + "\n";
        response += event.toString() + "\n";
        response += "Now you have " + taskList.numOfTasks() + " tasks in the list." + "\n";
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return response;
    }

    /**
     * Returns true if it is an exit command and false otherwise.
     * @return a boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
