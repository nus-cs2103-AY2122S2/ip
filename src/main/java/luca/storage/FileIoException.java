package luca.storage;

import luca.common.DukeException;

/**
 * Exception raised when running to IOException when
 * reading or writing to file.
 */
public class FileIoException extends DukeException {

    /**
     * Constructor for FileIOException.
     * @param errorMessage message describing error.
     */
    FileIoException(String errorMessage) {
        super("OOPS! File I/O error: " + errorMessage);
    }
}
