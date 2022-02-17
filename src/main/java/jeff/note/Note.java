package jeff.note;

import jeff.ui.Ui;

/**
 * Note class stores all the user entered notes in a StringBuilder object.
 */
public class Note {
    private StringBuilder notes;

    /**
     * Constructor for Note class.
     */
    public Note() {
        this.notes = new StringBuilder();
    }

    /**
     * Appends a given string onto the StringBuilder object.
     *
     * @param str string to be added.
     */
    public void add(String str) {
        String newEntry = Ui.addPrefix(str) + "\n";
        notes.append(newEntry);
    }

    /**
     * Delete all entries in the note.
     */
    public void clear() {
        notes.setLength(0);
    }

    /**
     * Checks if the note is empty.
     *
     * @return if the StringBuilder is empty
     */
    public boolean isEmpty() {
        return notes.length() == 0;
    }

    public String toString() {
        return notes.toString();
    }
}
