public class BlankCommandException extends DukeException {

    public BlankCommandException() {
        super("OOPS!!! I'm sorry, but I do not accept empty commands! :-(");
    }
}
