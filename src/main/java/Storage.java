import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILE = "apollo.ser";

    private static void initialiseDir() throws ApolloException {
        File dataDir = new File(DATA_DIRECTORY);
        boolean isDirCreated = dataDir.exists();
        if (!isDirCreated || !dataDir.isDirectory()) {
            isDirCreated = dataDir.mkdir();
        }
        if (!isDirCreated) {
            throw new ApolloException("Cannot create save directory. ");
        }
    }

    public static void save(ArrayList<Task> tasks) throws ApolloException {
        initialiseDir();
        File dataFile = new File(DATA_DIRECTORY + File.separator + DATA_FILE);
        try {
            boolean isFileExist = dataFile.createNewFile();
        } catch (IOException e) {
            throw new ApolloException("Cannot create save file. ");
        }

        try {
            FileOutputStream fileStream = new FileOutputStream(DATA_DIRECTORY + File.separator + DATA_FILE);
            ObjectOutputStream objStream = new ObjectOutputStream(fileStream);
            objStream.writeObject(tasks);
        } catch (IOException e) {
            throw new ApolloException("Cannot write to save file. ");
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> load() throws ApolloException {
        try {
            FileInputStream fileStream = new FileInputStream(DATA_DIRECTORY + File.separator + DATA_FILE);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);
            return (ArrayList<Task>) objStream.readObject();
        } catch (FileNotFoundException e) {
            throw new ApolloException("No save file found. ");
        } catch (IOException | ClassNotFoundException e) {
            throw new ApolloException("Cannot load save file. ");
        }
    }
}
