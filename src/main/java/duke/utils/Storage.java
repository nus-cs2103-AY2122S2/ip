package duke.utils;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;


public  class Storage {

    // The solution below has been adapted from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> getSavedList(){

        try {

            FileInputStream readData = new FileInputStream("data/list.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> savedTasks = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return savedTasks;

        } catch (IOException | ClassNotFoundException e){

            System.out.println("No Previously Saved Tasks Found");
            return new ArrayList<>();

        }

    }

    // The solution below has been adapted from https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    public static void saveListToDisk(ArrayList<Task> saved){

        try {

            FileOutputStream writeData = new FileOutputStream("data/list.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(saved);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e){

            e.printStackTrace();

        }
    }
}
