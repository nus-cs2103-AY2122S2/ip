package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the file storing saved user tasks
 */
public class Storage {
    private String filePath;
    private File file;

    Storage(String filePath) throws DukeFileNotFoundException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.isFile()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeFileNotFoundException();
            }
        }
    }

    /**
     * Loads tasks from the saved file to the user application
     * @return List of tasks found in the saved file
     * @throws DukeInvalidFileException Throws error when file saved is invalid
     */
    public ArrayList<Task> load() throws DukeInvalidFileException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                taskList.add(Parser.parseFile(nextLine));
                nextLine = bufferedReader.readLine();
            }
        } catch (IOException | DukeInvalidFileException e) {
            throw new DukeInvalidFileException();
        }
        return taskList;
    }

    /**
     * Saves tasks into a file
     * @param taskList <code>ArrayList</code> of tasks
     * @throws DukeInvalidFileSaveException Throws exception when tasks cannot be saved
     */
    public void save(ArrayList<Task> taskList) throws DukeInvalidFileSaveException {
        String savedTasks = "";
        for (Task task: taskList) {
            String completed = "";
            if (task.isCompleted()) {
                completed = "done";
            } else {
                completed = "undone";
            }
            String type = task.getType();
            String savedTask = "";
            switch (type) {
            case "ToDo":
                savedTasks += Parser.parseSavedToDoTask((ToDo) task);
                break;
            case "Deadline":
                savedTasks += Parser.parseSavedDeadlineTask((Deadline) task);
                break;
            case "Event":
                savedTasks += Parser.parseSavedEventTask((Event) task);
                break;
            default:
                break;
            }
            savedTasks += " " + completed;
            savedTasks += "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(savedTasks);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeInvalidFileSaveException();
        }
    }
}
