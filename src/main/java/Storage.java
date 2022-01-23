import java.io.*;
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

    public ArrayList<Task> save() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileOutputStream writeData = new FileOutputStream("data/duke.ser");
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

        writeStream.writeObject(arr);
        writeStream.flush();
        writeStream.close();
    }
}
