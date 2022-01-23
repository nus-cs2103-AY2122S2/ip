public class InvalidCommandException extends SaitamaException {
    InvalidCommandException() {
        super("huh?!\nPlease enter a valid command!");
    }
}
