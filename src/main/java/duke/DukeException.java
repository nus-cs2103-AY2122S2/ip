package duke;
public class DukeException extends RuntimeException {

    public DukeException(String e) {
        super(e);
    }
}

class UnknownCommandException extends DukeException {
    private final String message;

    public UnknownCommandException(String e) {
        super(e);
        this.message = e;
    }

    @Override
    public String toString(){
        return "    OH NO! :(" + this.message;
    }
}

class EmptyDescriptionException extends DukeException {
    private final String message;

    public EmptyDescriptionException(String e) {
        super(e);
        this.message = e;
    }

    @Override
    public String toString() {
        return "    OH NO! :(" + this.message;
    }
}

class InvalidInputException extends DukeException {
    private final String message;

    public InvalidInputException(String e) {
        super(e);
        this.message = e;
    }

    @Override
    public String toString(){
        return "    OH NO! :(" + this.message;
    }
}