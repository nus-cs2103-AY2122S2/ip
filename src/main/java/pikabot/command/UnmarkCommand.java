package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {

    String[] unmarkCommand;

    public UnmarkCommand(String[] markCommand) {
        this.unmarkCommand = markCommand;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        int taskToUnmark = Integer.parseInt(unmarkCommand[1]);
        taskList.markTaskAsUndone(taskToUnmark);

        try {
            storage.TaskListToFile(taskList);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }

        Ui.indicateUnmarked(taskList.get(taskToUnmark - 1));
    }
}
