package notes;

import java.util.ArrayList;

public class NoteList {

    public ArrayList<Note> notes;

    public NoteList() {
        this.notes = new ArrayList<>();
    }

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
     */
    public String addToList(String[] stringNoteArray) {
        String note = "";
        for (int i = 2; i < stringNoteArray.length; i++) {
            note = note + stringNoteArray[i] + " ";
        }
        Note newNote = new Note(note);
        this.notes.add(newNote);
        return "Got it!! :D I've added this note:\n" + note + "\n to your list of notes.";
    }

    /**
     * Deletes all or specific notes from the note list based on string input.
     * @param number string that specifies if a specific note number or all notes to be deleted.
     */
    public String delete(String number) {
        if (number.equals("all")) {
            this.notes = new ArrayList<>();
            return "All right! I have deleted all tasks in your list.";
        } else {
            int num = Integer.parseInt(number);
            Note note = notes.get(num - 1);
            String tempString = "Noted. I've removed this note:\n" + note.toString() +
                    "\nNow you have " + (this.notes.size() - 1) + " notes left in your note list";
            this.notes.remove(num - 1);
            return tempString;
        }
    }

    /**
     * Prints notes in the note list that contain a specified keyword.
     * @param inputStringsArray keyword used to filer notes in task list.
     */
    public String find(String inputStringsArray) {
        ArrayList<Note> foundNotes = new ArrayList<>();
        for (int i = 0; i < this.notes.size(); i++) {
            String details = this.notes.get(i).toString();
            String[] detailsArray = details.split(" ");
            for (int j = 0; j < detailsArray.length; j++) {
                if (detailsArray[j].equals(inputStringsArray)) {
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
    }

}
