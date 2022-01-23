import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ISerializable<T> {
    void serialize(DataOutputStream dOut) throws IOException;
}
