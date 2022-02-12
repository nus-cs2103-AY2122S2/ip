package seedu.duke.task;

public class Note {
    private final String taskNotes;
    private static String EMPTY_NOTE = "";

    Note() {
        this.taskNotes = EMPTY_NOTE;
    }

    public Note(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    Note editNoteContent(String newNoteContent) {
        return new Note(newNoteContent);
    }

    public String toString() {
        return this.taskNotes;
    }

}
