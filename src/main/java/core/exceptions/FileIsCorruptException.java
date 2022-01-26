package core.exceptions;

public class FileIsCorruptException extends DukeException {
    public FileIsCorruptException() {
        super("The file used to initialize the task list is corrupt!");
    }
}
