package duke.dukeexceptions;

import duke.dukeexceptions.DukeExceptions;

public abstract class InvalidCommand extends DukeExceptions{

    protected InvalidCommand(String s) {
        super(s);
    }

    public static InvalidCommand createInvalidCommand(String cmd) throws InvalidCommand {
        throw cmd.isBlank() ? new MissingCommand() : new IncorrectCommand();
    }

}

final class MissingCommand extends InvalidCommand {

    MissingCommand() {
        super("Sorry, I don't see any commands!");
    }

}

final class IncorrectCommand extends InvalidCommand {
    IncorrectCommand() {
        super("Sorry! I don't undestand what are you saying!");
    }
}