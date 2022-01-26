package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final Ui ui;

    Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    TaskList getOldTaskList() throws DukeException {
        //purpose of getOldTaskList: take in input from the file and return a tasklist
        TaskList oldTaskList = new TaskList();
        //ui.startLoadingOldTasks()
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
        //taskType is the first letter - e.g. "T" for ToDo
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

    String createSummaryFromTask(Task task) {
        String taskType = task.getTaskType();
        String  summary = taskType + " ";
        summary += (task.isDone()) ? "1 " : "0 ";
        summary += task.getTaskName() + " ";
        if (taskType.equals("E") || taskType.equals("D")) {
            summary += "/ " + task.getDate();
        }
        return summary;
    }

    //referenced from StackOverflow
    void removeLine(String lineContent) throws IOException
    {
        File file = new File("C:\\Users\\isabe\\IdeaProjects\\ip-false\\src\\data\\oldTasks.txt");
        File temp = new File("_temp_");
        PrintWriter out = new PrintWriter(new FileWriter(temp));
        Files.lines(file.toPath())
                .filter(line -> !line.contains(lineContent))
                .forEach(out::println);
        out.flush();
        out.close();
        temp.renameTo(file);
    }

    //referenced from BaelDung
    void addLine(String lineContent) throws IOException {
        Files.write(
                Paths.get("oldTasks.txt"),
                lineContent.getBytes(),
                StandardOpenOption.APPEND);
    }
}
