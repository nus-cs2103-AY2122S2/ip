package duke.util;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Represents an object that can be serialized and written to a {@link DataOutputStream}.
 */
public interface Serializable {
    /**
     * Serializes the current instance of the class and writes it to the provided {@link DataOutputStream}.
     *
     * @param dOut Output stream for the current instance to be serialized to.
     * @throws IOException If an error occurs during any write operation to the output stream.
     */
    void serialize(DataOutputStream dOut) throws IOException;
}
