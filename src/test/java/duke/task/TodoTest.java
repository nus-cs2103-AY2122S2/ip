package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.testutil.StreamUtils;

public class TodoTest {

    @Test
    public void testInitialState_testStrings_success() {
        Todo t1 = new Todo("Test 1");
        assertTrue(t1.getDate().isEmpty());
        assertEquals(String.format("[%s][ ] %s", TaskType.TODO.getShorthand(), "Test 1"),
                t1.getReadableString());
    }

    @Test
    public void testSerialize_valid_success() throws IOException {
        byte[] actual = StreamUtils.buildOutputStream((dOut) -> {
            Todo t1 = new Todo("Test 2");
            t1.serialize(dOut);
        });

        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(TaskType.TODO.getTypeId());
            dOut.writeUTF("Test 2");
            dOut.writeBoolean(false);
        });

        assertArrayEquals(reference, actual);
    }
}
