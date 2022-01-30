package apollo.commands;

/**
 * Finds a {@code Task} from the taskList that contains the keyword.
 * Extends {@code Command} superclass.
 */
public class FindCommand extends Command {

    private final String keyword;
    private int index;
    private final StringBuilder results;

    /**
     * Constructor for {@code FindCommand}.
     *
     * @param keyword To be used for search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
        index = 1;
        results = new StringBuilder();
        results.append("Here are your related tasks. ");
    }

    /**
     * Appends matching tasks to results.
     *
     * @param result Task containing keyword.
     */
    private void appendToResults(String result) {
        results.append("\n").append(index).append(".");
        results.append(result);
        index++;
    }

    /**
     * Finds all descriptions of tasks that contain the keyword.
     *
     * @return Tasks containing keyword.
     */
    @Override
    public String execute()  {
        for (int i = 0; i < taskList.taskCount(); i++) {
            if (taskList.getTaskDescription(i).toLowerCase().contains(keyword)) {
                appendToResults(taskList.getTaskString(i));
            }
        }

        if (index == 1) {
            return "I could not find any related tasks. ";
        }
        return results.toString();
    }
}
