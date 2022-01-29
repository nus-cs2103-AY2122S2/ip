import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static final Path DATA_PATH = Paths.get("data", "apollo.ser");

    private static void initialiseDir() throws ApolloException {
        try {
            if (!Files.exists(DATA_PATH)) {
                if (!Files.exists(DATA_PATH.getParent())) {
                    Files.createDirectory(DATA_PATH.getParent());
                }
                Files.createFile(DATA_PATH);
            }
        } catch (IOException e) {
            throw new ApolloException("Cannot create save. ");
        }
    }

    public static void save(ArrayList<Task> tasks) throws ApolloException {
        initialiseDir();
        try {
            ObjectOutputStream objStream = new ObjectOutputStream(Files.newOutputStream(DATA_PATH));
            objStream.writeObject(tasks);
        } catch (IOException e) {
            throw new ApolloException("Cannot write to save file. ");
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> load() throws IOException, ClassNotFoundException {
        ObjectInputStream objStream = new ObjectInputStream(Files.newInputStream(DATA_PATH));
        return (ArrayList<Task>) objStream.readObject();
    }
}
