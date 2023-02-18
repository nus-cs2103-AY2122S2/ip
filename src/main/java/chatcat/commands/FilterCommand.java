package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.TaskEditException;
import chatcat.tasks.Task;
import chatcat.util.SplitInput;
import chatcat.util.WriteToFile;
import chatcat.util.OutputMessage;

/**
 * The default FilterCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class FilterCommand extends Command {
    ArrayList<Task> filteredList = new ArrayList<>();
    final String STR_WITH_KEYWORD;

    /**
     * Creates a default FilterCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param STR_WITH_KEYWORD keyword to filter list of tasks {@code Task}.
     */
    public FilterCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String STR_WITH_KEYWORD) {
        super(tasks, writeToFile);
        this.STR_WITH_KEYWORD = STR_WITH_KEYWORD;
    }

    /**
     * Gets the tasks {@code Task} in tasklist
     * {@code taskList} that includes a specified keyword.
     *
     * @see Task
     */
    public void filter() throws TaskEditException {
        super.tasks = writeToFile.toRead();

        /* Searches the task list and outputs tasks with keyword in filteredList */
        String keyword = SplitInput.getKeyword(STR_WITH_KEYWORD);
      
        super.tasks.forEach(task -> {
            if (task.containsKeyword(keyword)) {
                filteredList.add(task);
            }
        });

        if (filteredList.isEmpty()) {
            throw new TaskEditException(OutputMessage.emptyListErrorMessage());
        }
    }

    /**
     * Returns a representation in string of filtered
     * {@code Task} tasks that includes on keyword.
     *
     * @return a representation in string of filtered
     * {@code Task} tasks that includes on keyword.
     * @see OutputMessage
     */
    @Override
    public String toString() {
        return OutputMessage.filterMessage(filteredList);
    }
}
