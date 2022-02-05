package duke.task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.gui.Ui;

/**
 * A storage class to create a save file, load & save the user's tasks.
 */
public class Storage {

    private String taskFilePath;
    private Ui ui;
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Initializes the Storage object by specifiying a path for its save file.
     * @param taskFilePath String of the path for the save file.
     * @param ui Ui object to handle user interaction.
     */
    public Storage(String taskFilePath, Ui ui) {
        this.taskFilePath = taskFilePath;
        this.ui = ui;
    }

    /**
     * A method that returns an ArrayList of current tasks if there are tasks in the save file
     * or creates a new empty ArrayList.
     * @return ArrayList containing tasks or a new ArrayList.
     */
    public ArrayList<Task> loadFileContents() {
        // load tasklist data from save
        try {
            File dir = new File("data");
            dir.mkdirs();
            File f = new File(taskFilePath);

            // checks if the user already has existing save data
            if (!f.createNewFile()) {
                //ui.showText("loading previous save data...");
                System.out.println("loading previous save data...");
                Scanner s = new Scanner(f);
                while (s.hasNext()) {

                    String task = s.nextLine();
                    String[] taskStrings = task.split(" \\| ");
                    String taskType = taskStrings[0];
                    boolean taskStatus = taskStrings[1].equals("1");
                    String taskDescription = taskStrings[2];

                    switch (taskType) {
                    case "T":
                        taskList.add(new Todo(taskDescription, taskStatus));
                        break;
                    case "E":
                        taskList.add(new Event(taskDescription, taskStrings[3], taskStatus));
                        break;
                    case "D":
                        taskList.add(new Deadline(taskDescription, taskStrings[3], taskStatus));
                        break;
                    default:
                        throw new DukeException("Invalid save data");
                    }
                }
                s.close();
            // else creates the save data for further use
            } else {
                //ui.showText("creating save data...");
                System.out.println("creating save data...");
            }

        // if the file cannot be read, generate an empty list
        } catch (IOException e) {
            ui.showError(e.getMessage());
            return new ArrayList<Task>();
        // if the file contains corrupt data, generate an empty list
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return new ArrayList<Task>();
        }

        return taskList;
    }

    /**
     * A method to update the tasks in the save file with the user's current existing tasks.
     * @param taskList ArrayList of user's current existing tasks to save to disk.
     */
    public void updateFileContents(TaskList taskList) {
        ArrayList<Task> externalTaskList = taskList.getTasks();
        // write & update save data with current tasklist
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            for (Task t: externalTaskList) {
                fw.write(t.toStringSaveData());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

}
