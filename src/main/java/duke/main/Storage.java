package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


/**
 * Storage class.
 * This class handles the storing of the user's TaskList.
 */
public class Storage {
    private static String filepath;

    /**
     * Constructor for Storage.
     * This tells Burp where to retrieve and save the user's TaskList to.
     *
     * @param filepath the path to read from, or write to
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    // Reads and adds the file's content into the array

    /**
     * Method to add the file's content to a new TaskList.
     * Tries to read from the given filepath. If the file exists, then this method
     * will retrieve its contents. Otherwise, a blank TaskList is returned
     *
     * @return the saved TaskList based on the filepath, or an empty TaskList if pathfile does not exist
     * @throws FileNotFoundException when the file cannot be found from the given path
     */
    public static TaskList addFileContent() throws FileNotFoundException {
        File f = new File(Storage.filepath);
        TaskList toDoList = new TaskList();
        // save commands into the file
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            // Read the saved command from file
            String cmd = s.nextLine();
            String[] cmdSplit = cmd.split("&");
            boolean mark = cmdSplit[0].equals("[X]") ? true : false;

            switch (cmdSplit[1]) {
            case "T":
                toDoList.add(new ToDo(cmdSplit[2], mark));
                break;
            case "E":
                toDoList.add(new Event(cmdSplit[2], mark, cmdSplit[3]));
                break;
            case "D":
                toDoList.add(new Deadline(cmdSplit[2], mark, cmdSplit[3]));
                break;
            default:
                // do nothing
                break;
            }
        }
        return toDoList;
    }

    // Writes the contents of toDoList into storage with specific formatting

    /**
     * This method writes to the given filepath.
     * Executed whenever a new Task is added to the TaskList.
     *
     * @param toDoList the user's List of Tasks
     * @throws IOException when IO error occurs
     */
    public static void writeFileContent(TaskList toDoList) throws IOException {
        // Create a new file writer to that filepath
        FileWriter fw = new FileWriter(Storage.filepath);
        for (int i = 0; i < toDoList.size(); i++) {
            Task currentTask = toDoList.get(i);
            fw.write(currentTask.getStringCmd());
            fw.write("\n");
        }
        fw.close();
    }

}
