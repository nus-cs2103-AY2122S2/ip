package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Storage functionality of the program. Takes in any new changes to the TaskList and modifies the text
 * file accordingly.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Storage {

    private final File dukeFile;
    private final File dukeFolder;

    /**
     * Constructor for the Storage class
     *
     * @param filePath the file path the text file will be stored in
     */
    public Storage(String filePath) {
        String folderPath = filePath.substring(0, filePath.indexOf("/"));
        this.dukeFile = new File(filePath);
        this.dukeFolder = new File(folderPath);
    }

    /**
     * Creates the text file and directory if the text file does not exist. Otherwise, loads the text file
     * into the TaskList.
     *
     * @return The list of tasks that have been loaded
     * @throws DukeException throws if text file does not exist
     * @throws IOException   throws if text file cannot be created
     */
    public List<Task> load() throws DukeException, IOException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(dukeFile);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] currentLineArr = currentLine.split("\\|");
                switch (currentLineArr[0]) {
                case "T":
                    Task toDoTask = loadToDo(currentLineArr);
                    tasks.add(toDoTask);
                    break;
                case "D":
                    Task deadlineTask = loadDeadline(currentLineArr);
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    Task eventTask = loadEvent(currentLineArr);
                    tasks.add(eventTask);
                    break;
                default:
                    assert false : "File contains illegal modifications";
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            checkIfFolderExists();
            checkIfFileExists();
            throw new DukeException("Pardon me! But the file was not found");
        }
        return tasks;
    }
    private ToDo loadToDo(String[] currentLineArr) {
        ToDo toDoTask = new ToDo(currentLineArr[2]);

        if (currentLineArr[1].equals("X")) {
            toDoTask.markDone();
        }
        return toDoTask;
    }
    private Deadline loadDeadline(String[] currentLineArr) {
        LocalDate currDate = LocalDate.parse(currentLineArr[3], Task.YEAR_FORMAT);
        LocalTime currTime = currentLineArr.length < 5 ? null : LocalTime.parse(currentLineArr[4],
            Task.TIME_FORMAT);

        Deadline deadlineTask = currTime == null ? new Deadline(currentLineArr[2], currDate) : new Deadline(
            currentLineArr[2], currDate, currTime);

        if (currentLineArr[1].equals("X")) {
            deadlineTask.markDone();
        }
        return deadlineTask;
    }

    private Event loadEvent(String[] currentLineArr) {
        LocalDate dateForEvent = LocalDate.parse(currentLineArr[3], Task.YEAR_FORMAT);
        LocalTime beginTime = LocalTime.parse(currentLineArr[4], Task.TIME_FORMAT);
        LocalTime endTime = LocalTime.parse(currentLineArr[5], Task.TIME_FORMAT);
        Event eventTask = new Event(currentLineArr[2], dateForEvent, beginTime, endTime);

        if (currentLineArr[1].equals("X")) {
            eventTask.markDone();
        }
        return eventTask;
    }

    /**
     * Retrieves the changes to be made to the stored text file and modifies it accordingly.
     *
     * @param task  The task to be added to/deleted from the stored text file
     * @param code  The type of action to be performed on the task
     * @param tasks The object representing the list of tasks
     * @throws DukeException throws if an Internal error obstructs the running of the code,
     *                       such as incorrect ConfirmCode
     */
    public void modifyStorage(Task task, ConfirmCodes code, TaskList tasks) throws DukeException {
        try {
            FileWriter fw;
            switch (code) {
            case ADDITION:
                fw = new FileWriter(dukeFile.getPath(), true);
                fw.write(task.toStringInFileFormat() + System.lineSeparator());
                fw.close();
                break;
            case DELETION:
            case SORT:
                fw = new FileWriter(dukeFile.getPath());
                fw.write(listInFileFormat(tasks));
                fw.close();
                break;
            default:
                throw new DukeException("INTERNAL ERROR: Invalid Type Declaration");
            }
        } catch (IOException e) {
            throw new DukeException("INTERNAL ERROR: File cannot be accessed. Check directory.");
        }
    }

    /**
     * Checks if dukeFolder exists. If not, create the folder.
     */
    private void checkIfFolderExists() {
        if (!dukeFolder.exists()) {
            dukeFolder.mkdirs();
        }
    }

    /**
     * Checks if dukeFile exists. If not, create the folder.
     */
    private void checkIfFileExists() throws IOException {
        if (!dukeFile.exists()) {
            dukeFile.createNewFile();
        }
    }

    private String listInFileFormat(TaskList taskList) {
        List<Task> tasks = taskList.getList();
        StringBuilder list = new StringBuilder();

        for (Task t : tasks) {
            list.append(t.toStringInFileFormat()).append(System.lineSeparator());
        }

        return list.toString();
    }
}
