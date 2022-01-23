import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Task implements ISerializable {
    private final TaskType type;
    private String description;
    private boolean isDone;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getReadableString() {
        return String.format("[%s][%s] %s", this.type.getShorthand(), this.getStatusIcon(),
                this.description);
    }

    @Override
    public void serialize(DataOutputStream dOut) throws IOException {
        dOut.writeShort(this.type.getTypeId());
        dOut.writeUTF(this.description);
        dOut.writeBoolean(this.isDone);
    }

    protected Task readSerializedData(DataInputStream dIn) throws IOException {
        this.description = dIn.readUTF();
        this.isDone = dIn.readBoolean();
        return this;
    }

    public static Task inflate(TaskType type, DataInputStream dIn) throws IOException {
        Task task;
        switch (type) {
        case TODO:
            task = new Todo();
            break;
        case EVENT:
            task = new Event();
            break;
        case DEADLINE:
            task = new Deadline();
            break;
        default:
            return null;
        }
        return task.readSerializedData(dIn);
    }
}
