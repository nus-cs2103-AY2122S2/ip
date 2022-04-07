package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;




public class Storage {

    private static String filePath = "db/TaskDatabase.ser";

    public Storage(String path) {
        filePath = path;
    }


    /**
     * @return ArrayList of Task from the an existing file, else return an empty ArrayList.
     */
    //@@author zzhikai-reused
    //Reused from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    // with minor modification
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> readFile() {
        try {
            FileInputStream databaseInputStream = new FileInputStream(filePath);
            ObjectInputStream readDataBaseStream = new ObjectInputStream(databaseInputStream);
            ArrayList<Task> inputDatabase = (ArrayList<Task>) readDataBaseStream.readObject();
            readDataBaseStream.close();
            return inputDatabase;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * @param inputTaskList The new ArrayList of task to be saved.
     */
    //@@author zzhikai-reused
    //Reused from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    // with minor modification
    public static void saveToFile(ArrayList<Task> inputTaskList) {
        File directory = new File("db");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileOutputStream writeDatabaseInput = new FileOutputStream(filePath);
            ObjectOutputStream writeDatabaseStream = new ObjectOutputStream(writeDatabaseInput);
            writeDatabaseStream.writeObject(inputTaskList);
            writeDatabaseStream.flush();
            writeDatabaseStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
