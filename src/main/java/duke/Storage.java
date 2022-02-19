package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles storing list of tasks to disk,
 * default filepath: "data/list.txt"
 */
public class Storage {

    private static final String TAG_SEPARATOR = "_";
    private static final String EMPTY_TAGS = "[]";
    /**
     * String containing path to save list of tasks
     */
    private String filePath;


    public Storage(String filePath) {
        assert filePath != null && filePath != "";
        this.filePath = filePath;
    }

    /**
     * Render task tags to be stored locally
     *
     * @param tags Arraylist of tags associated w task
     * @return String representation of tags formatted for storage
     */
    private String storeTags(ArrayList<String> tags) {
        boolean hasTags = tags.size() > 0;
        String renderStr = "";

        if (!hasTags) {
            return EMPTY_TAGS;
        }

        renderStr += "[";

        for (int i = 0; i < tags.size(); i++) {
            boolean isLastTag = i == tags.size() - 1;
            String tag = tags.get(i);

            renderStr += tag;

            if (!isLastTag) {
                renderStr += TAG_SEPARATOR;
            } else {
                renderStr += "]";
            }
        }
        return renderStr;
    }

    /**
     * Create default file that stores data for duke
     *
     * @param defaultFile File object of file path used to store duke data
     */
    public void createStorageFile(File defaultFile) {
        if (!defaultFile.getParentFile().exists()) {
            String output = "Data directory does not exist, creating list file now at: "
                    + this.filePath
                    + "\n";
            System.out.println(output);
            if (!defaultFile.getParentFile().mkdirs()) {
                System.out.println("Error occurred when trying to create Data directory :(");
            }
        }

        try {
            defaultFile.createNewFile();
        } catch (IOException err) {
            System.out.println("An IO error occurred while trying to create list.txt file :(");
            err.printStackTrace();
        }
    }

    /**
     * Set task class type based on type provided from storage
     *
     * @param taskName Name/description of task
     * @param taskLineSplit Array of task details loaded from storage
     * @return Task with specific type
     */
    private Task setTaskType(String taskName, String[] taskLineSplit) {
        String taskType = taskLineSplit[0];
        boolean isTodo = taskType.equals("T");
        boolean isEvent = taskType.equals("E");

        if (isTodo) {
            // if event starts with T, initialise as TODO
            return new Todo(taskName);

        } else if (isEvent) {
            // if event starts with E, initialise as Event
            String startTimeString = taskLineSplit[taskLineSplit.length - 2];
            String endTimeString = taskLineSplit[taskLineSplit.length - 1];
            LocalDateTime startDateTime = Parser.parseDateTime(startTimeString);
            LocalDateTime endDateTime = Parser.parseDateTime(endTimeString);

            return new Event(taskName, startDateTime, endDateTime);

        } else {
            // otherwise, event must start with D, initialise as Deadline
            String endTimeString = taskLineSplit[taskLineSplit.length - 1];
            LocalDateTime endDateTime = Parser.parseDateTime(endTimeString);
            return new Deadline(taskName, endDateTime);
        }
    }

    /**
     * Set tags of loaded task
     *
     * @param task Task loaded from storage
     * @param taskTags Tags of task loaded from storage as a single string
     * @return Task with updated tags
     */
    private Task setTaskTags(Task task, String taskTags) {
        String[] taskTagSplit = taskTags.split(TAG_SEPARATOR);
        boolean hasTags = taskTagSplit.length > 0;
        boolean nonEmptyTag = taskTagSplit[0].length() > 0;
        if (hasTags && nonEmptyTag) {
            for (String taskTag: taskTagSplit) {
                task.addTag(taskTag);
            }
        }

        return task;
    }

    /**
     * Set completion status of loaded task
     *
     * @param task Task loaded from storage
     * @param isCompleted Task completed status
     * @return Task with updated completion status
     */
    private Task setTaskCompletionStatus(Task task, boolean isCompleted) {
        if (isCompleted) {
            task.markAsComplete();
        } else {
            task.markAsIncomplete();
        }

        return task;
    }

    /**
     * Loads duke data from default file provided
     *
     * @param defaultFile File object of filepath used to store duke data
     * @return ArrayList of task data stored locally from the default file provided
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadFromDefaultFile(File defaultFile) throws FileNotFoundException {
        Scanner f = new Scanner(defaultFile);
        ArrayList<Task> data = new ArrayList<>();

        while (f.hasNext()) {
            String[] taskLineSplit = f.nextLine().split(",");
            boolean isCompleted = Integer.parseInt(taskLineSplit[1]) == 1 ? true : false;
            String taskName = taskLineSplit[2];
            String taskTags = taskLineSplit[3].substring(1, taskLineSplit[3].length() - 1);
            Task storedTask;

            // set task type
            storedTask = setTaskType(taskName, taskLineSplit);

            // set completion status
            storedTask = setTaskCompletionStatus(storedTask, isCompleted);

            // set tags
            storedTask = setTaskTags(storedTask, taskTags);

            data.add(storedTask);
        }

        return data;
    }

    /**
     * Load list data from "data/list.txt" if exists, otherwise
     * create empty "data/list.txt" file and return empty arraylist
     *
     * @return Arraylist of Tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadListFromDisk() throws FileNotFoundException {
        File defaultFile = new File(this.filePath);

        // if list.txt does not exist, create empty list.txt file
        // and return an empty ArrayList
        if (!defaultFile.exists()) {
            createStorageFile(defaultFile);
            return new ArrayList<>();

        } else {
            // if list.txt file exists, load from disk and return
            // ArrayList of Tasks from list.txt
            return loadFromDefaultFile(defaultFile);
        }
    }

    /**
     * Save task timings to string to be stored
     *
     * @param task Task to be saved
     * @return Returns completion status as a string
     */
    private String saveTaskTiming(Task task) {
        boolean isEvent = task.getEventType().equals(Type.EVENT);
        boolean isDeadline = task.getEventType().equals(Type.DEADLINE);

        if (isEvent) {
            // if event, return start and end timings
            Event event = (Event) task;
            return event.getStartTimeString() + "," + event.getEndTimeString();
        } else if (isDeadline) {
            // if deadline, return end timing
            Deadline deadline = (Deadline) task;
            return deadline.getEndTimeString();
        } else {
            // otherwise, return nothing since no timing data for other types
            return "";
        }
    }

    /**
     * Save task completion status to string to be stored
     *
     * @param task Task to be saved
     * @return Returns completion status as a string
     */
    private String saveTaskCompletionStatus(Task task) {
        // set task completion status
        if (task.getIsDone()) {
            return "1,";
        } else {
            return "0,";
        }
    }

    /**
     * Save task type to string to be stored
     *
     * @param task Task to be saved
     * @return Returns type of task as a string
     */
    private String saveTaskType(Task task) {
        boolean isEvent = task.getEventType().equals(Type.EVENT);
        boolean isTodo = task.getEventType().equals(Type.TODO);
        boolean isDeadline = task.getEventType().equals(Type.DEADLINE);

        // set event type
        if (isEvent) {
            return "E,";
        } else if (isTodo) {
            return "T,";
        } else if (isDeadline) {
            return "D,";
        }

        assert false;
        return "";
    }

    /**
     * Overwrite data in "data/list.txt" with current list data
     *
     * @param taskList Arraylist of Tasks data
     * @throws IOException Throws FileNotFoundException error if writing to non-existent file
     */
    public void saveListOnDisk(TaskList taskList) throws IOException {
        String dataText = "";

        for (Task task: taskList.getTasks()) {
            String taskText = "";

            taskText += saveTaskType(task);

            // set task completion status
            taskText += saveTaskCompletionStatus(task);

            // save task description & tags
            taskText += task.getDescription() + "," + storeTags(task.getTags()) + ",";

            // set task timing(s)
            taskText += saveTaskTiming(task);

            // add newline and append task data to total task data
            taskText += "\n";
            dataText += taskText;
        }

        FileWriter fw = new FileWriter(filePath, false);
        fw.write(dataText);
        fw.close();
    }
}
