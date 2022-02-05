package luca.command;

import luca.storage.Storage;
import luca.task.Task;
import luca.task.TaskList;

/**
 * Responsible for the functionality needed when listing out all the task.
 */
public class ListCommand extends Command {

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
        if (taskList.size() == 0) {
            return "There are no tasks on your list.";
        }

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
     * Returns string with the list of tasks.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string/
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String output = listToString(taskList);
        return output;
    }
}
