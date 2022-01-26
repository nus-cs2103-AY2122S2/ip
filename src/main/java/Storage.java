import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private final File bobbyFile;

    public Storage(String filepath) {
        this.bobbyFile = new File(filepath);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() throws StorageException {
        ArrayList<Task> task = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bobbyFile));
            task = (ArrayList<Task>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bobbyFile));
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
