package chibot.commands;

import chibot.storage.Storage;
import chibot.tasklist.TaskList;

public class HelpCommand extends Command {

    private final String[] tokens;

    public HelpCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public String execute(TaskList tl, Storage sge) {
        return "";
    }
}
