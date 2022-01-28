package stevie.command;

import stevie.exception.StevieException;
import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

public class MarkCommand extends Command {
    private final boolean isDone;
    private final int taskIdx;

    public MarkCommand(boolean mark, int idx) {
        isDone = mark;
        taskIdx = idx;
    }

    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        String out;
        try {
            if (isDone) {
                out = tasks.markDone(taskIdx);
            } else {
                out = tasks.markUndone(taskIdx);
            }
        } catch (StevieException ex) {
            ui.outputMessage(ex.getMessage());
            return false;
        }
        tasks.save(storage);
        ui.outputMessage(out);
        return false;
    }
}
