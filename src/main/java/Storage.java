import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Represents a location in the hard drive.
 */
public class Storage {
    public static File f;

    /**
     * Constructs a storage object that represents the file data/duke.txt.
     *
     * @throws ResourceNotFoundException if a file or folder cannot be found.
     */
    public Storage() throws ResourceNotFoundException{
        File data = new File("data");
        File duke = new File("data/duke.txt");

        if (!data.exists()) {
            throw new ResourceNotFoundException("Folder ./data/ cannot be found");
        } else if (!duke.exists()){
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
    public static ArrayList<Task> retrieveTasks() throws FileNotFoundException {
        ArrayList<Task> taskListFromStore = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] scanned = line.split("\\|");
            String type = scanned[1];
            int done = Integer.parseInt(scanned[2]);
            String description = scanned[3];
            if (type.equals("T")) {
                TodoTask task = new TodoTask(description, done == 1);
                taskListFromStore.add(task);
            } else if (type.equals("D")) {
                String deadline = scanned[4];
                DeadlineTask task = new DeadlineTask(description, deadline, done == 1);
                taskListFromStore.add(task);
            } else if (type.equals("E")) {
                String deadline = scanned[4];
                EventTask task = new EventTask(description, deadline, done == 1);
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
    public static void appendToFile(String input) throws IOException{
        FileWriter fw = new FileWriter("data/duke.txt", true);
        fw.write(input);
        fw.close();
    }

    /**
     * Update a task in the file.
     *
     * @param id id of the task to be updated.
     * @param done status of the task to be updated.
     * @throws FileNotFoundException if the file cannot be found.
     * @throws IOException if an input or output error occurs.
     */
    public static void updateTask(int id, boolean done) throws FileNotFoundException, IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("data/duke.txt"), StandardCharsets.UTF_8));
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            String[] a = line.split("\\|");
            int idFromStore = Integer.parseInt(a[0]);
            char d = done ? '1' : '0';

            if (idFromStore == id) {
                StringBuilder updatedLine = new StringBuilder(line);
                updatedLine.setCharAt(4, d);
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
     * @throws FileNotFoundException if the file cannot be found.
     * @throws IOException if an input or output error occurs.
     */
    public static void removeTask(int id) throws FileNotFoundException, IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("data/duke.txt"), StandardCharsets.UTF_8));
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            String[] a = line.split("\\|");
            int idFromStore = Integer.parseInt(a[0]);

            if (idFromStore == id) {
                fileContent.remove(i);
                break;
            }
        }

        Files.write(Paths.get("data/duke.txt"), fileContent, StandardCharsets.UTF_8);
    }
}
