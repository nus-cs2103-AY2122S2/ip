package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import static duke.constant.Message.LIST_TASK;
import static duke.constant.Message.NO_TASK;

/**
 * A representation of the command for listing tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Executes list command.
     *
     * @param tasks   TaskList class
     * @param ui      Ui class
     * @param storage Storage class
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumberOfTasks() == 0) {
            ui.printMessage(NO_TASK);
            return;
        }

        ui.printLine();
        System.out.println(LIST_TASK);
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            int index = i + 1;
            System.out.println("\t" + index + "." + tasks.getTaskByIndex(i).toString());
        }

        ui.printLine();
    }
}
