package duke.task;

import duke.testutil.StreamUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    @Test
    public void testInitialState_testStrings_success() {
        Event t1 = new Event("Test 1", LocalDateTime.parse("2022-12-22T12:34"));
        assertTrue(t1.getDate().isPresent());
        assertEquals("2022-12-22T12:34", t1.getDate().get().toString());
        assertEquals(String.format("[%s][ ] Test 1 (at: 22 Dec 2022 - 12:34 PM)",
                        TaskType.EVENT.getShorthand()), t1.getReadableString());
    }

    @Test
    public void testSerialize_valid_success() throws IOException {
        byte[] actual = StreamUtils.buildOutputStream((dOut) -> {
            Task t1 = new Event("Test 2", LocalDateTime.parse("2023-10-21T23:45"));
            t1.serialize(dOut);
        });

        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(TaskType.EVENT.getTypeId());
            dOut.writeUTF("Test 2");
            dOut.writeBoolean(false);
            dOut.writeUTF("2023-10-21T23:45");
        });

        assertArrayEquals(reference, actual);
    }

    @Test
    public void testInflate_valid_success() throws IOException {
        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeUTF("Test Description 1");
            dOut.writeBoolean(true);
            dOut.writeUTF("2020-11-13T12:34");
        });

        StreamUtils.buildInputStream((dIn) -> {
            Event d1 = new Event();
            d1.readSerializedData(dIn);
            assertTrue(d1.getDate().isPresent());
            assertEquals("2020-11-13T12:34", d1.getDate().get().toString());
        }, reference);
    }
}
