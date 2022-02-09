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
public class Storage {

    /**
     * Loads task data from file and adds it into a list.
     * If file or directory is not found, the respective items are created and
     * an empty array list is returned.
     *
     * @return an arraylist containing data read form the file.
     * @throws IOException if there's error arising from the file methods
     * @throws DukeException if data in the file is corrupted (not in the correct storage format)
     */
    public static TaskList readFromFile() throws IOException, DukeException {
        File directory = checkForDirectory();
        File file = checkForFile();
        TaskList list = new TaskList();
        // Read from duke.txt and store information back into task list
        Scanner fc = new Scanner(file);
        while (fc.hasNext()) {
            FileParser parser = new FileParser(fc.nextLine());
            switch(parser.getCmd()) {
            case "todo":
                list.addTask(new ToDo(parser.getDesc(), parser.getIsDone()));
                break;
            case "deadline":
                list.addTask(new Deadline(parser.getDesc(), parser.getDate(), parser.getIsDone()));
                break;
            case "event":
                list.addTask(new Event(parser.getDesc(), parser.getDate(), parser.getIsDone()));
                break;
            default:
                throw new DukeException("Error in file. Please try again.");
            }

        }
        return list;
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
        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.createNewFile(); // Create if file does not exist
        }
        return file;
    }

    /**
     * Writes data from the arraylist into file.
     *
     * @param list an arraylist containing task objects.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static void writeToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String str = list.getIndex(0).changeFormat();
        for (int i = 1; i < list.getSize(); i++) {
            str = str + System.lineSeparator() + list.getIndex(i).changeFormat();
        }
        fw.write(str);
        fw.close();
    }
}
