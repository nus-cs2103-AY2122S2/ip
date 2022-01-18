package Exceptions;

public class EmptyDescriptionException extends DukeExceptions{
    private String type;
    public EmptyDescriptionException(String type){
        this.type = type;
    }

    @Override
    public void printError() {
        printFormatted(new String[]{"☹ OOPS!!! The description of a "+ type + " cannot be empty."});
    }
}
