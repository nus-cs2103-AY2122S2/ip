package duke.commands;

import duke.DukeHistory;
import duke.DukeUi;

public class ListCommand extends Commands {

    public ListCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    /**
     * Runs validate() and execute().
     * @return Response.
     */
    @Override
    public String validateAndExecute() {
        validate();
        return execute();
    }

    /**
     * Always returns true. Bye command by default is valid so long as assertion holds in Duke class.
     */
    @Override
    public void validate() {

    }

    /**
     * Generates a response to "list" command.
     *
     * @return Response.
     */
    @Override
    public String execute() {
        String border = "_______________________________________________________\n";
        return border
                + "These are your tasks that we have in our records:\n"
                + this.getHistory().printAll() + border;
    }
}
