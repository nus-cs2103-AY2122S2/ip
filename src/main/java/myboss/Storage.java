package myboss;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public Storage(String filePath) throws MyBossException {
        this.filePath = filePath;
        createDirAndFileIfNonExistent();
    }

    /**
     * Appends the specified task to the storage file.
     *
     * @param task task specified.
     * @return whether the task was appended successfully.
     */
    public boolean appendTaskToFile(Task task) throws MyBossException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String stringToAppend = task.getTaskType() + "|" + task.getIsDone() + "|" + task.getTaskName();
            switch (task.getTaskType()) {
            case "E":
                // typecasting because only an event would have taskType "E"
                Event event = (Event) task;
                stringToAppend = stringToAppend + "|" + event.getEventDate() + "|" + event.getTimeRange();
                break;
            case "D":
                // typecasting because only a deadline would have taskType "D"
                Deadline deadlineTask = (Deadline) task;
                stringToAppend = stringToAppend + "|" + deadlineTask.getDeadline();
                break;
            case "T":
                break;
            default:
                throw new MyBossException(Ui.APPEND_TO_FILE_EXCEPTION_MSG);
            }
            fw.write(stringToAppend + System.lineSeparator());
            fw.close();
            return true;
        } catch (IOException e) {
            throw new MyBossException(Ui.APPEND_TO_FILE_EXCEPTION_MSG);
        }
    }

    /**
     * Clears the storage file.
     *
     * @return whether the clear was successful.
     */
    public boolean clearTaskFile() throws MyBossException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
            return true;
        } catch (IOException e) {
            //Ui.outputMyBoss("Error clearing DB");
            throw new MyBossException(Ui.CLEAR_FILE_EXCEPTION_MSG);
        }
    }

    /**
     * Creates directory and file as in the file path if it does not already exist.
     *
     * @return whether the directory and file was created successfully.
     */
    public boolean createDirAndFileIfNonExistent() throws MyBossException {
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
            throw new MyBossException(Ui.FILE_CREATION_EXCEPTION_MSG);
        }
    }

    /**
     * Loads the list of tasks from storage file.
     *
     * @return the ArrayList of tasks loaded from storage file.
     */
    public ArrayList<Task> loadTaskListFromFile() throws MyBossException {
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
            throw new MyBossException(Ui.FILE_NOT_FOUND_EXCEPTION_MSG);
        }
        return tempTaskList;
    }

    /**
     * Overwrites the storage file with the specified list of tasks.
     *
     * @param taskList list of tasks to be written into the storage file.
     */
    public void updateFile(ArrayList<Task> taskList) throws MyBossException {
        createDirAndFileIfNonExistent();
        clearTaskFile();
        for (int i = 0; i < taskList.size(); i++) {
            appendTaskToFile(taskList.get(i));
        }
    }
}
