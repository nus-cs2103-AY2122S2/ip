package jeff.storage;

import jeff.main.JeffException;

import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import java.time.format.DateTimeParseException;

/**
 * Storage class for loading and saving from an external file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath Location of external file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the contents of the external file into a TaskList if any.
     *
     * @return TaskList filled with the contents of the external file.
     * @throws JeffException When no available format is available to parse dateInfo.
     */
    public TaskList load() throws JeffException {

        File f = new File(filePath);
        TaskList tasks = new TaskList();

        // When the file or directory does not exist, create it.
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            File parent = f.getParentFile();
            if (!parent.exists()) {
                parent.mkdir();
            }
            return tasks;
        }

        // Look through every line on the external file and add to TaskList.
        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                String[] inputLine = input.split("\\| ", 4);
                String type = inputLine[0];
                String done = inputLine[1];
                String name = inputLine[2];
                String time;

                // New task from the current line
                Task curr;
                // Check what type of task is this.
                switch (type) {
                    case "T ":
                        curr = new Todo(name);
                        break;
                    case "D ":
                        time = inputLine[3];
                        curr = new Deadline(name.substring(0, name.length() - 1), time);
                        break;
                    case "E ":
                        time = inputLine[3];
                        curr = new Event(name.substring(0, name.length() - 1), time);
                        break;
                    default:
                        curr = null;
                }
                // Check if the task is marked as done.
                switch (done) {
                    case "1 ":
                        curr.setMark();
                    case "0 ":
                        break;
                }
                // After all the necessary information, add it into the TaskList.
                tasks.add(curr);
            } catch (DateTimeParseException e) {
                throw new JeffException("Invalid date time format at: " + input
                        + "Please refer to readme.txt for the available formats");
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Saves the current Tasks on the TaskList directly onto the filePath.
     *
     * @param tasks TaskList to be saved.
     */
    public void save(TaskList tasks) throws JeffException {

        try {
            FileWriter fw = new FileWriter(filePath);

            // For each task, store all its information into a string.
            for (int n = 0; n < tasks.size(); n++) {
                Task currTask = tasks.getAt(n);
                String str = null;
                String suffix = null;
                switch (currTask.whatType()) {
                    case "T":
                        str = "T | ";
                        suffix = currTask.getDescription();
                        break;
                    case "D":
                        str = "D | ";
                        Deadline tempD = (Deadline) currTask;
                        suffix = tempD.getDescription() + " | " + tempD.getOriginalDate();
                        break;
                    case "E":
                        str = "E | ";
                        Event tempE = (Event) currTask;
                        suffix = tempE.getDescription() + " | " + tempE.getOriginalDate();
                        break;

                }
                switch (currTask.getStatusIcon()) {
                    case "X":
                        str = str + "1 | ";
                        break;
                    case " ":
                        str = str + "0 | ";
                        break;
                }
                // Once all the information is extracted, write it into the file.
                fw.write(str + suffix + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new JeffException("Please make sure that" + filePath
                    + " is available to write.");
        }
    }
}
