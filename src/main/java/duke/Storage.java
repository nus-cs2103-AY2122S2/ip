package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Encapsulates a storage for Duke. It deals with
 * saving data to and loading data from a txt file.
 */
public class Storage {
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
     * @param ui the user interface of Duke.
     */
    public void loadData(TaskList taskList, Ui ui) {
        File taskDataDir = new File(this.dirPath);
        taskDataDir.mkdirs();
        try {
            File taskData = new File(this.dirPath + File.separator + this.fileName);
            if (taskData.exists()) {
                Scanner dataScanner = new Scanner(taskData);
                while (dataScanner.hasNext()) {
                    String[] currTask = dataScanner.nextLine().split("\\|");
                    switch (currTask[0]) {
                    case "T":
                        taskList.addTask(new Todo(currTask[2]));
                        break;
                    case "D":
                        taskList.addTask(new Deadline(currTask[2], LocalDate.parse(currTask[3])));
                        break;
                    case "E":
                        taskList.addTask(new Event(currTask[2], LocalDate.parse(currTask[3])));
                        break;
                    }
                    if (currTask[1].equals("1")) {
                        taskList.getTask(taskList.getLength()).markAsDone();
                    }
                }
            }
            ui.showLoadingSuccess(taskList);
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
    }
    /**
     * Overwrites the txt file in this <Code>Storage</Code> object
     * with data from a given <Code>TaskList</Code> object.
     *
     * @param taskList the <Code>TaskList</Code> object you want to save.
     * @param ui the user interface of Duke.
     */
    public void saveData(TaskList taskList, Ui ui) {
        StringBuilder tasksToSave = new StringBuilder();
        for (int i = 1; i < taskList.getLength()+1; i++) {
            Task currTask = taskList.getTask(i);
            String done = currTask.isDone() ? "1|" : "0|";
            if (currTask instanceof Todo) {
                tasksToSave.append("T|").append(done).append(currTask.getDescription());
            } else if (currTask instanceof Deadline) {
                tasksToSave.append("D|").append(done).append(currTask.getDescription())
                        .append("|").append(((Deadline) currTask).getDeadline());
            } else if (currTask instanceof Event){
                tasksToSave.append("E|").append(done).append(currTask.getDescription())
                        .append("|").append(((Event) currTask).getStartTime());
            }
            tasksToSave.append("\n");
        }
        try {
            FileWriter writer = new FileWriter(this.dirPath + File.separator + this.fileName);
            writer.write(tasksToSave.toString());
            writer.close();
        } catch (IOException e) {
            ui.showSavingError(e.getMessage());
        }
    }
}
