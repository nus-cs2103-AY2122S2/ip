package duke.commands;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command that lists out the task previously added.
 */
public class ListCommand extends Command<String> {
    private final TaskList list;

    /**
     * Constructor of this class to initialise this command to list out.
     *
     * @param list of tasks by user
     * @throws DukeException thrown when command cannot be executed
     */
    public ListCommand(TaskList list) throws DukeException {
        this.list = list;
        execute();
    }

    /**
     * Runs the command to list out tasks for user to see.
     *
     * @throws DukeException if execution is not possible
     */
    private void execute() throws DukeException {
        System.out.print(Ui.lineDivider + "Here are the tasks in your list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.printf("%d." + list.getTask(i).toString() + "\n", i+1);
        }
        System.out.print(Ui.lineDivider);
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop the bot from running
     */
    public boolean isExit() {
        return false;
    }
}
