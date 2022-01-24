import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String path) {
        this.filePath = path;
    }

    public void writeToFile(String input) throws IOException {
        FileHandler.writeToFile(this.filePath, input);
    }

    public ArrayList<Task> retrieveData() throws FileNotFoundException {
        ArrayList<Task> arr = new ArrayList<Task>();
        FileHandler.readFromFile(arr);
        return arr;
    }

    public void editData(String oldText, String newText) throws IOException {
        FileHandler.editFile(this.filePath, oldText, newText);
    }

}
