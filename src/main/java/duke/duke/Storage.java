package duke.duke;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream("data/duke.ser");
        ObjectInputStream readStream = new ObjectInputStream(readData);

        ArrayList<Task> arr = (ArrayList<Task>) readStream.readObject();
        readStream.close();
        return arr;
    }

    public void save(ArrayList<Task> arr) {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            FileOutputStream writeData = new FileOutputStream("data/duke.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(arr);
            writeStream.flush();
            writeStream.close();
        }
        catch (IOException e) {
            System.out.println("Something went wrong. I think I may be corrupted.");
        }
    }

    public void exit(ArrayList<Task> arr) {
        save(arr);
        System.out.println("Goodbye! I'll be here if you need anything else.");
    }
}
