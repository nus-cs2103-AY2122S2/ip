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

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Checks whether the task has been completed.
     * @return boolean of this done.
     */
    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public String getFormattingDateString(LocalDateTime date) {
        try {
            String indicator = (date.getHour() >= 12) ? "pm" : "am";
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm ")) + indicator;
        } catch (NullPointerException e) {
            return "";
        }
    }

    public NoteList getNotes() {
        return this.notes;
    }

    public String getNoteResult() {
        return this.notes.toString();
    }

    /**
     * Edits the note in each task.
     * @param noteContent contains the new note content to update
     * @return an updated Task
     */
    public abstract Task editNoteList(int indexOfNote, String noteContent);

    public abstract Task addNoteToNoteList(Note newNote);

    public abstract Task deleteNoteFromNoteList(int indexOfNote);
    /**
     * Marks task as done.or undone.
     * @return new Task with done attribute as the boolean parameter
     */
    public abstract Task changeTaskStatus(boolean isDone);

    public abstract String getTaskType();

    /**
     * prints task.
     * @return String with task type, status and name
     */
    @Override
    public String toString() {
        String marked = this.isDone() ? "X" : " ";
        return String.format("[%s] %s", marked, this.taskName);
    }
}
