package istjbot.text;

/**
 * Encapsulates a Note that contains info about small snippets of textual information the user wants to record
 */
public class Note {
    /** String description for the note. */
    private String description;

    /**
     * Constructor for Note.
     *
     * @param description String description for the note.
     */
    public Note(String description) {
        this.description = description;
    }

    /**
     * Returns a String with a format for the txt file that is to be saved.
     *
     * @return Txt-file formatted String.
     */
    public String toTxtString() {
        String txtString = "note / " + description;
        return txtString;
    }

    /**
     * Returns a String representation of the note.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return description;
    }
}
