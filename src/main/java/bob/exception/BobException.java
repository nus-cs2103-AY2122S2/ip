package bob.exception;

public class BobException extends IllegalArgumentException {
    private String bobReply;
    public BobException(String error) {
        super(error);
        this.bobReply = error;
    }
    public String getBobReply() {
        return this.bobReply;
    }
}
