package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task{

    private LocalDateTime date;

    public Deadline(String content, LocalDateTime date) {
        super(content);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }

    public String toSaveData() {
        return String.format("D|%s|%s\n", super.toSaveData(), this.date);
    }

    public static Deadline createFromData(String savedData) {
        String[] parsedSavedData = savedData.split("\\|");
        Deadline newDeadline = new Deadline(parsedSavedData[2], parsedSavedData[3]);
        if (parsedSavedData[1].equals("1")) {
            newDeadline.markAsDone();
        }
        return newDeadline;
    }
}
