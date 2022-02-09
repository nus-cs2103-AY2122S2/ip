package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class TagStorage {
    /**
     * Loads tag data from file and adds it into a list.
     * If file or directory is not found, the respective items are created and
     * an empty array list is returned.
     *
     * @return an arraylist containing data read form the file.
     * @throws IOException if there's error arising from the file methods.
     * @throws DukeException if data in the file is corrupted (not in the correct storage format).
     */
    public static TagList readFromFile() throws IOException, DukeException {
        File directory = checkForDirectory();
        File file = checkForFile();
        TagList list = new TagList();
        // Read from duke.txt and store information back into task list
        Scanner fc = new Scanner(file);
        while (fc.hasNext()) {
            Tag newTag = new Tag(fc.nextLine());
            list.addTag(newTag);
        }
        return list;
    }

    /**
     * Writes data from the arraylist into file.
     *
     * @param list an arraylist containing task objects.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static void writeToFile(TagList list) throws IOException {
        File directory = new File("./data");
        File file = new File("./data/tag.txt");
        FileWriter fw = new FileWriter("./data/tag.txt");
        assert (directory.exists() == true && file.exists() == true) : "Missing Directory and File";
        String str = list.getIndex(0).getName();
        for (int i = 1; i < list.getSize(); i++) {
            str = str + System.lineSeparator() + list.getIndex(i).getName();
        }
        fw.write(str);
        fw.close();
    }

    /**
     * Checks if directory can be found. If not found, create a directory.
     *
     * @returns the created or found directory.
     */
    public static File checkForDirectory() {
        // Check if data directory exist in the project root
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir(); // Create if directory does not exist
        }
        return directory;
    }

    /**
     * Checks if file can be found. If not found, create a file.
     *
     * @returns the created or found file.
     * @throws IOException if there's error arising from the file methods
     */
    public static File checkForFile() throws IOException {
        // Check if duke.txt exist in the data directory
        File file = new File("./data/tag.txt");
        if (!file.exists()) {
            file.createNewFile(); // Create if file does not exist
        }
        return file;
    }
}
