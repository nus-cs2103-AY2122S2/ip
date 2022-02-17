package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Initializes and starts the duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Sets up the required objects for the program.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage.retrieveTaskList());
    }

    /**
     * Generates a response to user input.
     *
     * @param input The full user input string.
     * @return Duke's response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getReminders() {
        ArrayList<Task> upcomingTasks = tasks.findUpcomingTask();
        StringBuilder reminder = new StringBuilder();
        String title = upcomingTasks.isEmpty() ? "You got no upcoming tasks." : "Upcoming Tasks:";
        reminder.append(title);
        for (Task task : upcomingTasks) {
            reminder.append("\n  " + task.toString());
        }
        return reminder.toString();
    }
}
