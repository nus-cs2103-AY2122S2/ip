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
            String taskType = taskLineSplit[0];
            boolean isTodo = taskType.equals("T");
            boolean isEvent = taskType.equals("E");
            boolean isCompleted = Integer.parseInt(taskLineSplit[1]) == 1 ? true : false;
            String taskName = taskLineSplit[2];
            String taskTags = taskLineSplit[3].substring(1, taskLineSplit[3].length() - 1);
            Task storedTask;

            if (isTodo) {
                // if event starts with T, initialise as TODO
                storedTask = new Todo(taskName);
            } else if (isEvent) {
                // if event starts with E, initialise as Event
                String startTimeString = taskLineSplit[taskLineSplit.length - 2];
                String endTimeString = taskLineSplit[taskLineSplit.length - 1];
                LocalDateTime startDateTime = Parser.parseDateTime(startTimeString);
                LocalDateTime endDateTime = Parser.parseDateTime(endTimeString);

                storedTask = new Event(taskName, startDateTime, endDateTime);
            } else {
                // otherwise, event must start with D, initialise as Deadline
                String endTimeString = taskLineSplit[taskLineSplit.length - 1];
                LocalDateTime endDateTime = Parser.parseDateTime(endTimeString);
                storedTask = new Deadline(taskName, endDateTime);
            }

            // set completion status
            if (isCompleted) {
                storedTask.markAsComplete();
            } else {
                storedTask.markAsIncomplete();
            }

            // set tags
            String[] taskTagSplit = taskTags.split(TAG_SEPARATOR);
            boolean hasTags = taskTagSplit.length > 0;
            boolean nonEmptyTag = taskTagSplit[0].length() > 0;
            if (hasTags && nonEmptyTag) {
                for (String taskTag: taskTagSplit) {
                    storedTask.addTag(taskTag);
                }
            }

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
     * Overwrite data in "data/list.txt" with current list data
     *
     * @param taskList Arraylist of Tasks data
     * @throws IOException Throws FileNotFoundException error if writing to non-existent file
     */
    public void saveListOnDisk(TaskList taskList) throws IOException {
        String dataText = "";

        for (Task task: taskList.getTasks()) {
            String taskText = "";

            boolean isEvent = task.getEventType().equals(Type.EVENT);
            boolean isTodo = task.getEventType().equals(Type.TODO);
            boolean isDeadline = task.getEventType().equals(Type.DEADLINE);

            // set event type
            if (isEvent) {
                taskText += "E,";
            } else if (isTodo) {
                taskText += "T,";
            } else if (isDeadline) {
                taskText += "D,";
            }

            // set task completion status
            if (task.getIsDone()) {
                taskText += "1,";
            } else {
                taskText += "0,";
            }

            taskText += task.getDescription() + "," + storeTags(task.getTags()) + ",";

            // set task name & timing
            if (isEvent) {
                Event event = (Event) task;
                taskText += event.getStartTimeString()
                        + "," + event.getEndTimeString();
            } else if (isDeadline) {
                Deadline deadline = (Deadline) task;
                taskText += deadline.getEndTimeString();
            }

            taskText += "\n";
            dataText += taskText;
        }

        FileWriter fw = new FileWriter(filePath, false);
        fw.write(dataText);
        fw.close();
    }
}
