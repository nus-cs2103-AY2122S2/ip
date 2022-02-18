package apollo.commands;

import static apollo.messages.Messages.EMPTY_TASKLIST;

/**
 * Lists {@code Tasks} in taskList.
 * Extends {@code Command} superclass.
 */
public class ListCommand extends Command {

    public static final String HELP_LIST_COMMAND = "Lists all current tasks: list";

    /**
     * Prefixes each list item with index.
     *
     * @return Indexed list in string representation.
     */
    private String getIndexedList() {
        assert taskList.taskCount() != 0 : "Indexing empty taskList.";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.taskCount(); i++) {
            stringBuilder.append("\n").append(i + 1).append(".");
            stringBuilder.append(taskList.getTaskString(i));
        }
        return stringBuilder.toString();
    }

    /**
     * Lists all current tasks in taskList
     *
     * @return Message for successful execution.
     */
    @Override
    public String execute() {
        if (taskList.taskCount() == 0) {
            return EMPTY_TASKLIST;
        }

        return String.format("These are your current tasks. %s",
                getIndexedList());
    }
}
