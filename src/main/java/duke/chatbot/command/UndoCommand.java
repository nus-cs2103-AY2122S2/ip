package duke.chatbot.command;

import java.util.ArrayList;

/**
 * Command which undo the last executed command
 * by the ChatBot.
 */
public class UndoCommand extends Command {

    /** Last command executed by ChatBot */
    private final Command lastExecutedCommand;

    /**
     * Returns an UndoCommand that will undo the given
     * last executed command.
     *
     * @param name Name of undo command.
     * @param lastExecutedCommand Command that is last executed by ChatBot,
     *                            to be undone.
     */
    public UndoCommand(String name, Command lastExecutedCommand) {
        super(name, null);
        this.lastExecutedCommand = lastExecutedCommand;
    }

    /**
     * Undo the command last executed by the ChatBot.
     *
     * @return ArrayList of string describing command that was undone.
     * @throws IllegalArgumentException If there is no last command.
     */
    @Override
    public ArrayList<String> execute() throws IllegalArgumentException {
        if (this.lastExecutedCommand == null) {
            throw new IllegalArgumentException("No command to undo!");
        }

        lastExecutedCommand.undo();
        ArrayList<String> response = new ArrayList<>();
        response.add("The following command was undone:");
        response.add(lastExecutedCommand.getOriginalCommand());
        return response;
    }
}
