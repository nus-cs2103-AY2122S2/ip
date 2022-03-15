package duke.commands;

import duke.tasks.TaskList;
/**
 * Represents a <code>ListCommand</code> which is called to list all entries.
 */
public class ListCommand implements Command {


    @Override
    public String execute(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "Duke currently has no tasks!";
        }
        return tasks.getTasksAsString();
    }
}
