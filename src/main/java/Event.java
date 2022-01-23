import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Event extends Task {
    private String at;

    public Event(String task, String at) {
        super(TaskType.EVENT, task);
        this.at = at;
    }

    protected Event() {
        this("", "");
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String getReadableString() {
        return String.format("%s (at: %s)", super.getReadableString(), this.at);
    }

    @Override
    public void serialize(DataOutputStream dOut) throws IOException {
        super.serialize(dOut);
        dOut.writeUTF(this.at);
    }

    @Override
    public Task readSerializedData(DataInputStream dIn) throws IOException {
        super.readSerializedData(dIn);
        this.at = dIn.readUTF();
        return this;
    }
}
