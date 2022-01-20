package baron.commands;

import baron.exceptions.BaronException;

public class InvalidCommand extends Command {
    @Override
    public String execute() {
        return (new BaronException("I'm sorry, but I beg your pardon? :-(")).toString();
    }
}