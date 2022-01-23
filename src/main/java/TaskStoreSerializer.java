import java.io.*;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Serializes and Deserializes Database Files into TaskStore object.
 * Database Files follow a custom binary format.
 */

public class TaskStoreSerializer {
    private static final String DATA_FOLDER_PATH = "data/";
    private static final String DB_FILENAME = "store.db";

    public static TaskStore inflate() throws DukeIOException {
        FileInputStream dbStream = openDatabaseRead();

        TaskStore store = new TaskStore();
        if (dbStream == null) {
            return store;
        }

        DataInputStream dbDataStream = new DataInputStream(dbStream);
        try {
            int recordCount = dbDataStream.readInt();
            for (int i = 0; i < recordCount; i++) {
                int recordLength = dbDataStream.readInt();
                byte[] recordData = dbStream.readNBytes(recordLength);
                try {
                    Task task = TaskSerializer.inflate(recordData);
                    if (task == null) {
                        System.out.println("Verbose: Failed to load Task record");
                        continue;
                    }
                    store.addTask(task);
                } catch (DukeIOException ex) {
                    System.out.println("Verbose: Failed to load Task record");
                }
            }
        } catch (IOException ex) {
            throw new DukeIOException("Failed to inflate database: IO Error");
        } finally {
            try {
                dbDataStream.close();
            } catch (IOException ex) {
                System.out.println("Verbose: Failed to close db read stream");
            }
        }

        return store;
    }

    public static void deflate(TaskStore store) throws DukeIOException {

        try (FileOutputStream dbStream = openDatabaseWrite()) {
            DataOutputStream dbDataStream = new DataOutputStream(dbStream);
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
        if (!database.exists()) {
            // Initialize database
            return null;
        }

        try {
            return new FileInputStream(database);
        } catch (FileNotFoundException ex) {
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
