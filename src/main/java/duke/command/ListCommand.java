package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import static duke.constant.Message.LINE_PREFIX;
import static duke.constant.Message.LINE_SEPARATOR;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumberOfTasks() == 0) {
            return ui.printMessage(NO_TASK);
//            return;
        }
        String result = "";

        result += ui.printLine();
        result += LINE_SEPARATOR;
        result += ui.printMessageWithoutLine(LIST_TASK);
        result += LINE_SEPARATOR;
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            int index = i + 1;
            result += ui.printMessageWithoutLine(LINE_PREFIX + index + "." + tasks.getTaskByIndex(i));
            result += LINE_SEPARATOR;
        }

        result += ui.printLine();
        return result;
    }
}
