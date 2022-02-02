package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.exceptions.InvalidTaskException;
import duke.tasks.Task;

public class Storage {
    private static final String DELIMITER = ",";

    private static final String ROOT_FOLDER = System.getProperty("user.dir");
    private static final String DATA_DIR = "data";
    private static final String FILENAME = "duke.txt";
    private static final Path FILE_PATH = Paths.get(ROOT_FOLDER, DATA_DIR, FILENAME);

    public Storage() {
    }

    /**
     * Checks if the file exists.
     *
     * @return boolean of whether the file exists
     */
    public Boolean fileExist() {
        File f = new File(FILE_PATH.toString());
        return f.exists();
    }

    /**
     * Initialise the data file if it doesn't exist.
     *
     * @throws IOException file already exists
     */
    public Boolean initFile() throws IOException {
        File file = new File(FILE_PATH.toString());
        return file.createNewFile();
    }

    /**
     * Read the data from the file.
     *
     * @return a list of Duke.tasks
     */
    public ArrayList<Task> readData() {
        ArrayList<Task> taskList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH.toString()))) {
            String line = br.readLine();
            while (line != null) {
                try {
                    taskList.add(Task.fromCsv(line, DELIMITER));
                    line = br.readLine();
                } catch (InvalidTaskException e) {
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Writes data to the file.
     *
     * @param taskList a list of Duke.tasks
     */
    public void writeData(ArrayList<Task> taskList) {
        if (!fileExist()) {
            try {
                initFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH.toString()))) {
            for (Task t : taskList) {
                bw.write(t.toCsv(DELIMITER) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file!");
            e.printStackTrace();
        }
    }
}
