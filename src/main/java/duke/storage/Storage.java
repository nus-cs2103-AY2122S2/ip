package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class that handles create, read and write file operations.
 */
public class Storage {
    private final File dataFile;

    /**
     * Constructor to initialize an instance of Storage class with folder
     * name and file name.
     *
     * @param folderName Folder name of the data file
     * @param fileName File name of the data file
     * @throws DukeException If the directory cannot be initialised
     * @throws IOException If the storage file cannot be initialised
     */
    public Storage(String folderName, String fileName) throws DukeException, IOException {
        File directory = initialiseDirectory(folderName);
        dataFile = initialiseFile(directory, fileName);
    }

    /**
     * Initializes the directory of the data file.
     * Creates the directory if it does not exist.
     *
     * @param folderName Folder name of the data file
     * @return The relative path of the directory
     * @throws DukeException If the directory cannot be initialised
     */
    private File initialiseDirectory(String folderName) throws DukeException {
        File directory = new File(folderName);
        boolean hasDirectory = directory.exists();

        if (!hasDirectory) {
            hasDirectory = directory.mkdir();
        }

        if (hasDirectory) {
            return directory;
        } else {
            throw new DukeException("\t" + "Unable to initialise directory");
        }
    }

    /**
     * Initializes the data file.
     * Creates the data file if it does not exist.
     *
     * @param directory Relative path of the directory
     * @param fileName File name of the data file
     * @return The data file
     * @throws IOException If the data file cannot be initialised
     */
    private File initialiseFile(File directory, String fileName) throws IOException {
        File file = new File(directory + "/" + fileName);
        boolean hasFile = file.exists();

        if (!hasFile) {
            hasFile = file.createNewFile();
        }

        if (hasFile) {
            return file;
        } else {
            throw new IOException("\t" + "Unable to initialise file");
        }
    }

    /**
     * Loads all the tasks in the data file to the task list.
     *
     * @return The task list containing the tasks
     * @throws IOException If the data file or task cannot be read
     * @throws DukeException If an invalid type of task is found
     */
    public TaskList loadTasksFromFile() throws IOException, DukeException {
        TaskList taskList = new TaskList();

        FileReader fileReader = new FileReader(dataFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] taskDetails = line.trim().split("\\|");
            String type = taskDetails[0].trim();
            boolean isDone = (Integer.parseInt(taskDetails[1].trim()) == 1);
            String description = taskDetails[2].trim();

            if (type.equals(TaskType.TODO.getAbbreviation())) {
                Task todoTask = new ToDo(description, isDone);
                taskList.addTask(todoTask);
            } else if (type.equals(TaskType.DEADLINE.getAbbreviation())) {
                String dateTime = taskDetails[3].trim();
                Task deadlineTask = new Deadline(description, dateTime, isDone);
                taskList.addTask(deadlineTask);
            } else if (type.equals(TaskType.EVENT.getAbbreviation())) {
                String dateTime = taskDetails[3].trim();
                Task eventTask = new Event(description, dateTime, isDone);
                taskList.addTask(eventTask);
            } else {
                // Error detection for any invalid type of tasks found in the
                // storage file. This should not happen since the user is only
                // allowed to create todo [T], deadline [D] and event [E] tasks.
                throw new DukeException("INVALID TYPE OF TASK FOUND");
            }
        }

        bufferedReader.close();

        return taskList;
    }

    /**
     * Saves all the tasks in the task list to the data file.
     *
     * @param taskList Task list containing the tasks to be saved
     * @throws IOException If the tasks cannot be saved to the data file
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            Task task = taskList.getTask(i);
            bufferedWriter.write(task.saveFormat() + System.lineSeparator());
        }

        bufferedWriter.close();
    }
}
