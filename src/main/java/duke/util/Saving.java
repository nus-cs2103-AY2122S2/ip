package duke.util;

/**
 * Interface for saving data by making it into a string to be saved into a file.
 */
public interface Saving {
    /**
     * Gets the Object's data file format.
     *
     * @return The format in which the object's data will be saved.
     */
    String saveFileFormat();
}
