package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.ChatCatException;
import chatcat.tasks.Task;
import chatcat.util.WriteToFile;
import chatcat.util.OutputMessage;

/**
 * The default FilterCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class FilterCommand extends Command {
    ArrayList<Task> filteredList = new ArrayList<>();
    final String KEYWORD;

    /**
     * Creates a default FilterCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param KEYWORD keyword to filter list of tasks {@code Task}.
     */
    public FilterCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String KEYWORD) {
        super(tasks, writeToFile);
        this.KEYWORD = KEYWORD;
    }

    /**
     * Displays the tasks {@code Task} in tasklist
     * {@code taskList} that includes a specified keyword.
     *
     * @see Task
     */
    public void filter() throws ChatCatException {
        super.tasks = writeToFile.toRead();

        /** Searches the task list and outputs tasks with keyword in filteredList **/
        String[] input = KEYWORD.split(" ");
      
        super.tasks.forEach(task -> {
            if (task.containsKeyword(input[1])) {
                filteredList.add(task);
            }
        });

        if (filteredList.isEmpty()) {
            throw new ChatCatException(OutputMessage.deleteErrorMessage());
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
