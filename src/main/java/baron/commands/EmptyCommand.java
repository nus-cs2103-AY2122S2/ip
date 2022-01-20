package baron.commands;

import baron.exceptions.BaronException;

public class EmptyCommand extends Command {
    @Override
    public String execute() {
        return (new BaronException("Please enter a command!")).toString();
    }
}
