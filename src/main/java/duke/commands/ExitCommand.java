package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.saveTaskList(tasks);
        return EXIT_MESSAGE;
    }
}
