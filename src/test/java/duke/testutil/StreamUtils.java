package duke.testutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Provides helper functions for testing scripts.
 */
public class StreamUtils {
    /**
     * Creates a writable stream and returns all the content written in by the supplied consumer.
     *
     * @param writer Consumer writing into the writable stream.
     * @return All data written into the stream.
     * @throws IOException If any write errors occur.
     */
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

    /**
     * Creates a readable stream from the supplied input data, and passes it to the supplied consumer.
     *
     * @param reader Consumer reading data from the stream.
     * @param data Data contained in the readable stream.
     * @throws IOException If any read errors occur.
     */
    public static void buildInputStream(ThrowableConsumer<DataInputStream, IOException> reader,
                                                   byte[] data) throws IOException {
        final ByteArrayInputStream memStream = new ByteArrayInputStream(data);
        final DataInputStream dataStream = new DataInputStream(memStream);
        reader.accept(dataStream);
        dataStream.close();
    }
}
