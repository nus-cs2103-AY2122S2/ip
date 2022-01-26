package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, BotStoring botStorage, DateTable dateTable)
            throws IOException {
        taskList.findTasksByKeyWord(keyWord);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
