package luca.command;

import java.util.stream.IntStream;

import luca.storage.Storage;
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
        IntStream.range(0, taskList.size())
                .forEach(index -> stringBuilder.append((index + 1) + "."
                        + taskList.get(index).toString() + "\n"));

        return stringBuilder.toString().trim();
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
