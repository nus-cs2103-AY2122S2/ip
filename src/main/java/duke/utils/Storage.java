package duke.utils;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;


/**
 * Class that deals with storing/saving
 * data on the hard disk
 */
public  class Storage {

    /**
     * Retrieve saved tasks
     * from the hard disk
     *
     * @return List of Tasks that has been saved on the hard disk
     */
    // The solution below has been adapted from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> getSavedList() {

        try {
            FileInputStream readData = new FileInputStream("data/list.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> savedTasks = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return savedTasks;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No Previously Saved Tasks Found");
            return new ArrayList<>();
        }
    }

    /**
     * Save the given list of tasks to the
     * hard disk
     *
     * @param saved The current list of tasks to be saved to hard disk
     */
    // The solution below has been adapted from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    public static void saveListToDisk(ArrayList<Task> saved) {

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
}
