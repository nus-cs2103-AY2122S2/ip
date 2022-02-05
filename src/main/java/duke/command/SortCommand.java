package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.util.List;

public class SortCommand extends Command {
    private String sortBy;
    private Boolean isAscending;

    public SortCommand (String sortBy) {
        this.sortBy = sortBy;
        this.isAscending = true;
    }

    public SortCommand (String sortBy, Boolean isAscending) {
        this.sortBy = sortBy;
        this.isAscending = isAscending;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        List<Task> sortedList = tasks.sort(sortBy, isAscending);
        storage.overWriteFile(tasks);
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
