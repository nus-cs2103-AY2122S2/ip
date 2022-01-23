import java.io.File;
import java.io.IOException;

/**
 * The Memory class abstracts the leading and saving of tasks given to Sana
 *
 * @author Jan
 * @version 1.0
 */
public class Memory {
    /**
     * This variable is the path where the taskList is saved
     */
    private final static String memPath = "data/sana.txt";

    /**
     * This variable is the path for directory that stores the memory file
     */
    private final static String dirPath = "data";

    /**
     * This variable represents the File object that stores the taskList
     */
    private File memFile;

    /**
     * The constructor for a Memory object
     */
    public Memory() {
        memFile = new File(memPath);
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdir();

        }
        if (!memFile.exists()) {
            try {
                memFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
