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
                String currLine = s.nextLine();
                String[] currLineArr = currLine.split("\\|");

                switch (currLineArr[0]) {
                case "T":
                    Task toDoTask = new ToDo(currLineArr[2]);

                    if (currLineArr[1].equals("X")) {
                        toDoTask.markDone();
                    }

                    tasks.add(toDoTask);
                    break;
                case "D":
                    LocalDate currDate = LocalDate.parse(currLineArr[3], Task.YEAR_FORMAT);
                    LocalTime currTime = currLineArr.length < 5 ? null : LocalTime.parse(currLineArr[4],
                        Task.TIME_FORMAT);

                    Task deadlineTask = currTime == null ? new Deadline(currLineArr[2], currDate) : new Deadline(
                        currLineArr[2], currDate, currTime);

                    if (currLineArr[1].equals("X")) {
                        deadlineTask.markDone();
                    }

                    tasks.add(deadlineTask);
                    break;
                case "E":
                    LocalDate dateForEvent = LocalDate.parse(currLineArr[3], Task.YEAR_FORMAT);
                    LocalTime beginTime = LocalTime.parse(currLineArr[4], Task.TIME_FORMAT);
                    LocalTime endTime = LocalTime.parse(currLineArr[5], Task.TIME_FORMAT);
                    Task eventTask = new Event(currLineArr[2], dateForEvent, beginTime, endTime);

                    if (currLineArr[1].equals("X")) {
                        eventTask.markDone();
                    }

                    tasks.add(eventTask);
                    break;
                default:
                    assert false : "File contains illegal modifications";
                }
            }
        } catch (FileNotFoundException e) {
            if (!dukeFolder.exists()) {
                dukeFolder.mkdirs();
            }

            if (!dukeFile.exists()) {
                dukeFile.createNewFile();
            }

            throw new DukeException("Pardon me! But the file was not found");
        }

        return tasks;
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

    private String listInFileFormat(TaskList taskList) {
        List<Task> tasks = taskList.getList();
        StringBuilder list = new StringBuilder();

        for (Task t : tasks) {
            list.append(t.toStringInFileFormat()).append(System.lineSeparator());
        }

        return list.toString();
    }
}
