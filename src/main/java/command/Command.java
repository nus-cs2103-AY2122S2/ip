package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {

    public static boolean isExit = false;

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public static void setIsExit(boolean state) {
        isExit = state;
    }

    public boolean isExit() {
        return isExit;
    }
}