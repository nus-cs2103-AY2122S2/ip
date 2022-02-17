package notes;

import exception.DukeException;

import java.util.ArrayList;

public class NoteList {

    private ArrayList<Note> notes;

    public NoteList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    /**
     * Prints all notes in the note list.
     */
    public String displayList() {
        String returnString = "";
        for (int i = 0; i < notes.size(); i++) {
            returnString = returnString + (i + 1) + ". " + notes.get(i).toString() + "\n";
        }
        if (returnString.equals("")) {
            return "Seems like you haven't added any notes to your list yet...";
        } else {
            return returnString;
        }
    }

    /**
     * Adds a new note to the note list.
     * @param stringNoteArray String note that needs to be added to the note list.
     * @throws DukeException when note description is empty.
     */
    public String addToList(String[] stringNoteArray) throws DukeException {
        String note = "";
        for (int i = 2; i < stringNoteArray.length; i++) {
            note = note + stringNoteArray[i] + " ";
        }
        if (note.equals("")) {
            throw new DukeException("Hmm. Note description cannot be empty...");
        } else {
            Note newNote = new Note(note);
            this.notes.add(newNote);
            return "Got it! I've added this note:\n" + note + "\n to your list of notes.";
        }
    }

    /**
     * Deletes all or specific notes from the note list based on string input.
     * @param inputStringsArray string that specifies if a specific note number or all notes to be deleted.
     * @throws DukeException when user has not entered a valid list index.
     */
    public String delete(String[] inputStringsArray) throws DukeException {
        try {
            String number = inputStringsArray[2];
            if (number.equals("all")) {
                this.notes = new ArrayList<>();
                return "All right! I have deleted all tasks in your list.";
            } else {
                int num = Integer.parseInt(number);
                Note note = notes.get(num - 1);
                String tempString = "Noted. I've removed this note:\n" + note.toString()
                        + "\nNow you have " + (this.notes.size() - 1) + " notes left in your note list";
                this.notes.remove(num - 1);
                return tempString;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Try entering a valid index number or 'all' for the notes you want to delete");
        }
    }

    /**
     * Prints notes in the note list that contain a specified keyword.
     * @param inputStringsArray keyword used to filer notes in task list.
     * @throws DukeException when user has not entered a keyword to filter using.
     */
    public String find(String[] inputStringsArray) throws DukeException {
        try {
            ArrayList<Note> foundNotes = new ArrayList<>();
            for (int i = 0; i < this.notes.size(); i++) {
                String details = this.notes.get(i).toString();
                String[] detailsArray = details.split(" ");
                for (int j = 0; j < detailsArray.length; j++) {
                    if (detailsArray[j].equals(inputStringsArray[2])) {
                        foundNotes.add(this.notes.get(i));
                        break;
                    }
                }
            }
            String returnString = "Here are the matching notes in your list:\n";
            for (int i = 0; i < foundNotes.size(); i++) {
                returnString = returnString + (i + 1) + ". " + foundNotes.get(i).toString() + "\n";
            }
            return returnString;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Enter a keyword to filter notes by!");
        }
    }

    /**
     * Getter for notes
     */
    public ArrayList<Note> getNotes() {
        return this.notes;
    }

}
