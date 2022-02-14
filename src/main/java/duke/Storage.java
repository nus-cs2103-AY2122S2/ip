package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * The Storage class deals with the loading and saving of tasks in the data file.
 */
public class Storage {
    private String filePath;
    private ArrayList<String> content;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.content = new ArrayList<>();
    }

    /**
     * Loads the tasks saved in the data file.
     *
     * @return An ArrayList of Task containing the saved tasks or an empty list if there are no tasks saved.
     * @throws DukeException If there are issues accessing the data file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            this.content = new ArrayList<>();
            while (s.hasNextLine()) {
                this.content.add(s.nextLine());
                String[] command = this.content.get(this.content.size() - 1).split(",");
                Task t;
                switch (command[0]) {
                case "T":
                    t = new Todo(command[2]);
                    break;
                case "D":
                    t = new Deadline(command[2], command[3]);
                    break;
                case "E":
                    t = new Event(command[2], command[3]);
                    break;
                default:
                    t = new Task("placeholder task");
                    break;
                }

                if (command[1].equals("1")) {
                    t.markAsDone();
                }
                tasks.add(t);
            }
        } catch (IOException e) {
            try {
                File parentDir = new File("./data");
                parentDir.mkdir();
                String fileName = "duke.txt";
                File file = new File(parentDir, fileName);
                file.createNewFile();
            } catch (IOException e2) {
                throw new DukeException("Something went wrong with creating a file to save the tasks");
            }
        }
        return tasks;
    }

    /**
     * Updates the list of tasks saved in the data file.
     *
     * @param oldDetails     The current save format of the task to be updated.
     * @param updatedDetails The new save format of the task to be updated.
     * @throws DukeException If there are issues accessing or writing to the data file .
     */
    public void updateSavedTasks(String oldDetails, String updatedDetails) throws DukeException {
        try {
            if (oldDetails.isEmpty()) {
                this.content.add(updatedDetails);
                FileWriter fw = new FileWriter(this.filePath, true);
                fw.write(updatedDetails);
                fw.close();
                return;
            }

            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder sb = new StringBuilder();
            int indexToRemoveAt = 0;
            for (int i = 0; i < this.content.size(); i++) {
                if (!updatedDetails.isEmpty() && this.content.get(i).equals(oldDetails)) {
                    this.content.set(i, updatedDetails);
                    sb.append(this.content.get(i) + "\n");
                }
                else if (!updatedDetails.isEmpty() || !this.content.get(i).equals(oldDetails)) {
                    sb.append(this.content.get(i) + "\n");
                } else if (updatedDetails.isEmpty()) {
                    indexToRemoveAt = i;
                }
            }
            if (updatedDetails.isEmpty()) {
                this.content.remove(indexToRemoveAt);
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong with saving the tasks");
        }
    }
}
