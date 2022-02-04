public class UnrecognizedCommandException extends Exception{
    public UnrecognizedCommandException(String errMsg) {
        super(errMsg);
    }

    public String getMessage() {
        return "----------------------------" +
                "----------------------------\n" +
                "OOPS!!! I'm sorry, but I don't know what that means :-(" +
                "\n" +
                "--------------------------------------------------------\n";
    }
}
