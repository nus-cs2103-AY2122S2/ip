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

/** Manages the storage of data from Duke into a file */
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
        // assumes reader is non-empty
        TaskList tl = new TaskList();
        while (true) {
            String entry = reader.readLine();
            if (entry == null) {
                break;
            }
            tl.addTask(Parser.parseFileFormat(entry));
        }
        return tl;
    }

    /**
     * Adds a task to the file.
     *
     * @param t The task to be added to the file.
     * @throws IOException If there are issues faced when writing the task into the file.
     */
    public void addTask(Task t) throws IOException {
        String newTask = t.convertToFileFormat() + "\n";
        Files.write(dukePath, newTask.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Deletes a task from the file.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws IOException If there are issues faced when reading tasks from and writing tasks into the file.
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
     * @param taskIndex The task to be modified.
     * @throws IOException If there are issues faced when reading tasks from and writing tasks into the file.
     */
    public void modifyTask(int taskIndex) throws IOException {
        List<String> currList = Files.readAllLines(dukePath);
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < currList.size(); i++) {
            if (i != taskIndex) {
                newList.add(currList.get(i));
            } else {
                String[] entrySplit = currList.get(i).split(" \\| ");
                int part = 0;
                StringBuilder newLine = new StringBuilder();
                while (part < entrySplit.length) {
                    if (part != 0) {
                        newLine.append(" | ");
                    }
                    if (part == 1) {
                        if (entrySplit[1].equals("0")) {
                            newLine.append("1");
                        } else {
                            newLine.append("0");
                        }
                    } else {
                        newLine.append(entrySplit[part]);
                    }
                    part++;
                }
                newList.add(newLine.toString());
            }
        }
        Files.delete(dukePath);
        Files.write(dukePath, newList);
    }
}
