package saitama.exceptions;

public class InvalidTaskNumberException extends SaitamaException {
    public InvalidTaskNumberException() {
        super("Please enter a valid task number!");
    }
}
