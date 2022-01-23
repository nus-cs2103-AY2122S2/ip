package saitama.exceptions;

public class InvalidCommandException extends SaitamaException {
    public InvalidCommandException() {
        super("huh?!\nPlease enter a valid command!");
    }
}
