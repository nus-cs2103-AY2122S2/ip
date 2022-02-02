package chatbot.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Utility class to read and write to a file.
 */
public class Storage {
    /**
     * Saves a serializable object to the specified file.
     *
     * @param filePath the file to save to
     * @param item     the serializable item to save
     * @param <T>      the type of the serializable item
     * @return returns true if the item was successfully saved, else returns false
     */
    public static <T extends Serializable> boolean save(String filePath, T item) {
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                //noinspection ResultOfMethodCallIgnored
                file.getParentFile().mkdirs();
            }
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();

            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, false)));
            objectOutputStream.writeObject(item);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Loads a serializable object from the specified file.
     *
     * @param fileName the file to load from
     * @param <T>      the type of the serializable item
     * @return returns the item if it was successfully loaded, else returns null
     */
    public static <T extends Serializable> T load(String fileName) {
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            T item = (T) objectInputStream.readObject();
            objectInputStream.close();
            return item;
        } catch (Exception e) {
            return null;
        }
    }
}