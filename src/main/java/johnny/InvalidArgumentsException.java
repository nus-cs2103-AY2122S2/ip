package johnny;

public class InvalidArgumentsException extends Exception{

    private String invalidInput;

    public InvalidArgumentsException(String invalidInput) {
        this.invalidInput = invalidInput;
    }

    public String errorMessage() {
        return "I'm sorry, I didn't quite get that";
    }

}
