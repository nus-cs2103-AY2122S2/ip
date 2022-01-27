public class DukeException extends Exception{

    protected Error err;

    public DukeException(Error err) {
        this.err = err;
    }

    public String invalidInput() {

        return "I'm sorry, but I don't know what that means.\n";
    }

    public String emptyDesc() {
        return "The description of a task cannot be empty.\n";
    }

    public String listError() {
        return "Index does not exists in array.\n";
    }
    
}
