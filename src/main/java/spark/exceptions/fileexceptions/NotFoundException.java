package spark.exceptions.fileexceptions;

public class NotFoundException extends FileException {
    public NotFoundException() {
        super("File could not be found at the specified relative file-path! Was it deleted or moved?");
    }
}
