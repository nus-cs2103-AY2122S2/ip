package seedu.duke.chatbot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.LoadingException;
import seedu.duke.exceptions.UnableToUpdateDatabaseException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Note;
import seedu.duke.task.NoteList;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

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
        return oldTaskList;
    }

    //@@author {isabelteo}-reused
    // {Code was referenced from Dominic Lim @domlimm to understand how to use relative path}
    /**
     * Creates a file to store task list or retrieves the file if it has been created.
     * @return File that will contain the task list
     * @throws DukeException to signal that there has been a problem updating
     */
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
    //@@author

    /**
     * Recreates the task list based on information in the database file.
     * @param myObj which contains information on the task list
     * @return recreated TaskList
     * @throws DukeException if there are problems retrieving the file
     */
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
     * @return {@link Task} created based on information in string from database
     */
    Task getTaskFromSummary(String taskDetails) throws DukeException {
        assert taskDetails.length() != 0 : "Storage getTaskFromSummary - Task details are empty";
        //taskType is the first letter - e.g. "T"
        String taskType = taskDetails.substring(0, 1);
        boolean doneStatus = Integer.parseInt(taskDetails.substring(2, 3)) == 1;
        NoteList noteList = this.createNoteListFromSummary(taskDetails);

        if (taskType.equals("T")) {
            return this.createToDoFromSummary(taskDetails, doneStatus, noteList);
        }
        String taskName = this.getTaskNameForTasksWithDates(taskDetails);
        if (taskType.equals("E")) {
            return this.createEventFromSummary(taskDetails, taskName, doneStatus, noteList);
        } else {
            return this.createDeadlineFromSummary(taskDetails, taskName, doneStatus, noteList);
        }
    }

    /**
     * Creates a Deadline object from information in database.
     * @param taskDetails which is the line from database
     * @param taskName for name of task
     * @param doneStatus for status of task
     * @param noteList for notes attached to task
     * @return Task or specifically a Deadline object
     * @throws DukeException if there is trouble generating the dates to put into Deadline object
     */
    Task createDeadlineFromSummary(
            String taskDetails, String taskName, boolean doneStatus, NoteList noteList) throws DukeException {
        LocalDateTime endDate = this.createDateTimeFromSummary(taskDetails);
        return new Deadline(taskName, doneStatus, endDate, noteList);
    }

    /**
     * Creates a Event object from information in database.
     * @param taskDetails which is the line from database
     * @param taskName for name of task
     * @param doneStatus for status of task
     * @param noteList for notes attached to task
     * @return Task or specifically a Event object
     * @throws DukeException if there is trouble generating the dates to put into Event object
     */
    Task createEventFromSummary(
            String taskDetails, String taskName, boolean doneStatus, NoteList noteList) throws DukeException {
        //date/2022-02-14 08:20/to2022-02-14 09:50
        LocalDateTime startDate = this.createEventStartDateTimeFromSummary(taskDetails);
        LocalDateTime endDate = this.createEventEndDateTimeFromSummary(taskDetails);
        return new Event(taskName, doneStatus, endDate, startDate, noteList);
    }

    /**
     * Creates a ToDo object from information in database.
     * @param taskDetails which is the line from database
     * @param doneStatus for status of task
     * @param noteList for notes attached to task
     * @return Task or specifically a ToDo object
     */
    Task createToDoFromSummary(String taskDetails, boolean doneStatus, NoteList noteList) {
        // "<taskName>|notes/" in command
        String taskName = taskDetails.substring(4, taskDetails.indexOf("notes/") - 1);
        return new ToDo(taskName, doneStatus, noteList);
    }

    /**
     * Creates a LocalDateTime representing the end date and time for an Event from database.
     * @param taskDetails which is the information from database on the task
     * @return LocalDateTime representing the end date and time for an Event
     */
    LocalDateTime createEventEndDateTimeFromSummary(String taskDetails) throws DukeException {
        int indexEndDate = taskDetails.indexOf("/to") + 3; // /to<endDate> in command
        String endDateString = taskDetails.substring(indexEndDate, taskDetails.indexOf("|notes/"));
        LocalDateTime endDate = this.getLocalDateTimeFromDate(endDateString);
        return endDate;
    }

    /**
     * Creates a LocalDateTime representing the start date and time for an Event from database.
     * @param taskDetails which is the information from database on the task
     * @return LocalDateTime representing the start date and time for an Event
     */
    LocalDateTime createEventStartDateTimeFromSummary(String taskDetails) throws DukeException {
        int indexOfDate = taskDetails.indexOf("date/");
        //"date/<datetime>/to"
        String date = taskDetails.substring(indexOfDate + 5, taskDetails.indexOf("/to"));
        LocalDateTime dateObj = this.getLocalDateTimeFromDate(date);
        return dateObj;
    }

    /**
     * Gets the task name for creating Event and Deadline objects from database information.
     * @param taskDetails which is the information from database on the task
     * @return task name in String
     */
    String getTaskNameForTasksWithDates(String taskDetails) throws DukeException {
        try {
            int indexOfDate = taskDetails.indexOf("date/");
            int indexTaskNameStart = 4; //"T|1|<taskName>|date/",
            String taskName = taskDetails.substring(4, indexOfDate - 1);
            return taskName;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Problem in getting task name for deadline or event :(");
        }
    }

    /**
     * Creates a LocalDateTime for end date in Deadline and start date in Event.
     * @param taskDetails containing information from database
     * @return LocalDateTime representing end date in Deadline and start date in Event
     * @throws DukeException if there is problem getting date from database
     */
    LocalDateTime createDateTimeFromSummary(String taskDetails) throws DukeException {
        try {
            int indexOfDate = taskDetails.indexOf("date/");
            //-1 because <datetime>|notes/<number of notes> --> got spacing we want to get rid of
            String date = taskDetails.substring(indexOfDate + 5, taskDetails.indexOf("notes/") - 1);
            //"date/<datetime>"
            LocalDateTime dateObj = this.getLocalDateTimeFromDate(date);
            return dateObj;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Problem in getting date from database :(");
        }
    }

    /**
     * Used to convert a string to a LocalDateTime object.
     * @param string which contains information in "yyyy-MM-dd HH:mm"
     * @return LocalDateTime with the correct date information
     */
    LocalDateTime getLocalDateTimeFromDate(String string) throws DukeException {
        try {
            TemporalAccessor ta = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(string);
            LocalDateTime date = LocalDateTime.from(ta);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("Error generating date string");
        }
    }

    /**
     * Creates note list based on information from database.
     * @param taskDetails which contains information from database.
     * @return NoteList for a Task stored in database
     * @throws DukeException if there are errors in the information stored
     */
    NoteList createNoteListFromSummary(String taskDetails) throws DukeException {
        //notes/<number of notes>|<content of note 1>/END/|<content of note 2>/END/ ...
        try {
            int indexOfNoteStart = taskDetails.indexOf("notes/");
            int indexOfNumberOfNotes = indexOfNoteStart + 6; //e.g. notes/5
            assert indexOfNumberOfNotes + 1 < taskDetails.length()
                    : "Index exceeds length in createNoteListFromSummary";
            int numberOfNotes = this.parseForNumberOfNotes(taskDetails, indexOfNumberOfNotes);

            if (numberOfNotes == 0) {
                return new NoteList();
            } else {
                return this.recreateNoteList(taskDetails, indexOfNumberOfNotes, numberOfNotes);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Problem with translating summary into note list");
        }
    }

    /**
     * Used to recreate the NoteList.
     * @param taskDetails which contains information from database
     * @param indexOfNumberOfNotes which contains index where note number is stored
     * @param numberOfNotes which is the number of notes attached to the task
     * @return NoteList for a Task
     */
    NoteList recreateNoteList(String taskDetails, int indexOfNumberOfNotes, int numberOfNotes) {
        int indexStartOfNoteContent = indexOfNumberOfNotes + 2; //<number of notes>|<content of note 1>
        taskDetails = taskDetails.substring(indexStartOfNoteContent); //<content of note 1>/END/|...
        int endIndexOfNoteContent = taskDetails.indexOf("/END/");
        ArrayList<Note> notes = new ArrayList();

        for (int i = 0; i < numberOfNotes; i++) {
            if (i != 0) {
                indexStartOfNoteContent = endIndexOfNoteContent + 6; // /END/|<nextNoteContent>/END/
                taskDetails = taskDetails.substring(indexStartOfNoteContent); //"<<nextNoteContent>/END/..."
                endIndexOfNoteContent = taskDetails.indexOf("/END/");
            }

            String noteContent = taskDetails.substring(0, endIndexOfNoteContent);

            notes.add(new Note(noteContent));
        }
        return new NoteList(notes);
    }


    int parseForNumberOfNotes(String input, int index) throws DukeException {
        return Integer
                .parseInt(input.substring(index, index + 1));
    }

    /**
     * Creates a summary string for the {@link Task} to be saved in the database.
     * @param task to be converted into a summary string and saved
     * @return string which contains a summarised information about the task to be recreated later
     */
    public String createSummaryFromTask(Task task) {
        String taskType = task.getTaskType();
        String summary = taskType + "|";
        summary += (task.isDone()) ? "1|" : "0|";
        summary += task.getTaskName() + "|";

        if (taskType.equals("D")) {
            summary = this.createSummaryFromDeadline(task, summary);
        } else if (taskType.equals("E")) {
            summary = this.createSummaryFromEvent(task, summary);
        }

        summary += task.getNotes().convertToSummary();
        return summary + "\n";
    }

    /**
     * Creates a summary to store into database for the Deadline object.
     * @param task which is the Deadline object
     * @param summary which is the summary to store into the database
     * @return String which is stored into database
     */
    String createSummaryFromDeadline(Task task, String summary) {
        LocalDateTime date = task.getEndDate();
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        summary += "date/" + dateString + "|"; //24 Dec 2019 --> 2019-12-24
        return summary;
    }

    /**
     * Creates a summary to store into database for the Event object.
     * @param task which is the Event object
     * @param summary which is the summary to store into the database
     * @return String which is stored into database
     */
    String createSummaryFromEvent(Task task, String summary) {
        LocalDateTime startDate = task.getStartDate();
        String dateString = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        summary += "date/" + dateString + "/to"; //24 Dec 2019 --> 2019-12-24

        LocalDateTime endDate = task.getEndDate();
        String endDateString = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        summary += endDateString + "|";
        return summary;
    }

    //@@author isabelteo - reused
    //reused from https://mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
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
    //@@author

    /**
     * Used to update the database after commands on task list.
     * @param taskList which contains the updated task list
     * @throws DukeException if there is trouble updating the database
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

}

