package baron.commands;

public class ByeCommand extends Command {
    @Override
    public boolean isByeCommand() {
        return true;
    }

    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }
}
