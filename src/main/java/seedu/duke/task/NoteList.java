package seedu.duke.task;

import java.util.ArrayList;

/**
 * Used to store the Note object for a Task.
 */
public class NoteList {
    private final ArrayList<Note> notes;

    public NoteList() {
        this.notes = new ArrayList<>();
    }

    public NoteList(ArrayList notes) {
        this.notes = notes;
    }

    /**
     * Used to edit the content of a note in the list.
     * @param indexOfNote which is the index of the note to edit
     * @param newNoteContent which is the new content to replace with
     * @return a updated NoteList
     */
    NoteList editNote(int indexOfNote, String newNoteContent) {
         Note noteToEdit = this.notes.get(indexOfNote);
         Note editedNote = noteToEdit.editNoteContent(newNoteContent);
         return this.replace(indexOfNote, editedNote);
    }

    /**
     * Used to add a note to the list.
     * @param newNote which is the Note to add
     * @return a updated NoteList
     */
    NoteList addNote(Note newNote) {
        ArrayList<Note> newNotes = this.copyNoteList();
        newNotes.add(newNote);
        return new NoteList(newNotes);
    }

    /**
     * Used to delete a Note from the NoteList.
     * @param indexOfNote which is the index of the note to be deleted.
     * @return an updated NoteList
     */
    NoteList deleteNote(int indexOfNote) {
        ArrayList<Note> newNotes = this.copyNoteList();
        newNotes.remove(indexOfNote);
        return new NoteList(newNotes);
    }

    /**
     * Makes a copy of the NoteList for immutability.
     * @return a copy of the NoteList
     */
    ArrayList<Note> copyNoteList() {
        return new ArrayList<>(this.notes);
    }

    /**
     * Replaces a note at a specified position with an updated Note.
     * @param noteIndex which is the index of the position to add the edited Note in
     * @param editedNote which is the updated note
     * @return updated NoteList
     */
    NoteList replace(int noteIndex, Note editedNote) {
        ArrayList<Note> newNotes = this.copyNoteList();
        newNotes.set(noteIndex,editedNote);
        return new NoteList(newNotes);
    }

    /**
     * Used to update database.
     * @return String that contains information needed to reconstruct NoteList
     */
    public String convertToSummary() {
        String result = String.format("notes/%d|",notes.size());
        for (int i = 0; i < this.notes.size(); i++) {
            result += this.notes.get(i).toString() + "/END/|";
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.notes.size(); i++) {
            result += String.format("Note %d: ", i+1) + this.notes.get(i).toString() + "\n";
        }
        return result;
    }

}
