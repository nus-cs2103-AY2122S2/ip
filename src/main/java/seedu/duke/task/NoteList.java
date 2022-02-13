package seedu.duke.task;

import java.util.ArrayList;

//can have a generic class that handles list like objects

public class NoteList {
    private final ArrayList<Note> notes;

    public NoteList() {
        this.notes = new ArrayList<>();
    }

    public NoteList(ArrayList notes) {
        this.notes = notes;
    }

    NoteList editNote(int indexOfNote, String newNoteContent) {
         Note noteToEdit = this.notes.get(indexOfNote);
         Note editedNote = noteToEdit.editNoteContent(newNoteContent);
         return this.replace(indexOfNote, editedNote);
    }

    NoteList addNote(Note newNote) {
        ArrayList<Note> newNotes = this.copyNoteList();
        newNotes.add(newNote);
        return new NoteList(newNotes);
    }

    NoteList deleteNote(int indexOfNote) {
        ArrayList<Note> newNotes = this.copyNoteList();
        newNotes.remove(indexOfNote);
        return new NoteList(newNotes);
    }

    ArrayList<Note> copyNoteList() {
        return new ArrayList<>(this.notes);
    }

    NoteList replace(int noteIndex, Note editedNote) {
        ArrayList<Note> newNotes = this.copyNoteList();
        newNotes.set(noteIndex,editedNote);
        return new NoteList(newNotes);
    }

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
