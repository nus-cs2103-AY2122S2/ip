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

    /**
     * String containing path to save list of tasks
     */
    private String filePath;

    public Storage(String filePath) {
        assert filePath != null && filePath != "";
        this.filePath = filePath;
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

            return new ArrayList<>();

        } else {
            // if list.txt file exists, load from disk and return
            // ArrayList of Tasks from list.txt
            Scanner f = new Scanner(defaultFile);
            ArrayList<Task> data = new ArrayList<>();

            while (f.hasNext()) {
                String[] taskLineSplit = f.nextLine().split(",");
                Boolean isCompleted = Integer.parseInt(taskLineSplit[1]) == 1 ? true : false;
                String taskName = taskLineSplit[2];
                Task storedTask;

                if (taskLineSplit[0].equals("T")) {
                    // if event starts with T, initialise as TODO
                    storedTask = new Todo(taskName);
                } else if (taskLineSplit[0].equals("E")) {
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

                data.add(storedTask);
            }

            return data;
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
            // set event type
            if (task.getEventType().equals(Type.EVENT)) {
                taskText += "E,";
            } else if (task.getEventType().equals(Type.TODO)) {
                taskText += "T,";
            } else if (task.getEventType().equals(Type.DEADLINE)) {
                taskText += "D,";
            }

            // set task completion status
            if (task.getIsDone()) {
                taskText += "1,";
            } else {
                taskText += "0,";
            }

            // set task name & timing
            if (task.getEventType().equals(Type.TODO)) {
                taskText += task.getDescription();
            } else if (task.getEventType().equals(Type.EVENT)) {
                Event event = (Event) task;
                taskText += task.getDescription()
                        + ","
                        + event.getStartTimeString()
                        + ","
                        + event.getEndTimeString();
            } else if (task.getEventType().equals(Type.DEADLINE)) {
                Deadline deadline = (Deadline) task;
                taskText += task.getDescription()
                        + ","
                        + deadline.getEndTimeString();
            }

            taskText += "\n";
            dataText += taskText;
        }

        FileWriter fw = new FileWriter(filePath, false);
        fw.write(dataText);
        fw.close();
    }
}
