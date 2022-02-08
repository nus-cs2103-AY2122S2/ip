package duke;

import java.io.IOException;

public abstract class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public boolean isExit() {
        return this.command.equals("bye");
    }

    public String getFirstWord() {
        return this.command;
    }

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
