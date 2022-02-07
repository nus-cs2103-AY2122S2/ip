package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents command to list all the tasks in the task list.
 *
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> chosenTasks = tasks.getObjectives();
        return getReply(chosenTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getReply(List<Task> chosenTasks) {
        StringBuilder reply = new StringBuilder();
        reply.append(this.speak(Dialogue.LIST));
        for (int i = 0; i < chosenTasks.size(); i++) {
            reply.append(i + 1);
            reply.append(".");
            reply.append(chosenTasks.get(i));
            reply.append("\n");
        }
        return reply.toString();
    }
}





















