package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime date;

    public Event(String content, LocalDateTime date) {
        super(content);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date.toString().replace("T", " "));
    }

    public String toSaveData() {
        return String.format("E|%s|%s\n", super.toSaveData(), this.date.toString().replace("T", " "));
    }

    private static LocalDateTime parseDateTime(String datetime) {
        DateTimeFormatter datetimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(datetime, datetimePattern);
    }

    public static Event createFromData(String savedData) {
        String[] parsedSavedData = savedData.split("\\|");
        Event newEvent = new Event(parsedSavedData[2], parseDateTime(parsedSavedData[3]));
        if (parsedSavedData[1].equals("1")) {
            newEvent.markAsDone();
        }
        return newEvent;
    }
}
