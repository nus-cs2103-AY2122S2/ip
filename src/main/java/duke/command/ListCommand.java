package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a List command to display all the list
 */
public class ListCommand extends Command {

    /**
     * call and format the string out put of list.
     *
     * @param tasks the entire TaskList.
     * @param ui the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder text = new StringBuilder();
        text.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            text.append("    ").append(i + 1).append(". ")
                    .append(tasks.getByIndex(i))
                    .append("\n");
        }
        text.delete(text.length() - 1, text.length());
        ui.showMessage(text.toString());
    }
}
