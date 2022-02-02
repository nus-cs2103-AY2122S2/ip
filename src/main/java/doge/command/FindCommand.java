package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;

/**
 * Represents the "find" command. Doge will filter out tasks that matches the corresponding character sequence.
 */
public class FindCommand extends Command {
    String message;

    /**
     * Constructor for class Find.
     *
     * @param s the keyword(s) to filter out from the TaskList
     */
    public FindCommand(String s) {
        super(s);
    }

    /**
     * Executes the "Find" command. It filters out tasks from the TaskList that matches the corresponding character
     * sequence given.
     *
     * @param tasks {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @throws DogeException if user fails to provide a String for Doge bot to search for
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        if (this.details.isEmpty()) {
            throw new DogeException("How am I supposed to find something if you don't give me the details?");
        }

        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        int numbering = 1;

        for (int i = 0; i < tasks.size(); i++) {
            String curr = tasks.getTask(i).getDescription();
            if (curr.contains(this.details)) {
                output.append("\n").append(numbering).append(") ").append(tasks.getTask(i).toString());
                numbering++;
            }
        }

        this.message = output.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.message;
    }
}
