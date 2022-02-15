package duke;

public class CommandUnknown extends Command {

    public CommandUnknown() {

    }

    @Override
    public String execute() {
        return "invalid command! try 'help' for list of commands\n";
    }
}
