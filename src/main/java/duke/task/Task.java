package duke.task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import duke.util.Serializable;

/**
 * Represents a generic Task with only a basic description and a completion marker.
 */
public abstract class Task implements Serializable {
    protected static final String FORMAT_DATETIME = "d MMM yyyy '-' hh:mm a";

    private final TaskType type;
    private String description;
    private boolean isDone;

    /**
     * Sole Constructor for {@link Task}.
     *
     * @param type Type of the task to be created.
     * @param description Description of the task.
     */
    protected Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed or uncompleted, depending on the argument.
     *
     * @param isDone New completion status of the task.
     */
    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the user-provided description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Completion status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns an icon associated with the current completion status of the task.
     *
     * @return X if the task is completed, single whitespace otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns the date, if any, associated with the task.
     *
     * @return {@link Optional} object containing a {@link LocalDateTime} object.
     */
    public Optional<LocalDateTime> getDate() {
        return Optional.empty();
    }

    /**
     * Returns a summary string containing all the current attributes of the task.
     *
     * @return Human-readable String that contains the current attributes.
     */
    public String getReadableString() {
        return String.format("[%s][%s] %s", this.type.getShorthand(), this.getStatusIcon(),
                this.description);
    }

    /**
     * Flattens and writes the task object into the supplied {@link DataOutputStream}.
     *
     * @param dOut Output stream for the current instance to be serialized to.
     * @throws IOException If an error occurs during any write operation.
     */
    @Override
    public void serialize(DataOutputStream dOut) throws IOException {
        assert dOut != null;

        dOut.writeShort(this.type.getTypeId());
        dOut.writeUTF(this.description);
        dOut.writeBoolean(this.isDone);
    }

    /**
     * Populates the attributes of this instance with attribute data read from the
     * supplied {@link DataInputStream}.
     *
     * @param dIn Input stream to read attribute data from.
     * @return The current instance of the task.
     * @throws IOException If an error occurs during any read operation.
     */
    protected Task readSerializedData(DataInputStream dIn) throws IOException {
        assert dIn != null;

        this.description = dIn.readUTF();
        this.isDone = dIn.readBoolean();

        return this;
    }
}
