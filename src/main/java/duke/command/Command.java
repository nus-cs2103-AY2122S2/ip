package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class for commands to establish essential methods for all command subclasses.
 */
public abstract class Command {
    /**
     * Enums to show the types of commands that exist.
     */
    public enum CommandType {
        BYE,
        FIND,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        DEADLINE,
        EVENT,
        SORT,
        TODO;

        /**
         * Compares a String to this enum based while ignoring case.
         *
         * @param input String Input String to be compared
         * @return boolean Boolean value to show if this equals command.
         */
        public boolean equals(String input) {
            if (input.equalsIgnoreCase(this.name())) {
                return true;
            } else {
                return false;
            }
        }
    }

    protected String[] commandArray;

    /**
     * Instantiates Command object with an empty commandArray.
     * Represents an invalid command.
     */
    public Command() {
        this.commandArray = new String[0];
    }

    /**
     * Instantiates Command object based on commandArray.
     *
     * @param commandArray String[] String array that contains the input command.
     */
    public Command(String[] commandArray) {
        this.commandArray = commandArray;
    }

    public String[] getCommandArray() {
        return commandArray;
    }

    /**
     * Executes the command based on the class.
     * @param taskList TaskList TaskList object.
     * @param ui Ui Ui object.
     * @param storage Storage Storage object.
     */
    public abstract void executeCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
