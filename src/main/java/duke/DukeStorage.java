package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents a class that handles the detection, creation, restoration
 * and updating of local duke.txt files
 */
public class DukeStorage {

    private File filePath;

    public DukeStorage() {
    }

    /**
     * Takes a given directory and fileName and detects the existence of
     * the file in the given directory. If it does not exist, creates the
     * directory and file on the user's behalf.
     *
     * Updates the filePath field in this class using the inputs provided.
     *
     * @param directory The desired directory.
     * @param fileName The name of the text file.
     * @throws IOException If there is an error in creating the filePath
     *                     due to incorrect directory or fileName input.
     */
    public void startup(String directory, String fileName) throws IOException {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke");
        String filePath = String.valueOf(path);

        // Check if parent and child directory exists else create them
        File f1 = new File(filePath + "\\" + directory);
        f1.mkdirs();

        // Check if duke.txt exits else create it
        File f2 = new File(filePath + "\\" + directory + "\\" + fileName);
        f2.createNewFile();

        this.filePath = f2;
    }

    /**
     * Attempts to load the data of a local duke.txt file into the given
     * history.
     *
     * @param history The DukeHistory instance that is being used in the
     *                current running instance of Duke.
     * @throws FileNotFoundException If there is an error in detecting
     *                               the file at the stored filePath
     *                               within this class.
     */
    public void restore(DukeHistory history) throws FileNotFoundException {
        Scanner s = new Scanner(this.filePath);
        while (s.hasNext()) {
            String temp = s.nextLine();
            String[] arr = temp.split(">");
            int mark = Integer.parseInt(arr[1]);
            switch (arr[0]) {
            case "T":
                history.addToDo(mark, arr[2]);
                break;
            case "D":
                history.addDeadline(mark, arr[2], arr[3], arr[4]);
                break;
            case "E":
                history.addEvent(mark, arr[2], arr[3], arr[4]);
                break;
            default:
                System.out.println("load unsuccessful");
                break;
            }
        }
    }

    /**
     * Updates the data of a local duke.txt file with the latest changes
     * made to it found in the provided history.
     *
     * @param history The DukeHistory instance that is being used in the
     *                current running instance of Duke.
     * @throws IOException If there is an error in detecting the file at
     *                     the stored filePath within this class.
     */
    void update(DukeHistory history) throws IOException {
        String content = history.formatRecord();
        FileWriter fw = new FileWriter(this.filePath);
        fw.append(content);
        fw.flush();
        fw.close();
    }

    /**
     * Returns the stored filePath in this class.
     *
     * @return The stored filePath in this class.
     */
    String getFilePath() {
        return this.filePath.getPath();
    }

}
