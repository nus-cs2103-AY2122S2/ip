package kenobi.command;

public class InvalidCommand extends Command {
    @Override
    public String execute() {
        return "It's not in the archives! Your command is invalid";
    }
}
