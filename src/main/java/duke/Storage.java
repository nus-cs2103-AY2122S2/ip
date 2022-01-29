package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.FileWriter; // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * Storage class is meant to read and write to the text file based on the tasks the the user creates.
 */
public class Storage {
    private String fileName;
    private TaskList tasks;

    /**
     * Constructor of the Storage class.
     *
     * @param path  file name of the text file.
     */
    public Storage(String path) {
        fileName = path;
        tasks = new TaskList();
    }

    /**
     * Gets the name of the file where the user stored their previous list of tasks.
     *
     * @return returns the file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Saves the existing tasks into the text file.
     *
     * @param args Formatted tasks that are in the form <Marked Status> | <Task Type> | <Task Details>.
     */
    public void saveFile(String args) {
        try {
            FileWriter myWriter = new FileWriter(fileName, false);
            myWriter.write(args);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the txt file and generates the TaskList based on historical tasks that the user has inputted into Ducky.
     *
     * @param filepath path in which the text file exists.
     * @return TaskList which holds the historical tasks that the user inputted into Ducky previously.
     * @throws DukeException Exception is thrown when there is an issue with the inputs in the text file.
     */
    public TaskList readFile(String filepath) throws DukeException {
        try {
            File myObj = new File(System.getProperty("user.dir")+ "/" + filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                // Process the data such that it can see if this is event or deadline or todo,
                // then create accordingly
                String data = myReader.nextLine();
                processString(data);
            }
            myReader.close();
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        }
    }

    /**
     * Processes the strings that are in the text file.
     *
     * @param line A string that is in the text file. Should be in the form <Mark State> | <Task Type> | <Task Details>.
     * @throws DukeException thrown when there is an issue with the inputs in the text file.
     */
    private void processString(String line) throws DukeException {
        String[] parts = line.split("\\|");
        String state = parts[0].trim();
        String type = parts[1].trim();
        String description = parts[2].trim();
        // 1. Check task.Task type
        if (type.equals("D")) {
            String time = parts[3];
            DateHelper datetime = new DateHelper(time);
            Deadline out = new Deadline(description, datetime);
            tasks.addTask(out);
        } else if (type.equals("E")) {
            String time = parts[3];
            DateHelper datetime = new DateHelper(time);
            Event out = new Event(description, datetime);
            tasks.addTask(out);
        } else if (type.equals("T")) {
            Todo out = new Todo(description);
            tasks.addTask(out);
        }
        // 2. Check if marked
        if (state.equals("1")) {
            tasks.getTask(tasks.getSize()-1).setDone();
        }
    }
}
