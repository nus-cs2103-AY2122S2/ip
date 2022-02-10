package chatcat.commands;

import java.util.ArrayList;

import chatcat.tasks.Task;
import chatcat.util.WriteToFile;

/**
 * The default DeleteCommand class inherited from {@code Command}.
 *
 * @see Command
 */
public class DeleteCommand extends Command {
    final String INDEX_TO_DELETE;
    Task removed;

    /**
     * Creates a default DeleteCommand {@code Command} object.
     *
     * @param tasks the tasklist {@code ArrayList}.
     * @param writeToFile the class to handle writing of .ser files.
     * @param INDEX_TO_DELETE the index of task {@code Task} in list to be deleted.
     */
    public DeleteCommand(ArrayList<Task> tasks, WriteToFile writeToFile, String INDEX_TO_DELETE) {
        super(tasks, writeToFile);
        this.INDEX_TO_DELETE = INDEX_TO_DELETE;
    }

    /**
     * Deletes the specified task {@code Task} in tasklist.
     *
     * @see Task
     * @see WriteToFile
     */
    public void delete() {
        super.tasks = writeToFile.toRead();
        String[] input = INDEX_TO_DELETE.split(" ");
        int toDelete = Integer.parseInt(input[1]) - 1;

        assert toDelete < super.tasks.size() : "Index is larger than task list size";
        removed = tasks.remove(toDelete);
        writeToFile.toWrite(tasks);
    }

    /**
     * Returns deleted task {@code Task} in String.
     *
     * @return deleted task {@code Task} in String.
     */
    @Override
    public String toString() {
        return "Noted. I've removed this task:\n" + removed + "\n" +
                "Now you have " + super.tasks.size() +
                " tasks in the list.";
    }
}

