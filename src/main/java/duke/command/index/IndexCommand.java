package duke.command.index;

import duke.command.Command;

public abstract class IndexCommand extends Command {
    protected static final String COMMAND_ARG = " <valid task no>";
    protected final int index;

    protected IndexCommand(int index) {
        this.index = index;
    }
}
