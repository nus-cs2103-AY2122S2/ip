package bob;

import bob.Task.Task;
import bob.command.ListCommand;
import bob.exception.FileException;

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
            System.out.println("File exception while creating new file");
        }
    }

    public void updateStore(TaskList taskList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(store));
            oos.writeObject(taskList.getList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
