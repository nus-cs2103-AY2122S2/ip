package duke.util;

import duke.task.Task;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and writes input history into a database file
 */
public class BotStoring implements Storing {

    private final String path;
    private final String directory;
    private final File database;


    public BotStoring(String path, String directory) throws IOException {
        this.path = path;
        database = new File(path);
        this.directory = directory;
        isDatabaseExists();
    }

    /**
     * Checks whether the database file exist or not
     *
     * @return True if the database file exists and false otherwise
     * @throws IOException If an I/O error occur
     */
    public boolean isDatabaseExists() throws IOException {
        if (!database.exists()) {
            File directoryFile = new File(this.directory);
            directoryFile.mkdir();
            database.createNewFile();
            return false;
        }
        return true;
    }

    /**
     * Reads the database file and append all the tasks into a collection
     *
     * @param storingList The collection need to append
     * @throws IOException If an I/O error occur
     */
    public void readFileContent(ArrayList<Task> storingList) throws IOException {
        FileReader fr = new FileReader(database);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String type = String.valueOf(line.charAt(2));
            String status = String.valueOf(line.charAt(6));
            String description = line.substring(10, line.length() - 2);
            Task task;
            if (type.equals("D") || type.equals("E")) {
                task = new Task(description.replace("| ", "/"), type);
            } else {
                task = new Task(description, type);
            }
            if (status.equals("1")) {
                task.changeTaskDone();
            }
            storingList.add(task);
        }
    }

    /**
     * Changes the status of the task in the database
     *
     * @param lineNumber The task location in the list
     * @param task       The task need to be changed
     * @throws IOException If an I/O error occur
     */
    public void changeStatusTask(int lineNumber, Task task) throws IOException {
        List<String> lines = Files.readAllLines(database.toPath());
        lines.set(lineNumber - 1, task.createTextDatabase());
        Files.write(database.toPath(), lines);
    }

    /**
     * Adds content of the task into the database
     *
     * @param task The task need to be added
     * @throws IOException If an I/O error occur
     */
    public void addTaskToDatabase(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);
        fileWriter.write(task.createTextDatabase() + System.lineSeparator());
        fileWriter.close();
    }

    /**
     * Deletes a specific task in the database
     *
     * @param lineNumber The task location in the list
     * @throws IOException If an I/O error occur
     */
    public void deleteTask(int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(database.toPath());
        lines.remove(lineNumber);
        Files.write(database.toPath(), lines);
    }

    /**
     * Retrieves the total length of the database
     *
     * @return The length of database file
     */
    public long getDatabaseLength() {
        return this.database.length();
    }
}
