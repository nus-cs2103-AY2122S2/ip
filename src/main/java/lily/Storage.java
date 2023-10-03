package lily;

import lily.task.Task;

import java.util.LinkedList;

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
 * @version Feb 2022 (AY21/22 Sem 2)
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage manager.
     * 
     * @param fp The filepath to load from and save to.
     */
    public Storage(String fp) {
        filePath = fp;
    }

    /**
     * Returns a LinkedList from reading the savefile.
     * 
     * @return Saved tasks in the savefile.
     * @throws LilyException If there is no savefile or a problem arose.
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
            throw new LilyException(LilyException.ERROR_LOAD_FILE);
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            throw new LilyException(LilyException.ERROR_LOAD_FILE_UNKNOWN_CLASS);
        } 
        return result;
    }

    /**
     * Writes the Tasklist to a file.
     * 
     * @param list The Tasklist to be exported.
     * @throws LilyException If the file could not be written.
     */
    public void save(TaskList list) throws LilyException {
        try {
            if (!list.isEmpty()) {
                File dataFolder = new File("./data");
                if (!dataFolder.exists()) {
                    dataFolder.mkdir();
                }
    
                FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list.getContents());
                oos.close();
                fos.close();
            }
        } catch (IOException e) {
            throw new LilyException(LilyException.ERROR_WRITE_FILE);
        }
    }
}