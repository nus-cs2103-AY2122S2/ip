package duke.commands;

import duke.exceptions.InvalidOperationException;
import duke.tasks.TaskManager;
import duke.ui.UiManager;

public class NumCommand extends Command {
    private Integer num;
    private TaskManager taskManager;
    private UiManager uiManager;
    private Type type;

    public NumCommand(UiManager um, TaskManager tm, String task, Type t) {
        this.taskManager = tm;
        this.uiManager = um;
        this.type = t;
        this.num = Integer.parseInt(task) - 1;
    }

    public void insert() {
        try {
            switch (type) {
            case MARK:
                taskManager.labelDone(num);
                break;
            case UNMARK:
                taskManager.labelUndone(num);
                break;
            case DELETE:
                taskManager.remove(num);
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            uiManager.showErrorMessage("I don't think we have that task!\nUse 'list' to check");
        } catch (InvalidOperationException e) {
            uiManager.showErrorMessage(e.toString());
        }
    }

    public void execute() throws IndexOutOfBoundsException, InvalidOperationException {
            switch (type) {
            case MARK:
                taskManager.mark(num);
                break;
            case UNMARK:
                taskManager.unmark(num);
                break;
            case DELETE:
                taskManager.delete(num);
                break;
            }
    }
}
