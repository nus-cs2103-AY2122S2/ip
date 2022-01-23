import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Deadline extends Task {
    private String by;

    public Deadline(String task, String by) {
        super(TaskType.DEADLINE, task);
        this.by = by;
    }

    protected Deadline() {
        this("", "");
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getReadableString() {
        return String.format("%s (by: %s)", super.getReadableString(), this.by);
    }

    @Override
    public void serialize(DataOutputStream dOut) throws IOException {
        super.serialize(dOut);
        dOut.writeUTF(by);
    }

    @Override
    public Task readSerializedData(DataInputStream dIn) throws IOException {
        super.readSerializedData(dIn);
        this.by = dIn.readUTF();
        return this;
    }
}
