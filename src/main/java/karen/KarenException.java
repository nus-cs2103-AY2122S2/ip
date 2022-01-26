package karen;

public class KarenException extends Exception {
    String msg;

    public KarenException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
