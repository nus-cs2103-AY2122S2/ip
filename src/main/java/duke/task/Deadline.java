package duke.task;

import java.util.StringTokenizer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private static final char DEADLINE_SYMBOL = 'D';

    public Deadline() {
        super();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        this.deadlineDate = LocalDate.parse("2020-12-12"); // just get default date
        this.deadlineTime = LocalTime.parse("2359", timeFormatter);
    }

    public Deadline(String taskDescription, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskDescription);

        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String saveFileFormat() {
        return DEADLINE_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|"
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "|"
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + "\n";
    }

    @Override
    public void extractFileData(String data) {
        StringTokenizer st = new StringTokenizer(data, "|");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        st.nextToken(); // remove the type symbol
        isDone = Boolean.parseBoolean(st.nextToken());
        taskDescription = st.nextToken();
        deadlineDate = LocalDate.parse(st.nextToken(), dateFormatter);
        deadlineTime = LocalTime.parse(st.nextToken(), timeFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ")";
    }
}
