import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private File store;
    public static String fileName;
    public Storage(String filePath) {
        fileName = filePath;
        try {
            store = new File(filePath);
            if (!store.exists()) {
                store.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("make file exception here");
        }
    }

    public void updateStore(TaskList taskList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(taskList.getList());
        } catch (IOException e) {
            System.out.println("make file exception here");
        }
    }

    // Only one List of type Task will be stored in the store
    @SuppressWarnings("unchecked")
    public void readStore(Ui ui) {
        try {
            FileInputStream fis = new FileInputStream(store);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Task> taskList = (List<Task>) ois.readObject();
            for (int i = 0; i < taskList.size(); i++) {
                ui.say("\t" + (i + 1) + ". " + taskList.get(i).printStatus());
            }
        } catch (IOException | ClassNotFoundException e) {
            ui.noTasks();
        }
    }

    // Only one List of type Task will be stored in the store
    @SuppressWarnings("unchecked")
    public List<Task> getSavedStore() {
        try {
            FileInputStream fis = new FileInputStream(store);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<Task>();
        }
    }
}
