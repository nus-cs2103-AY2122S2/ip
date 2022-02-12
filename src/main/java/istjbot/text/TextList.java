package istjbot.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import istjbot.command.CommandEnum;
import istjbot.exception.BotException;

/**
 * Encapsulates a list of texts (tasks and notes) that are stored in each ArrayList.
 * Supports different modifying methods (such as add, delete, search, and mark/unmark) for tasks,
 * and simplified methods (such as view, add, and delete) for notes.
 */
public class TextList {
    private static final int NOTE_OR_TASK_TYPE = 0;
    private static final int NOTE_DESCRIPTION = 1;
    private static final int TASK_MARKED_OR_NOT = 1;
    private static final int TASK_DESCRIPTION = 2;
    private static final int TASK_DATE = 3;

    /** ArrayList of tasks stored. */
    private final ArrayList<Task> tasks = new ArrayList<>();
    /** ArrayList of notes stored. */
    private final ArrayList<Note> notes = new ArrayList<>();

    /**
     * Constructor for this TaskList.
     */
    public TextList() {}

    /**
     * Constructor for this TaskList.
     * Processes the existing txt file and add the tasks to tasks ArrayList.
     *
     * @param file A file with a specified path that this TaskList will save to and load from.
     * @throws BotException When the command listed in the file is incorrect.
     * @throws FileNotFoundException When the file with that specified path is not found.
     */
    public TextList(File file) throws BotException, FileNotFoundException {
        // The file should be new or not tampered with by a user
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            // Can be abstracted out, but did not since it is a constructor method
            String[] textInfo = line.split(" / ");

            Task taskAdded;
            boolean isMarked;

            Note noteAdded;

            CommandEnum commandEnum = CommandEnum.stringToCommandEnum(textInfo[NOTE_OR_TASK_TYPE]);

            switch (commandEnum) {
            case TODO:
                taskAdded = new Todo(textInfo[TASK_DESCRIPTION]);
                this.tasks.add(taskAdded);
                isMarked = Integer.parseInt(textInfo[TASK_MARKED_OR_NOT]) == 1;
                if (isMarked) {
                    taskAdded.mark();
                }
                break;

            case DEADLINE:
                taskAdded = new Deadline(textInfo[TASK_DESCRIPTION], textInfo[TASK_DATE]);
                this.tasks.add(taskAdded);
                isMarked = Integer.parseInt(textInfo[TASK_MARKED_OR_NOT]) == 1;
                if (isMarked) {
                    taskAdded.mark();
                }
                break;

            case EVENT:
                taskAdded = new Event(textInfo[TASK_DESCRIPTION], textInfo[TASK_DATE]);
                this.tasks.add(taskAdded);
                isMarked = Integer.parseInt(textInfo[TASK_MARKED_OR_NOT]) == 1;
                if (isMarked) {
                    taskAdded.mark();
                }
                break;

            case NOTE:
                noteAdded = new Note(textInfo[NOTE_DESCRIPTION]);
                this.notes.add(noteAdded);
                break;

            default:
            }
        }
    }

    /**
     * Adds the task to tasks ArrayList.
     *
     * @param commandEnum Type of the task.
     * @param description Description for the task.
     * @param modifierDate Date set for the task.
     * @throws DateTimeParseException When the modifierDate contains a wrong date format.
     */
    public void addTask(CommandEnum commandEnum, String description, String modifierDate) throws
            DateTimeParseException {
        assert description != "" : "task description shouldn't be empty";

        switch (commandEnum) {
        case TODO:
            this.tasks.add(new Todo(description));
            break;

        case DEADLINE:
            this.tasks.add(new Deadline(description, modifierDate));
            break;

        case EVENT:
            this.tasks.add(new Event(description, modifierDate));
            break;

        default:
        }
    }

    /**
     * Adds the note to notes ArrayList.
     *
     * @param description Description for the note.
     */
    public void addNote(String description) {
        assert description != "" : "note description shouldn't be empty";

        this.notes.add(new Note(description));
    }

    /**
     * Returns the number of tasks stored in tasks ArrayList.
     *
     * @return The number of tasks stored.
     */
    public int taskListSize() {
        return this.tasks.size();
    }

    public int noteListSize() {
        return this.notes.size();
    }

    /**
     * Returns a String representation of the task with respective taskNumber.
     *
     * @param taskNumber Number (order) of the task that appear in tasks ArrayList. (1-based)
     * @return String representation of the given task.
     */
    public String taskString(int taskNumber) {
        assert taskNumber >= 1 || taskNumber <= taskListSize() : "taskNumber should be within the range";
        return this.tasks.get(taskNumber - 1).toString();
    }

    /**
     * Returns a String representation of the note with respective noteNumber.
     *
     * @param noteNumber Number (order) of the note that appear in notes ArrayList. (1-based)
     * @return String representation of the given note.
     */
    public String noteString(int noteNumber) {
        assert noteNumber >= 1 || noteNumber <= noteListSize() : "noteNumber should be within the range";
        return this.notes.get(noteNumber - 1).toString();
    }

    /**
     * Returns a String representation of all texts (tasks and notes) with a txt file format.
     *
     * @return a String (txt-file based) representation of all texts.
     */
    public String textsToTxtString() {
        StringBuilder str = new StringBuilder();
        addTxtTextsToStringBuilder(str);
        return str.toString();
    }

    private void addTxtTextsToStringBuilder(StringBuilder str) {
        for (Task task : tasks) {
            if (str.length() == 0) {
                str.append(task.toTxtString());
            } else {
                str.append("\n" + task.toTxtString());
            }
        }
        for (Note note : notes) {
            if (str.length() == 0) {
                str.append(note.toTxtString());
            } else {
                str.append("\n" + note.toTxtString());
            }
        }
    }

    /**
     * Returns a String representation of all tasks for display.
     *
     * @return a String (display based) representation of all tasks.
     */
    public String tasksToString() {
        StringBuilder str = new StringBuilder();
        addTasksToStringBuilder(str);
        return str.toString();
    }

    private void addTasksToStringBuilder(StringBuilder str) {
        int count = 1;
        for (Task task : tasks) {
            if (str.length() == 0) {
                str.append(count + ". " + task.toString());
            } else {
                str.append("\n" + count + ". " + task.toString());
            }
            count++;
        }
    }

    /**
     * Returns a String representation of all notes for display.
     *
     * @return a String (display based) representation of all notes.
     */
    public String notesToString() {
        StringBuilder str = new StringBuilder();
        addNotesToStringBuilder(str);
        return str.toString();
    }

    private void addNotesToStringBuilder(StringBuilder str) {
        int count = 1;
        for (Note note : notes) {
            if (str.length() == 0) {
                str.append(count + ". " + note.toString());
            } else {
                str.append("\n" + count + ". " + note.toString());
            }
            count++;
        }
    }

    /**
     * Marks a task with given taskNumber to be done.
     *
     * @param taskNumber Number (order) of the task that appear in tasks ArrayList. (1-based)
     */
    public void markTask(int taskNumber) {
        this.tasks.get(taskNumber - 1).mark();
    }

    /**
     * Un-marks a task with given taskNumber to be not done.
     *
     * @param taskNumber Number (order) of the task that appear in tasks ArrayList. (1-based)
     */
    public void unmarkTask(int taskNumber) {
        this.tasks.get(taskNumber - 1).unmark();
    }

    /**
     * Deletes a task with given taskNumber and
     * returns a String representation of that task (for display).
     *
     * @param taskNumber Number (order) of the task that appear in tasks ArrayList. (1-based)
     * @return String representation of the task that is deleted.
     */
    public String deletedTaskString(int taskNumber) {
        Task deletedTask = this.tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return deletedTask.toString();
    }

    /**
     * Deletes a note with given noteNumber and
     * returns a String representation of that note (for display).
     *
     * @param noteNumber Number (order) of the note that appear in notes ArrayList. (1-based)
     * @return String representation of the note that is deleted.
     */
    public String deletedNoteString(int noteNumber) {
        Note deletedNote = this.notes.get(noteNumber - 1);
        notes.remove(noteNumber - 1);
        return deletedNote.toString();
    }

    /**
     * Searches for tasks with given date and returns them
     * as a String representation (for display purpose).
     *
     * @param dateString String of date that is to be parsed as LocalDate object.
     * @return String presentation of all tasks that have the same date as given input.
     * @throws DateTimeParseException When the format of dateString is incorrect.
     */
    public String searchByDateString(String dateString) throws DateTimeParseException {
        LocalDate dateGiven = LocalDate.parse(dateString);
        StringBuilder searchList = new StringBuilder();
        addDateSearchToStringBuilder(searchList, dateGiven);
        return searchList.toString();
    }

    private void addDateSearchToStringBuilder(StringBuilder searchList, LocalDate dateGiven) {
        int[] count = new int[]{1};
        // Arrow-head identified
        for (Task task : tasks) {
            task.getDate().ifPresent(date -> {
                if (date.isEqual(dateGiven)) {
                    if (searchList.length() == 0) {
                        searchList.append(count[0] + ". " + task);
                    } else {
                        searchList.append("\n" + count[0] + ". " + task);
                    }
                    count[0]++;
                }
            });
        }
    }

    /**
     * Searches for tasks with given keyword and returns them
     * as a String representation (for display purpose).
     *
     * @param keyword A String that represents the keyword user is searching for.
     * @return String presentation of all tasks that contain the given keyword.
     */
    public String searchByKeywordString(String keyword) {
        StringBuilder searchList = new StringBuilder();
        addSearchToStringBuilder(searchList, keyword);
        return searchList.toString();
    }

    private void addSearchToStringBuilder(StringBuilder searchList, String keyword) {
        int count = 1;
        // Arrow-head identified
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                if (searchList.length() == 0) {
                    searchList.append(count + ". " + task);
                } else {
                    searchList.append("\n" + count + ". " + task);
                }
                count++;
            }
        }
    }
}
