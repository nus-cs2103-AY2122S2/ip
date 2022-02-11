package duke;

public class listCommand extends Commands {

    public listCommand(DukeHistory history, String[] userInput, DukeUi ui) {
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
     *
     * @return True.
     */
    @Override
    public void validate() {
        return;
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
                + history.printAll() + border;
    }
}
