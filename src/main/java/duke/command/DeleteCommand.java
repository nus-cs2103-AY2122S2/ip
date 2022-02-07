package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Represents command to delete a task from the task list.
 *
 */
public class DeleteCommand extends Command {
    protected Integer index;

    public DeleteCommand(Integer index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList task, Storage storage) throws IOException {
        String deletedTask = (task.getTask(this.index)).toString();
        task.delete(this.index);
        storage.overWriteFile(task);
        return getReply(deletedTask, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getReply(String deletedTask, TaskList task) {
        StringBuilder reply = new StringBuilder();
        reply.append(this.speak(Dialogue.DELETE));
        reply.append("\n");
        reply.append(deletedTask);
        reply.append("\n");
        reply.append(this.speak(Dialogue.NUMLEFT, task.getSize()));

        assert !reply.toString().equals("") : "Description of task deleted cannot be empty";

        return reply.toString();
    }
}
