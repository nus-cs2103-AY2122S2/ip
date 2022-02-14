package duke.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Class that deals with storing/saving
 * data on the hard disk
 */
public class Storage {

    /**
     * Retrieve saved tasks
     * from the hard disk
     *
     * @return List of Tasks that has been saved on the hard disk
     */
    //@@author goel-a-reused
    // This method has been adapted from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> getSavedList() {

        try {
            FileInputStream readData = new FileInputStream("data/list.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> savedTasks = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return savedTasks;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
    //@@author goel-a

    /**
     * Save the given list of tasks to the
     * hard disk
     *
     * @param saved The current list of tasks to be saved to hard disk
     */
    //@@author goel-a-reused
    // This method has been adapted from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    public static void saveListToDisk(ArrayList<Task> saved) {

        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            FileOutputStream writeData = new FileOutputStream("data/list.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(saved);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@@author goel-a
}
