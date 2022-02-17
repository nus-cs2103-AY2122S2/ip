package yale.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import yale.task.TaskList;



/**
 * Class to deal with loading tasks
 * from file and saving tasks in file.
 */
public class Storage {
    /**
     * String defining the file path.
     */
    protected String filePath;

    /**
     * Constructor method.
     * @param filePath Specified file location.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads contents from specified file.
     * @return Contents from specified file.
     */
    public String loadFileContents() {
        try {
            File f = new File(this.filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            boolean hasTask = s.hasNext();
            String output = "";
            while (s.hasNext()) {
                output += s.nextLine() + "\n";
            }
            return output;
        } catch (IOException e) {
            new File("data").mkdirs();
            return "";
        }
    }

    /**
     * Writes content into specified file.
     * @param textToAdd Content to be written to specified file.
     * @throws IOException
     */
    public void writeToFile(String textToAdd) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            new File("data").mkdirs();
            FileWriter fw1 = new FileWriter(filePath);
            fw1.write(textToAdd);
            fw1.close();
        }
    }

    /**
     * Writes String from list into specified file.
     * @param list List of Task objects.
     */
    public void writeTextTo(TaskList list) {
        try {
            writeToFile(list.exportOut());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
