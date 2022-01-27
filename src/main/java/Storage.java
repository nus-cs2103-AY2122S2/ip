import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void appendTaskToFile(Task task) {
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
        } catch (IOException e) {
            Ui.myBossOutput("Error appending task to text file!");
        }
    }

    public void clearTaskFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            Ui.myBossOutput("Error clearing DB");
        }
    }

    public void createDirAndFileIfNonExistent(String fileName) {
        try {
            File fileObj = new File(fileName);
            File parentFile = fileObj.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdir();
            }
            if (!fileObj.exists()) {
                fileObj.createNewFile();
            }
        } catch (IOException e) {
            Ui.myBossOutput("An Error has occurred with file creation!");
        }
    }

    public ArrayList<Task> loadTaskListFromFile() {
        // get taskList from file
        ArrayList<Task> tempTaskList = new ArrayList<>();
        try {
            File dbObj = new File(filePath);
            if (!dbObj.exists()) {
                createDirAndFileIfNonExistent(filePath);
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
            Ui.myBossOutput("Error file not found!");
        }
        return tempTaskList;
    }

    public void updateFile(ArrayList<Task> taskList) {
        createDirAndFileIfNonExistent(filePath);
        clearTaskFile();
        taskList.forEach((task)
                -> appendTaskToFile(task));
    }
}
