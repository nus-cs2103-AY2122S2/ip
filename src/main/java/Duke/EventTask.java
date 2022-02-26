package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EventTask extends Task{
    private LocalDateTime atStartDT;
    private LocalDateTime atEndDT;
    private final static DateTimeFormatter dtFm = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");

    public EventTask(String description, boolean isCompleted,
                     LocalDateTime atStartDT, LocalDateTime atEndDT){
        super(description, isCompleted);
        this.atStartDT = atStartDT;
        this.atEndDT = atEndDT;
    }

    public EventTask(String description, boolean isCompleted,
                     LocalDateTime createdDate, LocalDateTime atStartDT, LocalDateTime atEndDT){
        super(description, isCompleted, createdDate);
        this.atStartDT = atStartDT;
        this.atEndDT = atEndDT;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString() + "(at: " + atStartDT.format(dtFm) +
                " to " + atEndDT.format(dtFm) + ")";
    }

    /**
     * Generates Event string to be stored in the file.
     */
    //E | 0 | project meeting | Aug 6th 2-4pm
    @Override
    public String toFileString(){

        return "E" + super.toFileString() + " | " + atStartDT.format(Parser.dtFormat) + " | " +
                atEndDT.format(Parser.dtFormat);
    }
}
