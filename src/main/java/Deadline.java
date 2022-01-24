import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String description, LocalDate dLine) {
        super(description);
        this.date = dLine;
    }

    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + this.getSymbol() + " " + this.getName() + " (by: " + date + ")";
    }

    //Formats a line of text into a Deadline object
    public static Deadline createDeadline(String input) throws StringIndexOutOfBoundsException, DukeException {
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
        return new Deadline(dlDes.trim(), LocalDate.parse(dlDate.trim()));
    }

    public static Deadline createDeadline(int status, String description, String date) {
        Deadline dl = new Deadline(description, LocalDate.parse(date));
        if (status == 1) {
            dl.markTask();
        }
        return dl;
    }

    @Override
    public String formatText() {
        int status = (this.getStatus()) ? 1 : 0;
        return "D|" + status + "|" + this.getName() + "/" + this.date.toString();
    }
}
