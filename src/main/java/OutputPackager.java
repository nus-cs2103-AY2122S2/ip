public class OutputPackager {
    private final String message;

    OutputPackager(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("____________________________________________________________\n%s\n____________________________________________________________", message);
    }
}
