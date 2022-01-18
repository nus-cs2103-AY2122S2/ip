package Exceptions;

public class UnknownCommandException extends DukeExceptions{
    public UnknownCommandException(){}

    @Override
    public void printError(){
        printFormatted(new String[]{"☹ OOPS!!! I'm sorry, but I don't know what that means :-("});
    }
}
