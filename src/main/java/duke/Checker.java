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
        UNMARK
    };

    /**
     * Constructor of the Checker class.
     * Used to classify the status of the commands to be passed into Parser.
     *
     * @param input Command inputted by the user. E.g. help, delete, event.
     * @throws DukeException Thrown if the command inputted does not match any in-built commands.
     */
    public Checker(String input) throws DukeException {
        command = input;

        if (command.equals("bye")) {
            state = Status.BYE;
        } else if (command.equals("help")) {
            state = Status.HELP;
        } else if (command.equals("list")) {
            state = Status.LIST;
        } else if (command.equals("delete")) {
            state = Status.DELETE;
        } else if (command.equals("todo")) {
            state = Status.TODO;
        } else if (command.equals("deadline")) {
            state = Status.DEADLINE;
        } else if (command.equals("event")) {
            state = Status.EVENT;
        } else if (command.equals("unmark")) {
            state = Status.UNMARK;
        } else if (command.equals("mark")) {
            state = Status.MARK;
        } else {
            throw new DukeException("Sorry, I did not catch that! \\(T.T)/\n" +
                    "Please type 'help' to see all commands I can help with.");
        }
    }

    /**
     * Gets the enum status generated from the input command.
     *
     * @return State based on the input. E.g. help, delete, event.
     */
    public Status getStatus(){
        return state;
    }
}