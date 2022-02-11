package seedu.duke.chatbot;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.LoadingException;
import seedu.duke.exceptions.UnableToUpdateDatabaseException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;

/**
 * Handles updating of database, a txt file, whenever {@link TaskList} is updated.
 */
public class Storage {
    private final String filePath;
    private final String fileName;

    /**
     * Creates a Storage object which is used to update the database as commands are executed.
     * @param filePath specifying the path to the database
     * @param fileName specifying the name of the file
     */
    Storage(String filePath, String fileName) {
        assert filePath.length() > 0 : "Storage Constructor - filePath cannot be null";
        assert fileName.length() > 0 : "Storage Constructor - fileName cannot be null";
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Creates a {@link TaskList} based on what was saved in the database from previous run of Duke.
     * @return {@link TaskList} with all previous tasks added
     * @throws DukeException if database file is not found or unable to create the {@link TaskList}
     */
    TaskList getOldTaskList() throws DukeException {
        System.out.println(Ui.showStartLoading());

        File file = this.getDatabaseFile();

        TaskList oldTaskList = this.createTaskListFromDatabase(file);

        System.out.println(Ui.showWelcome());

        return oldTaskList;
    }

    File getDatabaseFile() throws DukeException {
        File directory = new File(this.filePath);

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(this.fileName);

        try {
            if (file.createNewFile()) {
                System.out.println(Ui.showFileCreated(file));
            }
        } catch (IOException e) {
            throw new DukeException("An error occured. I will restart the list");
        }

        return file;
    }

    TaskList createTaskListFromDatabase(File myObj) throws DukeException {
        try {
            TaskList taskList = new TaskList();
            Scanner sc = new Scanner(myObj);
            StringBuilder sb = new StringBuilder();

            while (sc.hasNext()) {
                String taskDetails = sc.nextLine();
                Task taskToAdd = getTaskFromSummary(taskDetails);
                taskList = taskList.add(taskToAdd);
            }

            sc.close();

            System.out.println(Ui.showLoadingResult(taskList));

            return taskList;
        } catch (FileNotFoundException e) {
            throw new LoadingException();
        }
    }

    /**
     * Creates a task from a summarised string saved in the database.
     * @param taskDetails which is the summarised string of the task
     * @return {@link Task} created based on information in string from database.
     */
    Task getTaskFromSummary(String taskDetails) {
        //taskType is the first letter - e.g. "T"f
        assert taskDetails.length() !=0 : "Storage getTaskFromSummary - Task details are empty";

        String taskType = taskDetails.substring(0,1);
        //start from index 2 to skip space
        boolean doneStatus = Integer.parseInt(taskDetails.substring(2,3)) == 1;

        String taskName;
        //only tasks with dates have '/'
        if (taskDetails.contains("/")) {
            int indexOfSlash = taskDetails.indexOf("/");
            taskName = taskDetails.substring(4,indexOfSlash - 1);
            String date = taskDetails.substring(indexOfSlash + 2); //"/ Sunday"
            LocalDateTime dateObj = this.getLocalDateTimeFromDate(date);
            if (taskType.equals("E")) {
                return new Event(taskName, doneStatus, dateObj);
            } else {
                return new Deadline(taskName, doneStatus, dateObj);
            }
        } else {
            taskName = taskDetails.substring(4);
            return new ToDo(taskName, doneStatus);
        }
    }

    /**
     * Creates a summary string for the {@link Task} to be saved in the database.
     * @param task to be converted into a summary string and saved
     * @return string which contains a summarised information about the task to be recreated later
     */
    public String createSummaryFromTask(Task task) {
        String taskType = task.getTaskType();
        String  summary = taskType + " ";

        summary += (task.isDone()) ? "1 " : "0 ";

        summary += task.getTaskName() + " ";

        if (taskType.equals("E") || taskType.equals("D")) {
            LocalDateTime date = task.getDate();
            String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            summary += "/ " + dateString; //24 Dec 2019 --> 2019-12-24
        }

        return summary + "\n";
    }

    /**
     *Used to add the summary string of a task to be saved into the database.
     * @param lineContent which stores the summary string of the task to be saved
     * @throws DukeException if unable to store the line into the database
     */
    public void addLine(String lineContent) throws DukeException {
        assert lineContent.length() != 0 : "Storage: addLine - Cannot load empty line into database";
        File file = new File(this.fileName);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append(lineContent);
            bw.close();
        } catch (IOException e) {
            throw new UnableToUpdateDatabaseException();
        }
    }

    /**
     * Used to update the database whenever the {@link TaskList} is changed.
     * @param taskList which is the updated {@link TaskList}
     * @throws DukeException when unable to update the database
     */

    public void convertTaskListToFile(TaskList taskList) throws DukeException {
        //rewrite to a new file
        File file = new File(this.fileName);
        if (file.delete()) {
            //need to make Duke talk first
            Ui.showCompleteUpdateOfFile();
        } else {
            throw new UnableToUpdateDatabaseException();
        }
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            String lineToAdd = this.createSummaryFromTask(taskList.getTasks().get(i));
            this.addLine(lineToAdd);
        }
    }

    /**
     * Used to convert a string to a LocalDateTime object.
     * @param string which contains information in "yyyy-MM-dd HH:mm"
     * @return LocalDateTime with the correct date information
     */
    LocalDateTime getLocalDateTimeFromDate(String string) {
        try {
            TemporalAccessor ta = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(string);
            LocalDateTime date = LocalDateTime.from(ta);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Error generating date string");
            return null;
        }
    }
}

