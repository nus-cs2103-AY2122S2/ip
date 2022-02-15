package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Used to create task that is added to {@link TaskList}.
 */
public abstract class Task {
    /**
     * taskName refers to the name of the task.
     */
    private final String taskName;
    /**
     * done records if a task has been marked as complete.
     */
    private final boolean isDone;
    private final LocalDateTime endDate;
    private final LocalDateTime startDate;
    private final NoteList notes;

    Task(String name, boolean doneStatus, LocalDateTime endDate, LocalDateTime startDate, NoteList notes) {
        this.taskName = name;
        this.isDone = doneStatus;
        this.endDate = endDate;
        this.startDate = startDate;
        this.notes = notes;
    }

    /**
     * Constructor used when editing note.
     */
    Task(Task oldTask, NoteList newNoteList) {
        this.taskName = oldTask.getTaskName();
        this.isDone = oldTask.isDone();
        this.endDate = oldTask.getEndDate();
        this.startDate = oldTask.getStartDate();
        this.notes = newNoteList;
    }

    /**
     * Checks whether the task has been completed.
     * @return boolean of this done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task name of a task.
     * @return String containing task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Marks task as done.or undone.
     * @return new Task with done attribute as the boolean parameter
     */
    public abstract Task changeTaskStatus(boolean isDone);

    public abstract String getTaskType();

    /**
     * Returns the end date of a Deadline or Event task.
     * @return LocalDateTime representing the end date
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Returns the start date for Event task.
     * @return LocalDateTime representing start date.
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Returns time in 12 hour format.
     * @param date which contain the time in 24hr format
     * @return a string with the date and time in 12hr format
     */
    public String getFormattingDateString(LocalDateTime date) {
        try {
            String indicator = (date.getHour() >= 12) ? "pm" : "am";
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm ")) + indicator;
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * Returns the NoteList of a Task.
     * @return NoteList
     */
    public NoteList getNotes() {
        return this.notes;
    }

    public String getNoteResult() {
        return this.notes.toString();
    }

    /**
     * Edits the note in the NoteList.
     * @param noteContent contains the new note content to update
     * @return an updated Task
     */
    public abstract Task editNoteList(int indexOfNote, String noteContent);

    /**
     * Adds a note to the NoteList.
     * @param newNote contains the new Note to add
     * @return an updated Task
     */
    public abstract Task addNoteToNoteList(Note newNote);

    /**
     * Deletes a note from the NoteList.
     * @param indexOfNote that contains index of the note to delete in NoteList
     * @return an updated Task
     */
    public abstract Task deleteNoteFromNoteList(int indexOfNote);

    @Override
    public String toString() {
        String marked = this.isDone() ? "X" : " ";
        return String.format("[%s] %s", marked, this.taskName);
    }
}
