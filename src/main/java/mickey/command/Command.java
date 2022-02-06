package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.TaskList;

import java.util.Arrays;
import java.util.Optional;

/**
 * Command object to handle user commands.
 */
public abstract class Command {
    /** User input command. */
    String cmd;

    /** Optional String arguments. */
    Optional<String> args;

    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public Command(String fullCommand) {
        String[] splitCommands = fullCommand.split(" ", 2);
        this.cmd = splitCommands[0];
        if (splitCommands.length > 1) {
            this.args = Optional.of(splitCommands[1]);
        } else {
            this.args = Optional.empty();
        }
    }

    /**
     * Splits args string into task and date arguments.
     * @return String array containing task and date.
     */
    String[] splitArgs() {
        return args.map(s -> Arrays.stream(s.split("/by", 2)).map(String::trim).toArray(String[]::new))
                .orElse(null);
    }

    /**
     * Gets task description from user input.
     *
     * @return Task description.
     * @throws MickeyException Missing task description from user input.
     */
    String getDescription() throws MickeyException {
        if (args.isEmpty()) {
            throw new MickeyException("\tWhoa! Minnie won't want to see a missing task.");
        }
        return splitArgs()[0];
    }

    /**
     * Gets date from user input.
     *
     * @return Date string.
     * @throws MickeyException Missing date input.
     */
    String getDate() throws MickeyException {
        String[] split = splitArgs();
        if (split.length == 1 || split[1].length() == 0) {
            throw new MickeyException("\tOops! Pluto must have eaten the date.");
        }
        return splitArgs()[1];
    }

    /**
     * Gets index of task from user input.
     *
     * @return Index of task.
     * @throws MickeyException Invalid index.
     */
    int getIndex() throws MickeyException {
        if (args.isPresent()) {
            return Integer.parseInt(args.get()) - 1;
        } else {
            throw new MickeyException("No such task!");
        }
    }

    /**
     * Execute command.
     *
     * @param tasks List of tasks.
     * @param ui Ui to print feedback.
     * @param storage Storage to store tasks.
     * @return Response after executing command.
     * @throws MickeyException Exception for invalid commands.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException;

    /**
     * Returns if user entered exit command.
     *
     * @return True if user entered exit command.
     */
    public boolean isExit() {
        return this.cmd.equals("bye");
    }
}
