import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public File directory = new File("data");
    public Path tasksPath = Paths.get("data", "tasks.txt");
    public File tasksFile = new File(tasksPath.toString());

    public ArrayList<Task> loadTasks() throws DukeException {

        ArrayList<Task> allTasks = new ArrayList<>();

        // adds directory if it does not exist
        boolean directoryCreated = directory.mkdir();

        // creates a new file if it does not exist
        boolean didNotExist;
        try {
            didNotExist = tasksFile.createNewFile();
        } catch (IOException err) {
            throw new DukeException(err.getMessage());
        }

        if (!directoryCreated && !didNotExist) {  // the file existed
            try {
                Scanner s = new Scanner(tasksFile); // create a Scanner using the File as the source
                Task t;
                while (s.hasNext()) {
                    String someTask = s.nextLine();
                    try {
                        t = Task.importFromString(someTask);
                        allTasks.add(t);
                    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            } catch (FileNotFoundException err) {
                throw new DukeException(err.getMessage());
            }
        }

        return allTasks;
    }

    public void saveTasks(ArrayList<Task> allTasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(tasksPath.toString());
            for (Task t: allTasks) {
                fw.write(t.exportToString() + "\n");
            }
            fw.close();
        } catch (IOException err) {
            throw new DukeException(err.getMessage());
        }
    }
}