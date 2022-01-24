import java.io.*;
import java.nio.file.Paths;

/**
 * Serializes and Deserializes Database Files into TaskStore object.
 * Database Files follow a custom binary format.
 */

public class TaskStoreSerializer {
    private static final String DATA_FOLDER_PATH = "data/";
    private static final String DB_FILENAME = "store.db";

    public static TaskList inflate() throws DukeIOException {
        FileInputStream dbStream = openDatabaseRead();

        TaskList store = new TaskList();
        if (dbStream == null) {
            return store;
        }

        DataInputStream dbDataStream = new DataInputStream(dbStream);
        try (dbDataStream) {
            int recordCount = dbDataStream.readInt();
            for (int i = 0; i < recordCount; i++) {
                int recordLength = dbDataStream.readInt();
                byte[] recordData = dbStream.readNBytes(recordLength);
                try {
                    store.addTask(TaskSerializer.inflate(recordData));
                } catch (DukeIOException ex) {
                    System.out.println("Verbose: Failed to load Task record");
                }
            }
        } catch (IOException ex) {
            throw new DukeIOException("Failed to inflate database: IO Error");
        }

        return store;
    }

    public static void deflate(TaskList store) throws DukeIOException {
        FileOutputStream dbStream = openDatabaseWrite();
        try (DataOutputStream dbDataStream = new DataOutputStream(dbStream)) {
            dbDataStream.writeInt(store.getTaskCount());
            for (int i = 0; i < store.getTaskCount(); i++) {
                byte[] flattenedData = TaskSerializer.deflate(store.getTaskByIndex(i));
                dbDataStream.writeInt(flattenedData.length);
                dbDataStream.write(flattenedData);
            }
        } catch (IOException e) {
            throw new DukeIOException("Failed to deflate database: IO Error");
        }
    }

    private static void initDataStore() throws DukeIOException {
        File dataFolder = new File(DATA_FOLDER_PATH);

        if (dataFolder.exists() && !dataFolder.isDirectory()) {
            throw new DukeIOException("Data Store is not a directory at: "
                    + dataFolder.getAbsolutePath());
        } else if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                throw new DukeIOException("Cannot create data store directory at: "
                        + dataFolder.getAbsolutePath());
            }
        }
    }

    private static FileInputStream openDatabaseRead() throws DukeIOException {
        initDataStore(); // throws DukeIOException

        File database = Paths.get(DATA_FOLDER_PATH, DB_FILENAME).toFile();
        try {
            return new FileInputStream(database);
        } catch (FileNotFoundException ex) {
            // Does not create the database until a change is made
            return null;
        }
    }

    private static FileOutputStream openDatabaseWrite() throws DukeIOException {
        initDataStore(); // throws DukeIOException

        File database = Paths.get(DATA_FOLDER_PATH, DB_FILENAME).toFile();
        try {
            if (!database.exists()) {
                if (!database.createNewFile()) {
                    throw new DukeIOException("Could not create database");
                }
            }

            return new FileOutputStream(database);
        } catch (IOException ex) {
            throw new DukeIOException("Could not create database");
        }
    }
}
