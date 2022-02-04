package myboss;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * Represents a storage with specified file path.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage Object. Creates directory and file as specified in file path if
     * it does not already exist.
     *
     * @param filePath path to file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createDirAndFileIfNonExistent();
    }

    /**
     * Appends the specified task to the storage file.
     *
     * @param task task specified.
     * @return whether the task was appended successfully.
     */
    public boolean appendTaskToFile(Task task) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String stringToAppend = task.taskType + "|" + task.isDone + "|" + task.taskName;
            switch (task.taskType) {
                case "E":
                    // typecasting because only an event would have taskType "E"
                    Event event = (Event) task;
                    stringToAppend = stringToAppend + "|" + event.eventDate + "|" + event.timeRange;
                    break;
                case "D":
                    // typecasting because only a deadline would have taskType "D"
                    Deadline deadlineTask = (Deadline) task;
                    stringToAppend = stringToAppend + "|" + deadlineTask.deadline;
                    break;
            }
            fw.write(stringToAppend + System.lineSeparator());
            fw.close();
            return true;
        } catch (IOException e) {
            //Ui.outputMyBoss("Error appending task to text file!");
            return false;
        }
    }

    /**
     * Clears the storage file.
     *
     * @return whether the clear was successful.
     */
    public boolean clearTaskFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
            return true;
        } catch (IOException e) {
            //Ui.outputMyBoss("Error clearing DB");
            return false;
        }
    }

    /**
     * Creates directory and file as in the file path if it does not already exist.
     *
     * @return whether the directory and file was created successfully.
     */
    public boolean createDirAndFileIfNonExistent() {
        try {
            File fileObj = new File(filePath);
            File parentFile = fileObj.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdir();
            }
            if (!fileObj.exists()) {
                fileObj.createNewFile();
            }
            return true;
        } catch (IOException e) {
            //Ui.outputMyBoss("An Error has occurred with file creation!");
            return false;
        }
    }

    /**
     * Loads the list of tasks from storage file.
     *
     * @return the ArrayList of tasks loaded from storage file.
     */
    public ArrayList<Task> loadTaskListFromFile() {
        // get taskList from file
        ArrayList<Task> tempTaskList = new ArrayList<>();
        try {
            File dbObj = new File(filePath);
            if (!dbObj.exists()) {
                createDirAndFileIfNonExistent();
                return new ArrayList<Task>();
            }
            Scanner s = new Scanner(dbObj);
            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] currLineSplit = currLine.split("\\|");
                String taskType = currLineSplit[0];
                boolean isDone = currLineSplit[1].equals("true");
                String taskName = currLineSplit[2];

                if (taskType.equals("E")) {
                    tempTaskList.add(new Event(taskName,
                            currLineSplit[3], currLineSplit[4], isDone));
                } else if (taskType.equals("D")) {
                    tempTaskList.add(new Deadline(taskName, currLineSplit[3], isDone));
                } else {
                    tempTaskList.add(new ToDo(taskName, isDone));
                }
            }
        } catch (FileNotFoundException e) {
            //Ui.outputMyBoss("Error file not found!");
        }
        return tempTaskList;
    }

    /**
     * Overwrites the storage file with the specified list of tasks.
     *
     * @param taskList list of tasks to be written into the storage file.
     */
    public void updateFile(ArrayList<Task> taskList) {
        createDirAndFileIfNonExistent();
        clearTaskFile();
        taskList.forEach((task)
                -> appendTaskToFile(task));
    }
}
