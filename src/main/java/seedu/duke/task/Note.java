package seedu.duke.task;

/**
 * Attached to a Task via a NoteList
 */
public class Note {
    private final String taskNotes;
    private static String EMPTY_NOTE = "";

    /**
     * Used to create an empty note.
     */
    Note() {
        this.taskNotes = EMPTY_NOTE;
    }

    /**
     * Used to create a note with specified content.
     * @param taskNotes that contains the specified content
     */
    public Note(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    /**
     * Creates a new Note that contains updated content.
     * @param newNoteContent contains new content
     * @return Note object with updated content
     */
    Note editNoteContent(String newNoteContent) {
        return new Note(newNoteContent);
    }

    @Override
    public String toString() {
        return this.taskNotes;
    }

}
