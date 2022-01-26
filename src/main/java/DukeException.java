public class DukeException extends Exception {
    String message;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }

    @Override
    public String toString(){
        return this.message;
    }
}