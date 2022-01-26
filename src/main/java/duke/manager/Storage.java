package duke.manager;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.IOException;
import duke.task.Task;
import duke.exception.DukeException;


/**
 * Represents a storage object that handles the saving and loading in of the list of Tasks.
 */
public class Storage {
    private String filePath;

    /**
     * A constructor to store the relative filepath of the save file that contains the tasks.
     * @param filePath The relative filepath of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns An ArrayList of tasks that contains the saved tasks.
     *
     * @return ArrayList of Tasks.
     * @throws Exception If there is a problem in closing the input streams, or the save is corrupted.
     */
    public ArrayList<Task> load() throws Exception {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(filePath);
            in = new ObjectInputStream(fileIn);
            ArrayList<Task> tasks = (ArrayList<Task>) in.readObject();
            return tasks;
        } catch (FileNotFoundException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            throw new DukeException("Could not load save, creating new save file");
        } catch (EOFException e) {
            throw new DukeException("Empty save");
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     *
     * Saves the list of Tasks locally in the relative path.
     *
     * @param taskList The list of tasks.
     * @throws Exception If there is an issue saving the tasks.
     */
    public void save(TaskList taskList) throws Exception {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList.toArrayList());
            out.close();
            fileOut.close();
        } catch (IOException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            fileOut = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList.toArrayList());
            out.close();
            fileOut.close();
            throw new DukeException("Error trying to save your tasks, did you delete the file while the program was still running?");
        }
    }

}
