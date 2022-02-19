package gene.exception;

public class FileDeletionException extends Exception {
    @Override
    public String getMessage() {
        return "--------------------------------------------------------\n"
                + ":( OOPS!!! Seems like there was trouble deleting a file"
                + "\n--------------------------------------------------------";
    }
}
