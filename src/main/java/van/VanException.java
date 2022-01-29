package van;

public class VanException extends Exception {
    private String error;

    public VanException(String str) {
        error = str;
    }

    public String getError() {
        return error;
    }
}
