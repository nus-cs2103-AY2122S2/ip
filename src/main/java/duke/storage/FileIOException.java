package duke.storage;

import duke.common.DukeException;

/**
 * Exception raised when running to IOException when
 * reading or writing to file.
 */
public class FileIOException extends DukeException {

    /**
     * Constructor FileIOException
     * @param errorMessage message describing error.
     */
    FileIOException(String errorMessage) {
        super("OOPS! File I/O error: " + errorMessage);
    }
}
