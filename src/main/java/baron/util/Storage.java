package baron.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.tasks.Deadline;
import baron.tasks.Event;
import baron.tasks.Task;
import baron.tasks.TaskType;
import baron.tasks.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the data file.
 */
public class Storage {
    private static final String FILE_DELIMITER = "rwN>fox8@j`XNc;CA#FYzLogY.5Ze";
    private final File file;

    /**
     * Constructs the {@code Storage} object with {@code File} location at the specified
     * relative file path.
     *
     * @param relativeFilePath the relative file path that user wants to store the data at.
     */
    public Storage(String relativeFilePath) {
        this.file = new File(relativeFilePath);
    }

    /**
     * Loads and returns all the tasks in an {@code ArrayList<Task>}.
     *
     * @return all the tasks in the form of an {@code ArrayList<Task>}.
     * @throws BaronException if the file is not found or the parsing of the data file fails.
     */
    public ArrayList<Task> load() throws BaronException {
        this.createFileIfNotExists();
        if (this.file.exists() && this.file.isFile()) {
            Scanner fileReader;
            try {
                fileReader = new Scanner(this.file);
            } catch (FileNotFoundException e) {
                throw new BaronException(Message.MESSAGE_FILE_NOT_FOUND);
            }

            ArrayList<Task> newTaskList = new ArrayList<>();

            while (fileReader.hasNext()) {
                String taskString = fileReader.nextLine().strip();
                newTaskList.add(parseTaskString(taskString));
            }
            return newTaskList;
        } else {
            throw new BaronException(Message.MESSAGE_FILE_NOT_FOUND);
        }
    }

    /**
     * Saves the given task list of type {@code ArrayList<Task>} into the data file.
     *
     * @param taskList the list of tasks to be saved.
     * @throws BaronException if data file is not found or {@code FileWriter} throws IOException.
     */
    public void save(ArrayList<Task> taskList) throws BaronException {
        this.createFileIfNotExists();
        if (this.file.exists() && this.file.isFile()) {
            try {
                FileWriter fileWriter = new FileWriter(this.file);
                for (Task task : taskList) {
                    fileWriter.write(task.toSaveString(Storage.FILE_DELIMITER) + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                throw new BaronException(Message.MESSAGE_FILE_WRITE_FAIL);
            }
        } else {
            throw new BaronException(Message.MESSAGE_FILE_NOT_FOUND);
        }
    }

    private Task parseTaskString(String taskString) throws BaronException {
        String[] taskStrings = taskString.split(Storage.FILE_DELIMITER, 4);

        if (taskStrings.length < 3 || taskStrings.length > 4) {
            throw new BaronException(Message.MESSAGE_INVALID_FILE_FORMAT);
        }

        assert (taskStrings.length == 3) || (taskStrings.length == 4) : "taskStrings.length must be 3 or 4";

        TaskType taskType = getTaskType(taskStrings);
        boolean isDone = getIsDone(taskStrings);
        String description = getDescription(taskStrings);
        LocalDateTime additionalInfo = getAdditionalInfo(taskStrings);

        Task newTask;

        if (taskType == TaskType.TODO) {
            if (additionalInfo != null) {
                throw new BaronException(Message.MESSAGE_INVALID_FILE_FORMAT);
            } else {
                newTask = new ToDo(description);
            }
        } else {
            if (additionalInfo == null) {
                throw new BaronException(Message.MESSAGE_INVALID_FILE_FORMAT);
            } else {
                if (taskType == TaskType.DEADLINE) {
                    newTask = new Deadline(description, additionalInfo);
                } else {
                    newTask = new Event(description, additionalInfo);
                }
            }
        }

        if (isDone) {
            newTask.mark();
        }
        return newTask;
    }

    private TaskType getTaskType(String[] taskStrings) throws BaronException {
        assert taskStrings.length == 3 || taskStrings.length == 4 : "taskStrings.length must be 3 or 4";
        switch (taskStrings[0]) {
        case "T":
            return TaskType.TODO;
        case "D":
            return TaskType.DEADLINE;
        case "E":
            return TaskType.EVENT;
        default:
            throw new BaronException(Message.MESSAGE_INVALID_FILE_FORMAT);
        }
    }

    private boolean getIsDone(String[] taskStrings) throws BaronException {
        assert taskStrings.length == 3 || taskStrings.length == 4 : "taskStrings.length must be 3 or 4";
        switch (taskStrings[1]) {
        case "0":
            return false;
        case "1":
            return true;
        default:
            throw new BaronException(Message.MESSAGE_INVALID_FILE_FORMAT);
        }
    }

    private String getDescription(String[] taskStrings) {
        assert taskStrings.length == 3 || taskStrings.length == 4 : "taskStrings.length must be 3 or 4";
        return taskStrings[2];
    }

    private LocalDateTime getAdditionalInfo(String[] taskStrings) throws BaronException {
        assert taskStrings.length == 3 || taskStrings.length == 4 : "taskStrings.length must be 3 or 4";
        if (taskStrings.length == 3) {
            return null;
        }
        return DateTimeUtil.getDateTime(taskStrings[3]);
    }

    private void createFileIfNotExists() throws BaronException {
        if (!(this.file.exists() && this.file.isFile())) {
            if (this.file.isDirectory()) {
                if (!this.file.delete()) {
                    throw new BaronException(Message.MESSAGE_FILE_CREATION_FAIL);
                }
            }
            if (this.file.getParentFile() != null) {
                this.file.getParentFile().mkdirs();
            }

            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new BaronException(Message.MESSAGE_FILE_CREATION_FAIL);
            }
        }
    }
}
