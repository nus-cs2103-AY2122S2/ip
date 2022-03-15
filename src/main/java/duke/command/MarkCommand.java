package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Represents command to mark a task in the task list.
 *
 */
public class MarkCommand extends Command {
    protected boolean isMark;
    protected Integer index;

    /**
     * Constructor for MarkCommand class
     * @param index Index of the Task that is marked.
     * @param mark Whether the Task is marked as completed or uncompleted.
     */
    public MarkCommand(Integer index, boolean mark) {
        this.isMark = mark;
        this.index = index - 1;
    }
    @Override
    public String execute(TaskList task, Storage storage) throws IOException {
        task.mark(this.index, this.isMark);
        storage.overWriteFile(task);
        return getReply(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getReply(TaskList task) {
        StringBuilder reply = new StringBuilder();
        if (this.isMark) {
            reply.append(this.speak(Dialogue.MARK));
        } else {
            reply.append(this.speak(Dialogue.UNMARKED));
        }
        reply.append("\n");
        reply.append(task.getTask(this.index).toString());
        return reply.toString();
    }
}
