package gene.exception;

public class UnrecognizedCommandException extends Exception{
    public UnrecognizedCommandException() {
        super("----------------------------" +
                "----------------------------\n" +
                "OOPS!!! I'm sorry, but I don't know what that means :-(" +
                "\n" +
                "--------------------------------------------------------\n");
    }

    public String getMessage() {
        return "----------------------------" +
                "----------------------------\n" +
                "OOPS!!! I'm sorry, but I don't know what that means :-(" +
                "\n" +
                "--------------------------------------------------------\n";
    }
}
