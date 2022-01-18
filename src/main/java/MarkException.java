public class MarkException extends BobbyException {
    public String errtype;
    public MarkException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch(errtype) {
            case "empty": error_string = "     Which task?"; break;
            case "letter": error_string = "    C'mon, a letter is not a number dude"; break;
            case "OOB": error_string = "    Number too big, count properly!"; break;
            case "negative": error_string = "    Invalid number man, cannot be 0 or negative"; break;
            default: error_string = "    I cannot understand this..";
        }
        return error_string;
    }
}
