package duke;

public class CommandBye extends Command {

    public CommandBye() {

    }

    @Override
    public String execute() {
        return "goodbye!\n";
    }
}
