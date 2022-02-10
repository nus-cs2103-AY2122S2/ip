package duke.command;

import duke.task.TaskList;

public class FindCommand extends Command {
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.findKeyWord(keyWord);
    }
}
