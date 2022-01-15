public class DukeException extends Exception {

    private String msg;
    public DukeException(String exn) {
        super(exn);
        this.msg = exn;
    }

    @Override
    public String toString() {
        switch(msg) {
            case "todo":
                return "Hey! This todo can't be empty nyaan~!";
            case "deadline":
                return "Hey! This deadline can't be empty nyaan~!";
            case "event":
                return "Hey! This event can't be empty nyaan~!";
            default:
                return "Chi-san doesn't understand that nyaan~!";
        }
    }
}
