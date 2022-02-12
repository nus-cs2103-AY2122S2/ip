package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Encapsulates a storage for Duke. It deals with
 * saving data to and loading data from a txt file.
 */
public class Storage {
    private static final String TODO_IDENTIFIER = "T";
    private static final String DEADLINE_IDENTIFIER = "D";
    private static final String EVENT_IDENTIFIER = "E";
    private static final String DELIMITER = " %% ";
    private static final String TASK_DONE = "1";
    private static final String TASK_NOT_DONE = "0";

    private String dirPath;
    private String fileName;

    /**
     * Initialises a Storage object.
     *
     * @param dirPath path to the directory where the txt file is stored.
     * @param fileName name of the txt file.
     */
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
    }

    /**
     * Loads data from the txt file in this Storage object
     * into a given TaskList object.
     *
     * @param taskList the TaskList object to populate with the data.
     */
    public void loadData(TaskList taskList) throws DukeException {
        File taskDataDir = new File(dirPath);
        taskDataDir.mkdirs();
        try {
            File taskData = new File(dirPath + File.separator + fileName);
            if (!taskData.exists()) {
                return;
            }
            fillTaskListWithData(taskList, taskData);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private void fillTaskListWithData(TaskList taskList, File taskData) throws IOException, DukeException {
        Scanner dataScanner = new Scanner(taskData);
        while (dataScanner.hasNext()) {
            String[] currTask = dataScanner.nextLine().split(DELIMITER);
            switch (currTask[0]) {
            case TODO_IDENTIFIER:
                taskList.addTask(new Todo(currTask[2]));
                break;
            case DEADLINE_IDENTIFIER:
                LocalDate deadlineDate = LocalDate.parse(currTask[3]);
                assert deadlineDate != null : "deadlineDate cannot be null";
                taskList.addTask(new Deadline(currTask[2], deadlineDate));
                break;
            case EVENT_IDENTIFIER:
                LocalDate eventDate = LocalDate.parse(currTask[3]);
                assert eventDate != null : "eventDate cannot be null";
                taskList.addTask(new Event(currTask[2], eventDate));
                break;
            default:
                throw new DukeException(
                        String.format("txt file supplied has unknown character %s ", currTask[0]));
            }
            if (currTask[1].equals(TASK_DONE)) {
                markLatestTaskDone(taskList);
            }
        }
    }

    private void markLatestTaskDone(TaskList taskList) {
        assert taskList.getLength() >= 0 : "taskList.getLength should return an int not less than 0";

        Task task = taskList.getTask(taskList.getLength());
        assert task != null : "task retrieved from taskList cannot be null";

        task.markAsDone();
    }

    /**
     * Overwrites the txt file in this Storage object
     * with data from a given TaskList object.
     *
     * @param taskList the TaskList object you want to save.
     */
    public void saveData(TaskList taskList) throws DukeException {
        String tasksToSave = makeStringToSave(taskList);
        try {
            FileWriter writer = new FileWriter(dirPath + File.separator + fileName);
            writer.write(tasksToSave);
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private String makeStringToSave(TaskList taskList) throws DukeException {
        StringBuilder tasksToSave = new StringBuilder();
        for (int i = 1; i < taskList.getLength() + 1; i++) {
            Task currTask = taskList.getTask(i);
            assert currTask != null : "task retrieved from taskList cannot be null";

            String description = currTask.getDescription();
            assert description != null : "description should not be null";
            assert description.strip() != "" : "description should not be an empty String or have whitespace only";
            String done = currTask.isDone() ? TASK_DONE + DELIMITER : TASK_NOT_DONE + DELIMITER;

            if (currTask instanceof Todo) {
                tasksToSave.append(TODO_IDENTIFIER).append(DELIMITER).append(done).append(description);
            } else if (currTask instanceof Deadline) {
                tasksToSave.append(DEADLINE_IDENTIFIER).append(DELIMITER).append(done).append(description)
                        .append(DELIMITER).append(((Deadline) currTask).getDate());
            } else if (currTask instanceof Event) {
                tasksToSave.append(EVENT_IDENTIFIER).append(DELIMITER).append(done).append(description)
                        .append(DELIMITER).append(((Event) currTask).getDate());
            } else {
                throw new DukeException("Unable to save unknown task type");
            }
            tasksToSave.append("\n");
        }
        return tasksToSave.toString();
    }
}
