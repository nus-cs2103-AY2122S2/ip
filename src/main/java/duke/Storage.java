package duke;
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

    /**
     * Returns an ArrayList after reading from 'data/duke.ser'.
     * Then, creates an array with all previous tasks.
     *
     * @return Old task list.
     * @throws IOException If file not found.
     * @throws ClassNotFoundException If unable to find Task class to cast ArrayList to.
     */
    //@@author waleedkhan-reused
    // Reused from https://tinyurl.com/yckrncus
    // with minor modifications
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream("data/duke.ser");
        ObjectInputStream readStream = new ObjectInputStream(readData);
        @SuppressWarnings("unchecked")
        // The object read will always be an ArrayList as it always loads from the previously saved ArrayList.
        ArrayList<Task> arr = (ArrayList<Task>) readStream.readObject();
        readStream.close();
        return arr;
    }
    //@@author

    /**
     * Save the current task list to new file 'data/duke.ser'
     * If this is first boot (no file initialized yet), will create directory "data" and
     * then create the new file to save to.
     * If file already exists, creates new file and overwrites previous one.
     *
     * @param arr Current task list.
     */
    //@@author waleedkhan-reused
    // Reused from https://tinyurl.com/yckrncus
    // with minor modifications
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
        } catch (IOException e) {
            System.out.println("Something went wrong. I think I may be corrupted.");
        }
    }
    //@@author

    /**
     * Invokes save command to save the current list into data/duke.ser to be loaded in future
     * sessions. Then, it will bid farewell to the user.
     *
     * @param arr The list of tasks that Duke has for the current session.
     * @return An acknowledgement that the session has ended.
     */
    public String exit(ArrayList<Task> arr) {
        save(arr);
        return "Goodbye! I'll be here if you need anything else.";
    }
}
