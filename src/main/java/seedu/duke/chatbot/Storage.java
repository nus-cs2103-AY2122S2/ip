package seedu.duke.chatbot;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.LoadingException;
import seedu.duke.exceptions.UnableToUpdateDatabaseException;
import seedu.duke.task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
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
            if (!file.exists()) {
                file.createNewFile();
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
    Task getTaskFromSummary(String taskDetails) throws DukeException {
        //getToDoFromSummary()...
        assert taskDetails.length() != 0 : "Storage getTaskFromSummary - Task details are empty";
        //taskType is the first letter - e.g. "T"
        String taskType = taskDetails.substring(0, 1);
        //start from index 2
        boolean doneStatus = Integer.parseInt(taskDetails.substring(2, 3)) == 1;

        NoteList noteList = this.createNoteListFromSummary(taskDetails);

        String taskName;
        //only tasks with dates have 'date/<datestring>'
        if (taskDetails.contains("date/")) {
            taskName = this.getTaskNameForTasksWithDates(taskDetails);
            if (taskType.equals("E")) {
                //date/2022-02-14 08:20/to2022-02-14 09:50
                LocalDateTime startDate = this.createEventStartDateTimeFromSummary(taskDetails);
                LocalDateTime endDate = this.createEventEndDateTimeFromSummary(taskDetails);
                return new Event(taskName, doneStatus, endDate, startDate, noteList);
            } else {
                LocalDateTime endDate = this.createDateTimeFromSummary(taskDetails);
                return new Deadline(taskName, doneStatus, endDate, noteList);
            }
        } else {
            //create a function getTaskNameForToDo
            //-1 is because <taskName>|notes/
            taskName = taskDetails.substring(4, taskDetails.indexOf("notes/") - 1);
            return new ToDo(taskName, doneStatus, noteList);
        }

    }

    LocalDateTime createEventEndDateTimeFromSummary(String taskDetails) {
        int indexEndDate = taskDetails.indexOf("/to") + 3;
        String endDateString = taskDetails.substring(indexEndDate,taskDetails.indexOf("|notes/"));
        LocalDateTime endDate = this.getLocalDateTimeFromDate(endDateString);
        return endDate;
    }

    LocalDateTime createEventStartDateTimeFromSummary(String taskDetails) throws DukeException {
        int indexOfDate = taskDetails.indexOf("date/");
        String date = taskDetails.substring(indexOfDate + 5, taskDetails.indexOf("/to")); //"date/<datetime>"
        LocalDateTime dateObj = this.getLocalDateTimeFromDate(date);
        return dateObj;
    }

    String getTaskNameForTasksWithDates(String taskDetails) throws DukeException {
        try {
            int indexOfDate = taskDetails.indexOf("date/");
            int indexTaskNameStart = 4; //"T|1|<taskName>|date/",
            String taskName = taskDetails.substring(4, indexOfDate - 1);
            return taskName;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Problem in getTaskNameForTasksWithDates()");
        }
    }

    LocalDateTime createDateTimeFromSummary(String taskDetails) throws DukeException {
        try {
            int indexOfDate = taskDetails.indexOf("date/");
            //-1 because <datetime>|notes/<number of notes> --> got spacing we want to get rid of
            String date = taskDetails.substring(indexOfDate + 5, taskDetails.indexOf("notes/") - 1); //"date/<datetime>"
            LocalDateTime dateObj = this.getLocalDateTimeFromDate(date);
            return dateObj;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Problem in getTaskNameForTasksWithDates()");
        }
    }

    NoteList createNoteListFromSummary(String taskDetails) throws DukeException {
        //add notes notes/<number of notes> <content of note 1>/END/ <content of note 2>/END/ ...
        try {
            int indexOfNoteStart = taskDetails.indexOf("notes/");
            int indexOfNumberOfNotes = indexOfNoteStart + 6; //e.g. notes/5

            assert indexOfNumberOfNotes + 1 < taskDetails.length() : "Index exceeds length in createNoteListFromSummary";

            int numberOfNotes = this.parseForNumberOfNotes(taskDetails, indexOfNumberOfNotes);

            if (numberOfNotes == 0) {
                return new NoteList();
            } else {
                int indexStartOfNoteContent = indexOfNumberOfNotes + 2; //<number of notes>|<content of note 1>
                taskDetails = taskDetails.substring(indexStartOfNoteContent);
                int endIndexOfNoteContent = taskDetails.indexOf("/END/");
                ArrayList<Note> notes = new ArrayList();
                for (int i = 0; i < numberOfNotes; i++) {
                    if (i != 0) {
                        indexStartOfNoteContent = endIndexOfNoteContent + 6; // /END/|<nextNoteContent>/END/
                        taskDetails = taskDetails.substring(indexStartOfNoteContent); //"<<nextNoteContent>/END/..."
                        endIndexOfNoteContent = taskDetails.indexOf("/END/");
                    }
                    String noteContent = taskDetails.
                            substring(0, endIndexOfNoteContent);

                    notes.add(new Note(noteContent));
                }
                return new NoteList(notes);
            }
        } catch(NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Problem with translating summary into notelist");
        }
    }


    /**
     * Creates a summary string for the {@link Task} to be saved in the database.
     * @param task to be converted into a summary string and saved
     * @return string which contains a summarised information about the task to be recreated later
     */
    public String createSummaryFromTask(Task task) {
        String taskType = task.getTaskType();

        String  summary = taskType + "|";

        summary += (task.isDone()) ? "1|" : "0|";

        summary += task.getTaskName() + "|";

        if (taskType.equals("D")) {
            LocalDateTime date = task.getEndDate();
            String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            summary += "date/" + dateString + "|"; //24 Dec 2019 --> 2019-12-24
        }

        if (taskType.equals("E")) {
            LocalDateTime startDate = task.getStartDate();
            String dateString = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            summary += "date/" + dateString + "/to"; //24 Dec 2019 --> 2019-12-24
            LocalDateTime endDate = task.getEndDate();
            String endDateString = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            summary += endDateString + "|";
        }

        summary += task.getNotes().convertToSummary();

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
            //referenced from https://mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
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
        if (!file.delete()) {
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

    int parseForNumberOfNotes(String input, int index) throws DukeException {
        return Integer
                    .parseInt(input.substring(index, index + 1));
    }

}

