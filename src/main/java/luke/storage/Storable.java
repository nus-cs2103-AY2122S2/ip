package luke.storage;

import java.util.List;

/**
 * API of Storable component, called by StorageFile to save data into file.
 * Implement this if you would like to use StorageFile to save and load data from file.
 */
public interface Storable {
    /**
     * Method invoked by StorageFile to get the data that needs to be stored into a file.
     *
     * @return An ArrayList of String containing the data to be stored into a file.
     */
    List<String> getData();
}
