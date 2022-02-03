package duke;

import duke.dukeexceptions.DeadlineException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;

/**
 * Storage class that helps to save or load existing data from local site
 */
public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Will load the data if it exist, if it does not exist, it will create a new file named "tasks.txt"
     * @return An array of loaded tasks
     * @throws IOException error loading the file
     */
    public ArrayList<Task> load() throws IOException {
        // TODO: 26/1/2022
        ArrayList<Task> result = new ArrayList<>();
        try {
            File path = new File(filePath);
            File directory = new File("data");
            if (directory.mkdir()) {
                System.out.println("directory ./data/ created");
                File data = new File(filePath);
                data.createNewFile();
                System.out.println("then, file ./data/tasks is also created");
                return result;
            } else {
                if (path.createNewFile()) {
                    System.out.println("file ./data/tasks.txt created");
                } else {
                    System.out.println("normal");
                    result = loadFile(path);
                    return result;
                }
                return result;
            }


        } catch(IOException | DeadlineException e) {
            System.out.println(e);
            return result;
        }

    }

    /**
     * Method to load file line by line and translate them into list of tasks
     * @param path file path to the data
     * @return An array of tasks
     * @throws FileNotFoundException the file is missing
     * @throws IOException error loading the file
     */
    public ArrayList<Task> loadFile(File path) throws FileNotFoundException, IOException, DeadlineException {
        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);
        ArrayList<Task> result = new ArrayList<>();
        while(br.ready()) {
            String[] task = br.readLine().split(" \\| ");
            boolean done;
            done = task[1].equals("1");
            switch (task[0]) {
            case "T":
                result.add(new Todo(task[2], done));
                break;
            case "D":
                result.add(new Deadline(task[2], task[3], done));
                break;
            case "E":
                result.add(new Event(task[2], task[3], done));
                break;
            }
        }
        return result;
    }

    /**
     * Write current list of tasks into the file
     * @param taskList Current list of tasks
     * @throws IOException error loading the file
     */
    public void saveFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i <taskList.size() ; i++) {
            fileWriter.write(taskList.get(i).dataFormatOfTask() + "\n");
        }
        fileWriter.close();
    }


}
