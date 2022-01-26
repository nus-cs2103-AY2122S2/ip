package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.UnableToStoreLineException;
import seedu.duke.exceptions.UnableToUpdateDatabaseException;
import seedu.duke.task.*;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final Ui ui;

    Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    public String getFilePath() {
        return this.filePath;
    }

    TaskList getOldTaskList() throws DukeException {
        TaskList oldTaskList = new TaskList();
        System.out.println("Hold on, I am checking if you have tasks saved.");
        // If the file does not exist,we create the file.
        // If the file exists, then we scan it to update the taskList
        File myObj;
        try {
            myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                ui.showFileCreated(myObj);
            } else {
                try {
                    Scanner sc = new Scanner(myObj);
                    StringBuilder sb = new StringBuilder();
                    while (sc.hasNext()) {
                        String taskDetails = sc.nextLine();
                        Task taskToAdd = getTaskFromSummary(taskDetails);
                        oldTaskList = oldTaskList.add(taskToAdd);
                    }
                    sc.close();
                    ui.showLoadingResult(oldTaskList);
                } catch (FileNotFoundException e) {
                    throw new DukeException("Hmmm seems like the file doesn't exist.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("An error occured. I will restart the list");
        }
        return oldTaskList;
    }

    Task getTaskFromSummary(String taskDetails) {
        //taskType is the first letter - e.g. "T"f
        String taskType = taskDetails.substring(0,1);
        //start from index 2 to skip space
        boolean doneStatus = Integer.parseInt(taskDetails.substring(2,3)) == 1;
        //taskname
        String taskName;
        //only tasks with dates have '/'
        if (taskDetails.contains("/")) {
            int indexOfSlash = taskDetails.indexOf("/");
            taskName = taskDetails.substring(4,indexOfSlash - 1);
            String date = taskDetails.substring(indexOfSlash + 2); //"/ Sunday"
            if (taskType.equals("E")) {
                return new Event(taskName, doneStatus, date);
            } else {
                return new Deadline(taskName, doneStatus, date);
            }
        } else {
            taskName = taskDetails.substring(4);
            return new ToDo(taskName, doneStatus);
        }
    }

    public String createSummaryFromTask(Task task) {
        String taskType = task.getTaskType();
        String  summary = taskType + " ";
        summary += (task.isDone()) ? "1 " : "0 ";
        summary += task.getTaskName() + " ";
        if (taskType.equals("E") || taskType.equals("D")) {
            summary += "/ " + task.getDate();
        }
        return summary + "\n";
    }

    public void addLine(String filePath, String lineContent) throws DukeException {
        File f = new File(filePath);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(lineContent);
            bw.close();
        } catch (IOException e) {
            throw new UnableToStoreLineException();
        }
    }

    public void convertTaskListToFile(TaskList taskList) throws DukeException {
        //rewrite to a new file
        File f = new File(this.filePath);
        if (f.delete()) {
            ui.showCompleteUpdateOfFile();
        } else {
            throw new UnableToUpdateDatabaseException();
        }
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            String lineToAdd = this.createSummaryFromTask(taskList.getTasks().get(i));
            this.addLine(filePath, lineToAdd);
        }
    }
}

