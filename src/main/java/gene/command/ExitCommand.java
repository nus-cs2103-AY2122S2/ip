package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;

/**
 * The exit command class. Exits the program
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class ExitCommand extends Command{

    /**
     * The execute methods contains the instruction(s) for when the command is
     * to be executed. Nothing happens other than the goodbye statement
     * being printed
     *
     * @param tasks the list of tasks
     * @param userInt the Ui class object
     * @param storage the storage class object
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) {
        return "--------------------------------" +
                "------------------------\n"
                + "Bye. Hope to see you again soon!"
                + "\n"
                + "--------------------------------------------------------";
    }

    /**
     * The method to distinguish this command from other commands
     *
     * @return must return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
