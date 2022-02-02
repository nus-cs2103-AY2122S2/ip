package jose;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jose.task.Task;

/**
 * A class that handles the reading and writing of data stored in the data file.
 */
public class Storage {
    private File file;

    /**
     * The main constructor that also creates a data file and its relevant directories if one does not already exist.
     *
     * @param filePath The path of the data file.
     * @throws IOException If there are any errors creating the data file.
     */
    public Storage(String filePath) throws IOException {
        file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Reads the data file and compiles all the tasks saved in it for further processing.
     *
     * @return An ArrayList containing the tasks stored in the data file.
     * @throws IOException If there are any errors accessing the data file.
     */
    public ArrayList<String> load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> tasks = new ArrayList<>();
        String s;

        while ((s = br.readLine()) != null) {
            tasks.add(s);
        }

        br.close();
        return tasks;
    }

    /**
     * Rewrites the data file with the given list of tasks.
     *
     * @param tasks  A list of tasks to be stored in the data file.
     * @throws IOException If there are any errors when writing to the data file.
     */
    public void update(TaskList tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        for (Task t : tasks.getTasks()) {
            bw.write(t.formatData());
            bw.newLine();
        }

        bw.close();
    }
}
