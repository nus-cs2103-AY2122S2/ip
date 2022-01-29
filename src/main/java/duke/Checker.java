package duke;

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

    public Checker(String input) throws DukeException {
        this.command = input;

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
            throw new DukeException("Sorry, I did not catch that! \\(T.T)/\n" +
                    "Please type 'help' to see all commands I can help with.");
        }
    }

    public Status getStatus(){
        return this.state;
    }
}