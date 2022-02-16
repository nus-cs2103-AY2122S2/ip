package duke.util;

import duke.exception.DukeException;

/**
 * Interface for loading and extracting data from a string.
 */
public interface Loading {

    /**
     * Extracts and load data from the string.
     *
     * @param data The string to extract data from.
     * @throws DukeException If there are issues extracting data from line.
     */
    void extractDataFromLine(String data) throws DukeException;
}
