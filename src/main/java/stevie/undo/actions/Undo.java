package stevie.undo.actions;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

public interface Undo {
    String undo(TaskList tasks, TaskDataHandler storage, StevieUi ui);
}
