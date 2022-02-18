package apollo;

import static apollo.messages.Messages.LOAD_ERROR;
import static apollo.messages.Messages.SAVE_CREATE_ERROR;
import static apollo.messages.Messages.SAVE_WRITE_ERROR;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import apollo.exceptions.ApolloIoException;
import apollo.tasks.TaskList;

/**
 * Class for {@code Storage} of taskList.
 */
public class Storage {

    private static final Path DATA_PATH = Paths.get("data", "apollo.ser");

    /**
     * Creates directories and save file if it does not exist.
     *
     * @throws ApolloIoException If creating save file fails.
     */
    private static void initialiseDir() throws ApolloIoException {
        try {
            if (!Files.exists(DATA_PATH)) {
                if (!Files.exists(DATA_PATH.getParent())) {
                    Files.createDirectory(DATA_PATH.getParent());
                }

                Files.createFile(DATA_PATH);
            }
        } catch (IOException e) {
            throw new ApolloIoException(SAVE_CREATE_ERROR);
        }
    }

    /**
     * Saves taskList object to file via Serialization.
     *
     * @param taskList To save in save file.
     * @throws ApolloIoException If taskList cannot be written to save file.
     */
    public static void save(TaskList taskList) throws ApolloIoException {
        initialiseDir();

        try {
            ObjectOutputStream objStream =
                    new ObjectOutputStream(Files.newOutputStream(DATA_PATH));
            objStream.writeObject(taskList);
        } catch (IOException e) {
            throw new ApolloIoException(SAVE_WRITE_ERROR);
        }
    }

    /**
     * Loads TaskList object from save file via Deserialization.
     *
     * @return TaskList loaded from save file.
     * @throws ApolloIoException If save file cannot be loaded.
     */
    public static TaskList load() throws ApolloIoException {
        try {
            ObjectInputStream objStream =
                    new ObjectInputStream(Files.newInputStream(DATA_PATH));
            return (TaskList) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ApolloIoException(LOAD_ERROR);
        }
    }
}
