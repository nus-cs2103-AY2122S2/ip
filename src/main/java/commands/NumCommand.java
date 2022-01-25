package commands;

import exceptions.InvalidOperationException;
import tasks.TaskManager;
import ui.UiManager;

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
            }
        } catch (IndexOutOfBoundsException e) {
            uiManager.errorMessage("I don't think we have that task!\nUse 'list' to check");
        } catch (InvalidOperationException e) {
            uiManager.errorMessage(e.toString());
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
            }
    }

}
