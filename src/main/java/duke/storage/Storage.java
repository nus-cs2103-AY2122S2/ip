package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.data.exception.IllegalValueException;
import duke.data.exception.ResourceNotFoundException;
import duke.data.task.DeadlineTask;
import duke.data.task.EventTask;
import duke.data.task.Task;
import duke.data.task.TodoTask;

/**
 * Represents a file used to store the task list data.
 */
public class Storage {
    private static File f;

    /**
     * Constructs a storage object that represents the file "data/duke.txt"
     *
     * @throws ResourceNotFoundException if a file or folder cannot be found.
     */
    public Storage() throws ResourceNotFoundException, IOException {
        File data = new File("data");
        if (!data.exists()) {
            data.mkdir();
        }

        File duke = new File("data/duke.txt");
        if (!duke.exists()) {
            duke.createNewFile();
        }

        if (!data.exists()) {
            throw new ResourceNotFoundException("Folder ./data/ cannot be found");
        } else if (!duke.exists()) {
            throw new ResourceNotFoundException("File ./data/duke.txt cannot be found");
        }

        this.f = duke;
    }

    /**
     * Retrieves all the tasks from the storage.
     *
     * @return a list of tasks.
     * @throws FileNotFoundException if the file cannot be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException, IllegalValueException {
        ArrayList<Task> taskListFromStore = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] currentLineSplit = currentLine.split("\\|");
            String id = currentLineSplit[0];
            String type = currentLineSplit[1];
            int done = Integer.parseInt(currentLineSplit[2]);
            String description = currentLineSplit[3];


            if (type.equals("T")) {
                String tagName = currentLineSplit.length > 4 ? currentLineSplit[4] : "";
                TodoTask task = new TodoTask(description, done == 1, id, tagName);
                taskListFromStore.add(task);
            } else if (type.equals("D")) {
                String deadline = currentLineSplit[4];
                String tagName = currentLineSplit.length > 5 ? currentLineSplit[5] : "";
                DeadlineTask task = new DeadlineTask(description, deadline, done == 1, id, tagName);
                taskListFromStore.add(task);
            } else if (type.equals("E")) {
                String deadline = currentLineSplit[4];
                String tagName = currentLineSplit.length > 5 ? currentLineSplit[5] : "";
                EventTask task = new EventTask(description, deadline, done == 1, id, tagName);
                taskListFromStore.add(task);
            }
        }
        return taskListFromStore;
    }

    /**
     * Append to the file.
     *
     * @param input string to be appended to the file.
     * @throws IOException if an input or output error occurs.
     */
    public void appendToFile(String input) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true);
        fw.write(input);
        fw.close();
    }

    /**
     * Update a task in the file.
     *
     * @param id id of the task to be updated.
     * @param done status of the task to be updated.
     * @throws IOException if an input or output error occurs.
     */
    public void updateTask(String id, boolean done) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("data/duke.txt"),
                StandardCharsets.UTF_8));
        for (int i = 0; i < fileContent.size(); i++) {
            String currentLine = fileContent.get(i);
            String[] currentLineSplit = currentLine.split("\\|");
            String taskIdFromFile = currentLineSplit[0];
            char d = done ? '1' : '0';

            if (taskIdFromFile.equals(id)) {
                StringBuilder updatedLine = new StringBuilder(currentLine);
                updatedLine.setCharAt(39, d);
                fileContent.set(i, String.valueOf(updatedLine));
                break;
            }
        }

        Files.write(Paths.get("data/duke.txt"), fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Removes a task from the file.
     *
     * @param id id of the file to be removed.
     * @throws IOException if an input or output error occurs.
     */
    public void removeTask(String id) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("data/duke.txt"),
                StandardCharsets.UTF_8));
        for (int i = 0; i < fileContent.size(); i++) {
            String currentLine = fileContent.get(i);
            String[] currentLineSplit = currentLine.split("\\|");
            String taskIdFromFile = currentLineSplit[0];

            if (taskIdFromFile.equals(id)) {
                fileContent.remove(i);
                break;
            }
        }

        Files.write(Paths.get("data/duke.txt"), fileContent, StandardCharsets.UTF_8);
    }
}
