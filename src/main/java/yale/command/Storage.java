package yale.command;

import yale.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to deal with loading tasks
 * from file and saving tasks in file
 */
public class Storage {
    /**
     * String defining the file path
     */
    protected String filePath;

    /**
     * Constructor method
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads contents from specified file
     * @return
     */
    public String loadFileContents() {
        try {
            File f = new File(this.filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            if (!s.hasNext()) {
                System.out.println("You have no saved tasks.");

            } else {
                String output = "";
                while (s.hasNext()) {
                    output += s.nextLine() + "\n";
                }
                return output;
            }
            return "";
        } catch (IOException e) {
            new File("data").mkdirs();
            return "";
        }
    }

    /**
     * Writes content into specified file
     * @param textToAdd
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
     * Writes String from list into specified file
     * @param list
     */
    public void writeTextTo(TaskList list) {
        String file2 = this.filePath;
        try {
            writeToFile(list.exportOut());
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
