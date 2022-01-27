package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    String[] markCommand;

    public MarkCommand(String[] markCommand) {
        this.markCommand = markCommand;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        int taskToMark = Integer.parseInt(markCommand[1]);
        taskList.markTaskAsDone(taskToMark);

        try {
            storage.TaskListToFile(taskList);
        } catch (IOException e) {
            Ui.printExceptionMessage(e);
        }

        Ui.indicateMarked(taskList.get(taskToMark - 1));
    }
}
