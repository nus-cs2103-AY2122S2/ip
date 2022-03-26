package duke.commands;

import duke.DukeHistory;
import duke.DukeUi;

public class ByeCommand extends Commands {

    public ByeCommand(DukeHistory history, String[] userInput, DukeUi ui) {
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
        return;
    }

    /**
     * Generates a response to "bye" command.
     * @return Response.
     */
    @Override
    public String execute() {
        return "_______________________________________________________\n"
                + "Goodbye. I hope to be of service to you again soon!\n"
                + "_______________________________________________________\n";
    }
}
