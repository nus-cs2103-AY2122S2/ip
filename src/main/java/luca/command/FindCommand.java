package luca.command;

import luca.storage.Storage;
import luca.task.Task;
import luca.task.TaskList;

public class FindCommand extends Command {

    /** Keyword used for search. */
    private String keyword;

    /**
     * Constructor to create FindCommand.
     */
    public FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    /**
     * Loops through the list of tasks and outputs string of tasks
     * containing the keyword in the description.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @retrun response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");
        int startIndex = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(keyword)) {
                startIndex++;
                stringBuilder.append("" + startIndex + "." + task.toString() + "\n");
            }
        }

        if (startIndex > 0) {
            String output = stringBuilder
                    .deleteCharAt(stringBuilder.length() - 1)
                    .toString();
            return output;
        } else {
            return "Sorry, I was unable to find any matching tasks.";
        }
    }
}
