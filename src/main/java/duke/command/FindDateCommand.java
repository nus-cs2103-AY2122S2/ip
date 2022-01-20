package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents command to find all the tasks in the task list that matches a date.
 *
 */
public class FindDateCommand extends Command {
    private LocalDate keyword;

    public FindDateCommand(String keyword) {
        this.keyword = LocalDate.parse(keyword);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        List<Task> t = tasks.getObjectives(keyword);
        StringBuilder reply = new StringBuilder();
        reply.append(this.speak(Dialogue.FINDDATE));
        for (int i = 0; i < t.size(); i++) {
            reply.append(i + 1);
            reply.append(".");
            reply.append(t.get(i));
            reply.append("\n");
        }
        return reply.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
