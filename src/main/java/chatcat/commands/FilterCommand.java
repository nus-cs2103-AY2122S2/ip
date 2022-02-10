package chatcat.commands;

import java.util.ArrayList;

import chatcat.chatcatexception.ChatCatException;
import chatcat.tasks.Task;
import chatcat.util.WriteToFile;

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
     * Displays the tasks {@code Task} in tasklist {@code taskList} that includes a specified keyword.
     *
     * @see Task
     */
    public void filter() throws ChatCatException {
        super.tasks = writeToFile.toRead();
        String[] input = KEYWORD.split(" ");

        super.tasks.forEach(task -> {
            if (task.containsKeyword(input[1])) {
                filteredList.add(task);
            }
        });

        if (filteredList.isEmpty()) {
            throw new ChatCatException("No task with keyword: " + "str");
        }
    }

    /**
     * Returns a representation in string of filtered {@code Task} tasks that includes on keyword.
     *
     * @return a representation in string of filtered {@code Task} tasks that includes on keyword.
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        str.append("Here are the matching tasks in your list:" + "\n");
        for (int i = 0; i < filteredList.size(); i++) {
            str.append((i + 1) + ". " + filteredList.get(i) + "\n");
        }
        str.append("");

        return str.toString();
    }
}
