package baron.commands;

public abstract class Command {

    public boolean isByeCommand() {
        return false;
    }

    public abstract String execute();
}