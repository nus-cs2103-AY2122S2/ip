package duke.task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String task, LocalDateTime by) {
        super(TaskType.DEADLINE, task);
        this.by = by;
    }

    protected Deadline() {
        this("", null);
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public Optional<LocalDateTime> getDate() {
        return Optional.of(this.by);
    }

    @Override
    public String getReadableString() {
        return String.format("%s (by: %s)", super.getReadableString(),
                this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy '-' hh:mm a")));
    }

    @Override
    public void serialize(DataOutputStream dOut) throws IOException {
        super.serialize(dOut);
        dOut.writeUTF(by.toString());
    }

    @Override
    protected Task readSerializedData(DataInputStream dIn) throws IOException {
        super.readSerializedData(dIn);
        this.by = LocalDateTime.parse(dIn.readUTF());
        return this;
    }
}
