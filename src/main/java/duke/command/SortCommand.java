package duke.command;

import java.io.IOException;
import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


public class SortCommand extends Command {
    private String sortBy;
    private Boolean isAscending;

    /**
     * Constructor for SortCommand.
     * Task is marked as true as default.
     *
     * @param sortBy keyword to sort by
     */
    public SortCommand (String sortBy) {
        this.sortBy = sortBy;
        this.isAscending = true;
    }
    /**
     * Constructor for SortCommand.
     *
     * @param sortBy        the sortBy
     * @param isAscending the isAscending
     */
    public SortCommand (String sortBy, Boolean isAscending) {
        this.sortBy = sortBy;
        this.isAscending = isAscending;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        List<Task> sortedList = tasks.sort(sortBy, isAscending);
        storage.overWriteFile(tasks);
        return getReply(sortedList);
    }

    private String getReply(List<Task> sortedList) {
        StringBuilder reply = new StringBuilder();
        reply.append(this.speak(Dialogue.LIST));
        for (int i = 0; i < sortedList.size(); i++) {
            reply.append(i + 1);
            reply.append(".");
            reply.append(sortedList.get(i));
            reply.append("\n");
        }
        return reply.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
