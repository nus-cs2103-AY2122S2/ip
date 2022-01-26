package duke.task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a deadline type task.
 * Contains a description and an associated limit date.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a deadline type task.
     * @param task Description of the deadline.
     * @param by Limit date of the task.
     */
    public Deadline(String task, LocalDateTime by) {
        super(TaskType.DEADLINE, task);
        this.by = by;
    }

    /**
     * Creates an empty instance of the class to be populated by the
     * {@link #readSerializedData(DataInputStream)} method.
     * Usage should be avoided as there is potential for inconsistent states when the attributes are
     * not immediately populated after creation.
     */
    protected Deadline() {
        this("", null);
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
