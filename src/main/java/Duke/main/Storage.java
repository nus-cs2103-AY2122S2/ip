package Duke.main;


import Duke.task.Task;
import Duke.task.TaskList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class use to load and write data
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * save taskList to a file
     * @param taskList contain data to be saved
     * @throws IOException handle IO errors
     */
    public void writeToFile(TaskList taskList) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList.getTaskList());
        oos.close();
    }

    /**
     * load the saved taskList from file
     * @return the data read from a file
     * @throws IOException handle IO errors
     * @throws ClassNotFoundException handle other errors
     */
    public ArrayList<Task> readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(file);
        @SuppressWarnings("unchecked")
        ArrayList<Task> taskList = (ArrayList<Task>) ois.readObject();
        ois.close();
        return taskList;
    }
}
