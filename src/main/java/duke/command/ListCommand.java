package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Responsible for the functionality needed when listing out all the task.
 */
public class ListCommand extends Command{

    /**
     * Constructor to create List duke.command.Command.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Returns the string containing list of tasks.
     *
     * @param taskList duke.task.Task list loaded to the chat bot.
     * @return list of tasks as a string.
     */
    private static String listToString(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        Task task;
        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            stringBuilder.append((i + 1) + "." + task.toString());
            if (i != taskList.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Prints out the list of tasks.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String output = listToString(taskList);
        ui.showMessage(output);
    }
}
