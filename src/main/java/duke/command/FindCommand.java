package duke.command;

import duke.functionality.TaskList;

public class FindCommand extends Command{
    public FindCommand(String word) {
        super(null, null, word);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.findWord(super.word);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
