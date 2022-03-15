package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.exceptions.DukeException;
import duke.exceptions.DuplicateException;

/**
 * Represents a <code>TodoCommand</code> which is called to add a Todo task.
 */

public class TodoCommand implements Command {
    private Todo toAdd;

    /**
     * Constructor which takes in the relevant Todo.
     * @param input
     */
    public TodoCommand(String input) {
        this.toAdd = new Todo(input.split("todo")[1].trim());
    }

    /**
     * Utility method to get the Task name.
     * @return the Todo name.
     */
    public String getName() {
        return this.toAdd.getName();
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        if (tasks.containsTask(toAdd.getName())) {
            throw new DuplicateException(toAdd.getName());
        }
        tasks.addTask(toAdd);
        response += "Got it. I've added this task: \n";
        response += toAdd + "\n";
        response += "Now you have " + tasks.getSize() + " tasks in the list.";
        return response;
    }
}
