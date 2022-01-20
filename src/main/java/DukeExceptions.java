abstract class DukeExceptions extends Exception{
    DukeExceptions(String s) {
        super(s);
    }
}

abstract class InvalidCommand extends DukeExceptions {

    InvalidCommand(String s) {
        super(s);
        //TODO Auto-generated constructor stub
    }

    static InvalidCommand createInvalidCommand(String cmd) throws InvalidCommand {
        throw cmd.isBlank() ? new MissingCommand() : new IncorrectCommand();
    }

}

abstract class EmptyParameters extends DukeExceptions {

    EmptyParameters(String s) {
        super(s);
        //TODO Auto-generated constructor stub
    }
    
}

final class EmptyTask extends EmptyParameters {

    private EmptyTask(String s) {
        super(s);
        //TODO Auto-generated constructor stub
    }
    
    static EmptyTask createEmptyTask(String taskCmd) throws EmptyTask {
        throw new EmptyTask("Are you tryng to add something to " + taskCmd);
    }
}

final class EmptyDate extends EmptyParameters {

    private EmptyDate(String s) {
        super(s);
        //TODO Auto-generated constructor stub
    }
    
    static EmptyDate createEmptyDate(String taskCmd) throws EmptyDate {
        throw new EmptyDate(taskCmd + " needs a date");
    }

}

final class EmptyNumber extends EmptyParameters {

    private EmptyNumber(String s) {
        super(s);
        //TODO Auto-generated constructor stub
    }

    static EmptyNumber createEmptyNumber(String cmd) throws EmptyNumber {
        throw new EmptyNumber(cmd + " needs a number");
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

final class ListIndexOutOfBound extends DukeExceptions {

    ListIndexOutOfBound() {
        super("The index is out of bond!");
    }
    
}