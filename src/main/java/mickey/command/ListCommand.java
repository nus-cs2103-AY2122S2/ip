package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        if (tasks.size() > 0) {
            System.out.println("\tOh boy! You have " + tasks.size() + " tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t\t" + (i + 1) + ". " + tasks.get(i));
            }
        } else {
            System.out.println("\tHooray! You have no tasks");
        }
    }
}
