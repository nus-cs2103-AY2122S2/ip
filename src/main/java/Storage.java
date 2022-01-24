import java.io.File;
import java.io.IOException;

public class Storage {
    private File saveFile;

    public Storage(String filePath) {
        try {
            saveFile = new File(filePath);
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("make file exception here");
        }
    }

    public void addTask(String task) {

    }
}
