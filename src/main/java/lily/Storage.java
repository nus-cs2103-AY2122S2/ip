package lily;
import lily.task.Task;

import java.util.LinkedList;

import lily.task.LilyException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Loads and Saves the Task list from the savefile.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Storage {
    private String filePath = "data/list.txt";

    /**
     * Create a new Storage manager.
     * @param fp The filepath to load from and save to
     */
    public Storage(String fp) {
        filePath = fp;
    }

    /**
     * Returns a LinkedList from reading the savefile.
     * @return Saved tasks in the savefile.
     * @throws IOException If there is no savefile or a problem arose.
     */
    public LinkedList<Task> load() throws LilyException {
        LinkedList<Task> result = new LinkedList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            LinkedList<Task> read = (LinkedList<Task>) ois.readObject();

            ois.close();
            fis.close();
            result = read;
        } catch (IOException e) {
            throw new LilyException("There's no save file.");
        } catch (ClassNotFoundException c) {
           System.out.println("Class not found");
            c.printStackTrace();
        } 
        return result;
    }

    /**
     * Writes the Tasklist to a file.
     * @param list The Tasklist to be exported.
     * @throws IOException If a problem arose.
     */
    public void save(TaskList list) throws IOException {
        if (!list.isEmpty()) {
            File dataFolder = new File("/data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list.getContents());
            oos.close();
            fos.close();
        }
    }
}