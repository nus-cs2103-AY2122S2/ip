package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime endTime;

    public Deadline(String description, String end) {
        super(description, Type.DEADLINE);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime endDate = LocalDateTime.parse(end, format);
        this.endTime = endDate;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getEndTimeString(String formatString) {
        return getEndTime().format(DateTimeFormatter.ofPattern(formatString));
    }

    public String getEndTimeString() {
        return getEndTimeString("dd/MM/yyyy HHmm");
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by: "
                + getEndTimeString()
                + ")";
    }
}
>>>>>>> 3b5f87c667a37235d9c6c86386e211d503709ebd
