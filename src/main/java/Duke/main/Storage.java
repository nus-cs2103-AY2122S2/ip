package Duke.main;

import Duke.task.Task;
import Duke.task.TaskList;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(TaskList taskList) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList.getTaskList());
        oos.close();
    }

    public ArrayList<Task> readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(file);
        @SuppressWarnings("unchecked")
        ArrayList<Task> taskList = (ArrayList<Task>) ois.readObject();
        ois.close();
        return taskList;
    }
}
