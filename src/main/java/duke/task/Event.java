package duke.task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents an event type task.
 * Contains a description and an associated date.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Creates an event type task.
     *
     * @param task Description of the event.
     * @param at Date associated with the event.
     */
    public Event(String task, LocalDateTime at) {
        super(TaskType.EVENT, task);
        this.at = at;
    }

    /**
     * Creates an empty instance of the class to be populated by the
     * {@link #readSerializedData(DataInputStream)} method.
     * Usage should be avoided as there is potential for inconsistent states when the attributes are
     * not immediately populated after creation.
     */
    protected Event() {
        this("", null);
    }

    @Override
    public Optional<LocalDateTime> getDate() {
        return Optional.of(this.at);
    }

    @Override
    public String getReadableString() {
        return String.format("%s (at: %s)", super.getReadableString(),
                this.at.format(DateTimeFormatter.ofPattern(FORMAT_DATETIME)));
    }

    @Override
    public void serialize(DataOutputStream dOut) throws IOException {
        super.serialize(dOut);
        dOut.writeUTF(this.at.toString());
    }

    @Override
    protected Task readSerializedData(DataInputStream dIn) throws IOException {
        super.readSerializedData(dIn);
        this.at = LocalDateTime.parse(dIn.readUTF());
        return this;
    }
}
