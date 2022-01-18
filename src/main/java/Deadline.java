public class Deadline extends Task {

    private String date;

    public Deadline(String description, String dline) {
        super(description);
        this.date = dline;
    }

    @Override
    public String toString() {
        return "[D]" + this.getSymbol() + " " + this.getName() + " (by: " + this.date.trim() + ")";
    }

    //Formats a line of text into a Deadline object
    public static Deadline formatInput(String input) throws StringIndexOutOfBoundsException, DukeException {
        String dlTask = input.substring(8); //Grabs all the text after the "deadline" command word
        dlTask = dlTask.trim();
        if (dlTask.equals("")) {
            throw new DukeException("Empty description for Deadline object");
        }
        String dLine = "/by";
        int dlDatePos = dlTask.indexOf(dLine);
        String dlDate = dlTask.substring(dlDatePos + 3); //Grabs all the text after the "/by" key word
        String dlDes = dlTask.substring(0, dlDatePos);
        if (dlDate.trim().equals("") || dlDes.trim().equals("")) {
            throw new DukeException("No valid date/description entered");
        }
        return new Deadline(dlDes.trim(), dlDate);
    }

}
