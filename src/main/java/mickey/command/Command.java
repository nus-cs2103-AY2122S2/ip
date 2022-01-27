package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.TaskList;

import java.util.Arrays;
import java.util.Optional;

public abstract class Command {
    String cmd;
    Optional<String> args;

    public Command(String fullCommand) {
        String[] splitCommands = fullCommand.split(" ", 2);
        this.cmd = splitCommands[0];
        if (splitCommands.length > 1) {
            this.args = Optional.of(splitCommands[1]);
        } else {
            this.args = Optional.empty();
        }
    }

    String[] splitArgs() {
        return args.map(s -> Arrays.stream(s.split("/by", 2)).map(String::trim).toArray(String[]::new))
                .orElse(null);
    }

    String getDescription() throws MickeyException {
        if (args.isEmpty()) {
            throw new MickeyException("\tWhoa! Minnie won't want to see a missing task.");
        }
        return splitArgs()[0];
    }

    String getDate() throws MickeyException {
        String[] split = splitArgs();
        if (split.length == 1 || split[1].length() == 0) {
            throw new MickeyException("\tOops! Pluto must have eaten the date.");
        }
        return splitArgs()[1];
    }

    int getIndex() throws MickeyException {
        if (args.isPresent()) {
            return Integer.parseInt(args.get()) - 1;
        } else {
            throw new MickeyException("No such task!");
        }
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException;

    public boolean isExit() {
        return this.cmd.equals("bye");
    }
}
