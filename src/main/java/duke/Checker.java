package duke;

/**
 * Checks the command inputted by the user and filters it into the various Enum Status.
 */
public class Checker {
    private String command;
    private Status state;

    public enum Status {
        BYE,
        HELP,
        LIST,
        EVENT,
        DEADLINE,
        TODO,
        DELETE,
        MARK,
        UNMARK,
        FIND
    };

    /**
     * Constructor of the Checker class.
     * Used to classify the status of the commands to be passed into Parser.
     *
     * @param input Command inputted by the user. E.g. help, delete, event.
     * @throws DukeException Thrown if the command inputted does not match any in-built commands.
     */
    @SuppressWarnings("checkstyle:OperatorWrap")
    public Checker(String input) throws DukeException {
        command = input;

        if (this.command.equals("bye")) {
            this.state = Status.BYE;
        } else if (this.command.equals("help")) {
            this.state = Status.HELP;
        } else if (this.command.equals("list")) {
            this.state = Status.LIST;
        } else if (this.command.equals("delete")) {
            this.state = Status.DELETE;
        } else if (this.command.equals("todo")) {
            this.state = Status.TODO;
        } else if (this.command.equals("deadline")) {
            this.state = Status.DEADLINE;
        } else if (this.command.equals("event")) {
            this.state = Status.EVENT;
        } else if (this.command.equals("unmark")) {
            this.state = Status.UNMARK;
        } else if (this.command.equals("mark")) {
            this.state = Status.MARK;
        } else if (this.command.equals("find")) {
            this.state = Status.FIND;
        } else {
            throw new DukeException(String.format("Sorry, I did not catch that! \\(T.T)/\n"
                    + "type 'help' to see all commands I can help with."));
        }
    }

    /**
     * Gets the enum status generated from the input command.
     *
     * @return State based on the input. E.g. help, delete, event.
     */
    public Status getStatus() {
        return state;
    }
}
