package yale.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoWithinPeriod extends Task {
    /**
     * String to represent the starting date of the period.
     */
    protected LocalDate startPeriod;

    /**
     * String to represent the ending date of the period.
     */
    protected LocalDate endPeriod;

    /**
     * Constructor method.
     *
     * @param name     Name of task.
     * @param isMarked Boolean of whether task is marked.
     */
    public DoWithinPeriod(String name, boolean isMarked, String startPeriod, String endPeriod) {
        super(name, isMarked);
        this.startPeriod = LocalDate.parse(startPeriod);
        this.endPeriod = LocalDate.parse(endPeriod);
    }

    /**
     * Returns a customised String format of
     * the DoWithinPeriod object.
     * @return Custom String format of DoWithinPeriod object.
     */
    @Override
    public String export() {
        assert this.startPeriod != null : "No start date!";
        assert this.endPeriod != null : "No end date!";
        return "DW " + "| " + (isMarked ? 1 : 0) + " | " + this.getName()
                + " | " + this.startPeriod + " | " + this.endPeriod;
    }

    /**
     * Returns a customised String.
     * @return Customised String format.
     */
    @Override
    public String toString() {
        return "[DW]" + super.toString()
                + "\n (between: "
                + this.startPeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " and " + this.endPeriod.format(DateTimeFormatter.ofPattern("MMM d yyy"))
                + ")";
    }
}
