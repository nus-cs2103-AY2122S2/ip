package spark.exceptions.fileexceptions;

public class ReadFileException extends FileException {
    public ReadFileException() {
        super("Save-File could not be read!");
    }
}
