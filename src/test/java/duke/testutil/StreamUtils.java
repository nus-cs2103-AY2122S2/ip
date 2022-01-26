package duke.testutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StreamUtils {
    public static byte[] buildOutputStream(ThrowableConsumer<DataOutputStream, IOException> writer)
            throws IOException {
        final ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        final DataOutputStream dataStream = new DataOutputStream(memStream);

        writer.accept(dataStream);
        dataStream.flush();
        final byte[] ret = memStream.toByteArray();
        dataStream.close();
        return ret;
    }

    public static void buildInputStream(ThrowableConsumer<DataInputStream, IOException> reader,
                                                   byte[] data) throws IOException {
        final ByteArrayInputStream memStream = new ByteArrayInputStream(data);
        final DataInputStream dataStream = new DataInputStream(memStream);
        reader.accept(dataStream);
        dataStream.close();
    }
}
