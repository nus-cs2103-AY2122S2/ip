abstract class DukeExceptions extends Exception{
    protected DukeExceptions(String s) {
        super(s);
    }
}

abstract class InvalidCommand extends DukeExceptions {

    protected InvalidCommand(String s) {
        super(s);
    }

    static InvalidCommand createInvalidCommand(String cmd) throws InvalidCommand {
        throw cmd.isBlank() ? new MissingCommand() : new IncorrectCommand();
    }

}

abstract class EmptyParameters extends DukeExceptions {

    EmptyParameters(String s) {
        super(s);
    }
    
}

final class ListIndexOutOfBound extends DukeExceptions {

    ListIndexOutOfBound() {
        super("Index is out of bond!");
    }
    
}

final class EmptyTask extends EmptyParameters {

    private EmptyTask(String s) {
        super(s);
    }
    
    static EmptyTask createEmptyTask(String taskCmd) throws EmptyTask {
        throw new EmptyTask("Are you tryng to add something to " + taskCmd.toLowerCase());
    }
}

final class EmptyDate extends EmptyParameters {

    private EmptyDate(String s) {
        super(s);
    }
    
    static EmptyDate createEmptyDate(String taskCmd) throws EmptyDate {
        throw new EmptyDate(taskCmd.substring(0, 1).toUpperCase() + taskCmd.substring(1) + " needs a date");
    }

}

final class EmptyNumber extends EmptyParameters {

    private EmptyNumber(String s) {
        super(s);
    }

    static EmptyNumber createEmptyNumber(String cmd) throws EmptyNumber {
        throw new EmptyNumber(cmd.substring(0, 1).toUpperCase() + cmd.substring(1) + " needs a number");
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