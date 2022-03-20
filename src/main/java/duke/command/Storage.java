package duke.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.ui.Parser;

/**
 * Manages the storage of data from Duke into a file.
 */
public class Storage {
    private BufferedReader reader;
    private Path dukePath;

    /**
     * Creates a new Storage instance.
     *
     * @throws IOException If I/O errors are faced when creating or reading files.
     */
    public Storage() throws IOException {
        dukePath = Paths.get("data/duke.txt");
        if (!Files.exists(dukePath)) {
            Files.createDirectory(Paths.get("data/"));
            Files.createFile(dukePath);
        }
        reader = Files.newBufferedReader(dukePath);
    }

    /**
     * Returns a TaskList based on any existing Duke data saved locally.
     *
     * @return A TaskList based on any existing Duke data saved locally.
     * @throws IOException If I/O errors are faced when reading files.
     */
    public TaskList initialize() throws IOException {
        assert reader.ready();
        TaskList taskList = new TaskList();
        while (true) {
            String input = reader.readLine();
            if (input == null) {
                break;
            }
            taskList.addTask(Parser.parseFileFormat(input));
        }
        return taskList;
    }

    /**
     * Adds a Task to the file.
     *
     * @param t The Task to be added to the file.
     * @throws IOException If there are issues faced when writing the Task into the file.
     */
    public void addTask(Task t) throws IOException {
        String newTask = t.toFileFormatString() + "\n";
        Files.write(dukePath, newTask.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Deletes a Task from the file.
     *
     * @param taskIndex The index of the Task to be deleted.
     * @throws IOException If there are issues faced when reading Tasks from and writing Tasks into the file.
     */
    public void deleteTask(int taskIndex) throws IOException {
        List<String> currList = Files.readAllLines(dukePath);
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < currList.size(); i++) {
            if (i != taskIndex) {
                newList.add(currList.get(i));
            }
        }
        Files.delete(dukePath);
        Files.write(dukePath, newList);
    }

    /**
     * Modifies an existing entry in the file.
     * Converts the entry from done to undone or vice versa.
     *
     * @param taskIndex The Task to be modified.
     * @throws IOException If there are issues faced when reading Tasks from and writing Tasks into the file.
     */
    public void modifyTask(int taskIndex) throws IOException {
        List<String> currList = Files.readAllLines(dukePath);
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < currList.size(); i++) {
            if (i != taskIndex) {
                newList.add(currList.get(i));
            } else {
                String[] descriptionParts = currList.get(i).split(" \\| ");
                StringBuilder newDescription = new StringBuilder();
                int partIndex = 0;
                while (partIndex < descriptionParts.length) {
                    if (partIndex != 0) {
                        newDescription.append(" | ");
                    }
                    if (partIndex == 1) {
                        if (descriptionParts[1].equals("0")) {
                            newDescription.append("1");
                        } else {
                            newDescription.append("0");
                        }
                    } else {
                        newDescription.append(descriptionParts[partIndex]);
                    }
                    partIndex++;
                }
                newList.add(newDescription.toString());
            }
        }
        Files.delete(dukePath);
        Files.write(dukePath, newList);
    }
}
