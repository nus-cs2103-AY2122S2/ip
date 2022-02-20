package duke;

import java.util.ArrayList;

public class NoteList {

    private static final String NOTE_HEADER = "Here are your notes: \n";
    private static final String NOTE_EMPTY_MESSAGE = "Seems like you don't have any notes yet\n\n" +
            "Use command \"note [keyword]: [description]\" to add new notes";

    private final ArrayList<Note> notes;

    NoteList() {
        notes = new ArrayList<>();
    }

    NoteList(ArrayList<Note> notes) {
        this.notes = new ArrayList<>(notes);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public String add(Note note) {
        notes.add(note);

        return "BMO added this to your notes: \n    " + note;
    }

    public String delete(String key) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getLabel().equals(key)) {
                return "Deleted this note: \n    " + notes.remove(i);
            }
        }

        return "Cannot find note with same key to delete.";
    }

    public String makeNoteList() {
        StringBuilder bld = new StringBuilder();
        for (Note n : notes) {
            bld.append("    ");
            bld.append(n);
            bld.append("\n");
        }

        return bld.toString();
    }

    public String checkNotes() {
        if (notes.isEmpty()) {
            return NOTE_EMPTY_MESSAGE;
        }
        return NOTE_HEADER + makeNoteList();
    }
}
