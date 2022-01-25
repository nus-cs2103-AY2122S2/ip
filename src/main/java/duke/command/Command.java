package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {

    /**
     * Collection of possible keywords of a command.
     */
    public enum Keyword {
        /**
         * BYE command
         */
        BYE,
        /**
         * LIST command
         */
        LIST,
        /**
         * MARK command
         */
        MARK,
        /**
         * UNMARK command
         */
        UNMARK,
        /**
         * TODO command
         */
        TODO,
        /**
         * DEADLINE command
         */
        DEADLINE,
        /**
         * EVENT command
         */
        EVENT,
        /**
         * DELETE command
         */
        DELETE,
        /**
         * Other not recognized commands
         */
        UNRECOGNIZED
    }

    private final Keyword keyword;

    /**
     * Constructs a {@code Command} object with one of the following keywords:
     *   {BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNRECOGNIZED}.
     * @param keyword the keyword
     */
    public Command(Keyword keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the keyword of the command.
     * @return the keyword of the command
     */
    public Keyword getKeyword() {
        return keyword;
    }

    /**
     * Executes the command.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
