package gene.exception;

public class FileRenameException extends Exception {
    @Override
    public String getMessage() {
        return "--------------------------------------------------------\n"
                + ":( OOPS!!! Seems like there was trouble renaming a file"
                + "\n--------------------------------------------------------";
    }
}
