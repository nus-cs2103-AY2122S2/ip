package doge.command;

import doge.*;
import doge.exception.DogeException;

public class ByeCommand extends Command {
    public ByeCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        storage.save(tasks.getTaskList());
        this.setExit(true);
    }

    @Override
    public String toString() {
        return "Doge:   Please don't ever bother me again, bye.";
    }
}
