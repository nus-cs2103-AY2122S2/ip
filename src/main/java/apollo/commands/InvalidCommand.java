package apollo.commands;

public class InvalidCommand extends Command {

    @Override
    public String execute() {
        return "Apologies, I do not understand that. ";
    }
}
