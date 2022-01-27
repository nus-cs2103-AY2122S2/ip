package pikabot.exception;

public class InvalidTaskCommandException extends PikaBotException {

    public InvalidTaskCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
