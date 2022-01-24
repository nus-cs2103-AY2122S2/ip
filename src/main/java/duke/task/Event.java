package duke.task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String task, LocalDateTime at) {
        super(TaskType.EVENT, task);
        this.at = at;
    }

    protected Event() {
        this("", null);
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    public Optional<LocalDateTime> getDate() {
        return Optional.of(this.at);
    }

    @Override
    public String getReadableString() {
        return String.format("%s (at: %s)", super.getReadableString(),
                this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy '-' hh:mm a")));
    }

    @Override
    public void serialize(DataOutputStream dOut) throws IOException {
        super.serialize(dOut);
        dOut.writeUTF(this.at.toString());
    }

    @Override
    public Task readSerializedData(DataInputStream dIn) throws IOException {
        super.readSerializedData(dIn);
        this.at = LocalDateTime.parse(dIn.readUTF());
        return this;
    }
}
