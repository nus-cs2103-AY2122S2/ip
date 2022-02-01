package duke.commands;

import duke.exception.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command for finding tasks using keywords.
 */
public class FindCommand extends Command<String> {
    private TaskList list;
    private String[] echo;

    /**
     * Constructor for find class to execute the finding of tasks with specific keywords.
     *
     * @param list list to search from
     * @param echo the details of find search
     * @throws DukeException when find command cannot be executed
     */
    public FindCommand(TaskList list, String[] echo) throws DukeException {
        this.list = list;
        this.echo = echo;
        execute();
    }

    private void execute() throws DukeException {
        String err = "Oh no! The description of find cannot be empty... Try again :)\n";
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            throw new DukeException(err);
        }
        int n = 1;
        System.out.print(Ui.getLineDivider() + "Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            String taskDetail = list.getTask(i).toString();
            if (taskDetail.contains(description)) {
                System.out.printf("%d." + taskDetail + "\n", n);
                n = n + 1;
            }
        }
        System.out.print(Ui.getLineDivider());
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop bot from running
     */
    public boolean isExit() {
        return false;
    }
}
