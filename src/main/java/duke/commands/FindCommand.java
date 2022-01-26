package duke.commands;

import duke.tasks.TaskManager;
import duke.ui.UiManager;

public class FindCommand extends Command {
    private String taskName;
    private TaskManager taskManager;
    private UiManager uiManager;
    private Type type;

    public FindCommand(UiManager um, TaskManager tm, String task, Type t) {
        this.uiManager = um;
        this.taskManager = tm;
        this.taskName = task;
        this.type = t;
    }

    @Override
    public void execute() {
        String tasks = this.taskManager.findTasks(this.taskName);
        uiManager.printFind(tasks, this.taskName);
    }
}
