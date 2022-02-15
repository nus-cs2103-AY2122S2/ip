package chatcat.util;

import chatcat.tasks.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.ArrayList;

public class WriteToFile  {
    String home;
    File file;
    Path path;
    ObjectOutputStream writeStream;
    FileOutputStream writeData;

    public WriteToFile() {
        home = System.getProperty("user.home");
        Path currentRelativePath = Paths.get("");
        path = currentRelativePath.toAbsolutePath();
        file = new File(path + File.separator + "tasklist.ser");
    }


    //function is written based on reference from:
    //https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    public void toWrite(ArrayList<Task> tasks) {
        try {
            writeData = new FileOutputStream(file);
            writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(tasks);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to tasklist.");
            e.printStackTrace();
        }
    }

    //function is written based on reference from:
    //https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    public ArrayList<Task> toRead() {
        try {
            FileInputStream readData = new FileInputStream(file);
            ObjectInputStream readStream = new ObjectInputStream(readData);

            @SuppressWarnings("unchecked")
            ArrayList<Task> tasks = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while reading tasklist.");
            return new ArrayList<>();
        }
    }

}
