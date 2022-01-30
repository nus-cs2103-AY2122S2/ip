package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.testutil.StreamUtils;

public class DeadlineTest {
    @Test
    public void testInitialState_testStrings_success() {
        Deadline t1 = new Deadline("Test 1", LocalDateTime.parse("2022-12-22T12:34"));
        assertTrue(t1.getDate().isPresent());
        assertEquals("2022-12-22T12:34", t1.getDate().get().toString());
        assertEquals(String.format("[%s][ ] Test 1 (by: 22 Dec 2022 - 12:34 PM)",
                        TaskType.DEADLINE.getShorthand()), t1.getReadableString());
    }

    @Test
    public void testSerialize_valid_success() throws IOException {
        byte[] actual = StreamUtils.buildOutputStream((dOut) -> {
            Task t1 = new Deadline("Test 2", LocalDateTime.parse("2023-10-21T23:45"));
            t1.serialize(dOut);
        });

        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(TaskType.DEADLINE.getTypeId());
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
            Deadline d1 = new Deadline();
            d1.readSerializedData(dIn);
            assertTrue(d1.getDate().isPresent());
            assertEquals("2020-11-13T12:34", d1.getDate().get().toString());
        }, reference);
    }
}
