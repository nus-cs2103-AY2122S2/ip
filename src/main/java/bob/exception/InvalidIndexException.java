package bob.exception;

public class InvalidIndexException extends BobException {
    public InvalidIndexException() {
        super("You need to give me a valid task number! (ಥ﹏ಥ) \n" +
            "\tTry \"list\" if you want to see your tasks and their numbers.");
    }
}
