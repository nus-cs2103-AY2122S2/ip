import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
    private LocalDate dlDate;
    private LocalTime dlTime;

    public Deadline(String item, LocalDate dlDate) {
        super(item);
        this.dlDate = dlDate;
    }

    public Deadline(String item, LocalDate dlDate, LocalTime dlTime) {
        super(item);
        this.dlDate = dlDate;
        this.dlTime = dlTime;
    }

    public boolean dateCompare(LocalDate date) {
        return this.dlDate.equals(date);
    }

    @Override
    public String getItemAndStatus() {
        String returned;
        if (dlTime == null) {
            returned = "[D]" + super.getItemAndStatus() + " (by: " + this.dlDate.format(Deadline.dtf)+ ")";
        } else {
            returned = "[D]" + super.getItemAndStatus() + " (by: " + this.dlDate.format(Deadline.dtf) + " " + this.dlTime + ")";
        }

        return returned;
    }
}