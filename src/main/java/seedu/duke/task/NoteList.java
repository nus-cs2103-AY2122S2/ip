package seedu.duke.task;

import java.util.ArrayList;

//can have a generic class that handles list like objects

public class NoteList {
    private final ArrayList<Note> notes;

    NoteList() {
        this.notes = new ArrayList<>();
    }

    NoteList(ArrayList notes) {
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

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.notes.size(); i++) {
            result += String.format("Note %d: ", i+1) + this.notes.get(i).toString() + "\n";
        }
        return result;
    }

}
