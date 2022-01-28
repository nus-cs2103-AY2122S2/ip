package duke;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


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
    public static TaskList loadFile() throws IOException, DukeException {
        // Check if data directory exist in the project root
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir(); // Create if directory does not exist
        }
        // Check if duke.txt exist in the data directory
        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.createNewFile(); // Create if file does not exist
        }
        TaskList list = new TaskList();
        // Read from duke.txt and store information back into task list
        Scanner fc = new Scanner(file);
        int index = 0;
        while (fc.hasNext()) {
            String[] str = fc.nextLine().split(",");
            if (str[0].equals("T")) {
                if (str[1].equals("1")) {
                    Task newTask = new ToDo(str[2], true);
                    list.addTask(newTask);
                } else {
                    Task newTask = new ToDo(str[2], false);
                    list.addTask(newTask);
                }
                index++;
            } else if (str[0].equals("D")) {
                try {
                    if (str[1].equals("1")) {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Deadline(str[2], date, true);
                        list.addTask(newTask);
                    } else {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Deadline(str[2], date, false);
                        list.addTask(newTask);
                    }
                    index++;
                } catch (DateTimeParseException ex) {
                    throw new DukeException("INVALID DATE FORMAT FOUND IN DUKE.TXT FILE. UNABLE TO READ FROM FILE.");
                }
            } else if (str[0].equals("E")) {
                try {
                    if (str[1].equals("1")) {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Event(str[2], date, true);
                        list.addTask(newTask);
                    } else {
                        LocalDate date = LocalDate.parse(str[3]);
                        Task newTask = new Event(str[2], date, false);
                        list.addTask(newTask);
                    }
                    index++;
                } catch (DateTimeParseException ex) {
                    throw new DukeException("INVALID DATE FORMAT FOUND IN DUKE.TXT FILE. UNABLE TO READ FROM FILE.");
                }
            } else {
                throw new DukeException("INVALID TASK FOUND IN DUKE.TXT FILE. UNABLE TO READ FROM FILE.");
            }
        }
        return list;
    }

    /**
     * Writes data from the arraylist into file.
     *
     * @param list an arraylist containing task objects.
     * @throws IOException if there's error arising from I/O methods.
     */
    public static void writeFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String str = list.getIndex(0).changeFormat();
        for (int i = 1; i < list.getSize(); i++) {
            str = str + System.lineSeparator() + list.getIndex(i).changeFormat();
        }
        fw.write(str);
        fw.close();
    }
}
