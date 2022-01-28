package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");
        int startIndex = 0;
        for(int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(keyword)) {
                startIndex++;
                stringBuilder.append("" + startIndex + "." + task.toString() + "\n");
            };
        }

        if (startIndex > 0) {
            String output = stringBuilder
                    .deleteCharAt(stringBuilder.length() - 1)
                    .toString();
            ui.showMessage(output);
        } else {
            ui.showMessage("Sorry, I was unable to find any matching tasks.");
        }
    }
}
