package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import static duke.constant.Message.LINE_PREFIX;
import static duke.constant.Message.LIST_TASK;
import static duke.constant.Message.NO_TASK;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumberOfTasks() == 0) {
            ui.printMessage(NO_TASK);
            return;
        }

        ui.printLine();
        ui.printMessageWithoutLine(LIST_TASK);
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            int index = i + 1;
            ui.printMessageWithoutLine(LINE_PREFIX + index + "." + tasks.getTaskByIndex(i));
        }

        ui.printLine();
    }
}
