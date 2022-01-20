public class InvalidArgumentsException extends Exception{

    private String invalidInput;

    public InvalidArgumentsException(String invalidInput) {
        this.invalidInput = invalidInput;
    }

    @Override
    public String toString() {
        return "I'm sorry, I didn't quite get that";
    }
}
