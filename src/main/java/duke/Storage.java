package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Holds the filepath to the file in which the data will be stored
 */
public class Storage {
    private String filePath;
    private Path path;

    Storage(String filePath) {
        this.filePath = filePath;
        String home =  System.getProperty("user.home"); // base directory
        // following code should give me [HOME_DIRECTORY]/Desktop/iP/data
        this.path = java.nio.file.Paths.get(home,"Desktop", "iP", "data");
    }

    /**
     * Converts String stored in the database to create tasks when booting up Duke
     *
     * @param description Line read from the data file
     * @return A task which will be added to the Tasklist
     */
    private Task convertStringToTask(String description) {
        String[] splitDescription = description.split("\\|");
        String taskType = splitDescription[0];
        Task tempTask = new Task("Temp task");
        switch (taskType) {
        case "T":
            Task newToDo = new ToDos(splitDescription[2]);
            if (splitDescription[1].equals("1")) {
                newToDo.markAsDone();
            }
            tempTask = newToDo;
            break;

        case "D":
            Task newDeadline = new Deadlines(splitDescription[2], new DateTime(splitDescription[3].split("[- ]")));
            if (splitDescription[1].equals("1")) {
                newDeadline.markAsDone();
            }
            tempTask = newDeadline;
            break;

        case "E":
            Task newEvent = new Events(splitDescription[2], new DateTime(splitDescription[3].split("[- ]")));
            if (splitDescription[1].equals("1")) {
                newEvent.markAsDone();
            }
            tempTask = newEvent;
            break;

        default:
            System.out.println("All tasks loaded!");

        }
        return tempTask;
    }

    /**
     * Read the data file and store it into an ArrayList<Task>
     *
     * @return An ArrayList of Tasks
     * @throws IOException Missing file
     * @throws DukeException Missing file
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> masterList = new ArrayList<>();
        try {
            File dukeStore = new File(this.path + this.filePath);
            Scanner fileReader = new Scanner(dukeStore);
            while (fileReader.hasNextLine()) {
                masterList.add(convertStringToTask(fileReader.nextLine()));
            }
        } catch (IOException e) {
            Path filePath = Paths.get("data");
            boolean isDirectoryExists = Files.exists(filePath);
            if (!isDirectoryExists) {
                new File("data").mkdir();
            }
            new File(path + "/tasks.txt").createNewFile();
            throw new DukeException("File not found. Creating new file...");
        }

        return masterList;
    }

    /**
     * Save all tasks before exiting Duke
     *
     * @param tasklist List of tasks to be saved
     * @throws IOException Missing file
     */
    public void saveAllTasks(TaskList tasklist) throws IOException {
        File dukeStore = new File(this.path + this.filePath);
        FileWriter fw = new FileWriter(dukeStore);
        for (int i = 0; i < tasklist.size(); i++) {
            fw.write(taskToString(tasklist.get(i)));
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * Converts a task to String for storage purposes
     *
     * @param task Task to be converted to String to be stored
     * @return String of Task
     */
    private final String taskToString(Task task) {
        String toReturn = "";
        if (task instanceof ToDos) {
            toReturn += "T|";
        } else if (task instanceof Deadlines) {
            toReturn += "D|";
        } else if (task instanceof Events) {
            toReturn += "E|";
        }
        if (task.isDone) {
            toReturn += "1|";
        } else {
            toReturn += "0|";
        }
        toReturn += task.description.trim();
        if (task instanceof Deadlines) {
            String duration = ((Deadlines) task).getDateTimeForStorage();
            toReturn += "|" + duration;
        } else if (task instanceof Events) {
            String duration = ((Events) task).getDateTimeForStorage();
            toReturn += "|" + duration;
        }
        return toReturn;
    }



}
