package chatcat.commands;

import java.util.ArrayList;

import chatcat.tasks.Task;
import chatcat.util.WriteToFile;

/**
 * The default ListTaskCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class ListTaskCommand extends Command{

    /**
     * Creates a default ListTaskCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     */
    public ListTaskCommand(ArrayList<Task> tasks, WriteToFile writeToFile) {
        super(tasks, writeToFile);
    }

    /**
     * Prints out all tasks in Task List.
     *
     * @see WriteToFile
     * @see Task
     */
    public void listTasks() {
        tasks = writeToFile.toRead();
    }

    /**
     * Returns a representation in string of Task in list {@code Task}.
     *
     * @return a representation in string of Task in list {@code Task}.
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        assert super.tasks.size() > 0 : "empty list!";

        str.append("Here are the tasks in your list:" + "\n");
        for (int i = 0; i < super.tasks.size(); i++) {
            str.append((i + 1) + ". " + super.tasks.get(i) + "\n");
        }

        return str.toString();
    }
}
