import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    private LocalDateTime atStartDT;
    private LocalDateTime atEndDT;
    private final static DateTimeFormatter dtFm = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");

    public EventTask(String description, boolean isCompleted, LocalDateTime atStartDT, LocalDateTime atEndDT){
        super(description, isCompleted);
        this.atStartDT = atStartDT;
        this.atEndDT = atEndDT;
    }

    public LocalDateTime getStartAtDateTime() {
        return atStartDT;
    }

    public void setStartAtDate(LocalDateTime atDate) {
        this.atStartDT = atDate;
    }

    public LocalDateTime getEndAtDateTime() {
        return atEndDT;
    }

    public void setEndAtDate(LocalDateTime atDate) {
        this.atEndDT = atDate;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString() + "(at: " + atStartDT.format(dtFm) + " to " + atEndDT.format(dtFm) + ")";
    }

    //E | 0 | project meeting | Aug 6th 2-4pm
    @Override
    public String toFileString(){

        return "E" + super.toFileString() + " | " + atStartDT.format(Parser.dtFormat) + " | " +
                atEndDT.format(Parser.dtFormat);
    }
}
