package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.testutil.StreamUtils;

public class TaskTest {
    private static class TaskStub extends Task {
        private TaskStub(String description) {
            super(TaskType.TODO, description);
        }

        private static String getShorthand() {
            return TaskType.TODO.getShorthand();
        }

        private static int getTypeId() {
            return TaskType.TODO.getTypeId();
        }
    }

    @Test
    public void testInitialState_testStrings_success() {
        Task t1 = new TaskStub("Test Description 1");
        assertEquals("Test Description 1", t1.getDescription());
        assertFalse(t1.isDone());
        assertEquals(" ", t1.getStatusIcon());
        assertTrue(t1.getDate().isEmpty());
        assertEquals(String.format("[%s][ ] %s", TaskStub.getShorthand(), "Test Description 1"),
                t1.getReadableString());
    }

    @Test
    public void testMarking_markUnmark_success() {
        Task t1 = new TaskStub("Test Description 1");
        assertFalse(t1.isDone());
        assertEquals(" ", t1.getStatusIcon());
        assertEquals(String.format("[%s][ ] %s", TaskStub.getShorthand(), "Test Description 1"),
                t1.getReadableString());

        t1.setDone(true);
        assertTrue(t1.isDone());
        assertEquals("X", t1.getStatusIcon());
        assertEquals(String.format("[%s][X] %s", TaskStub.getShorthand(), "Test Description 1"),
                t1.getReadableString());

        t1.setDone(false);
        assertFalse(t1.isDone());
        assertEquals(" ", t1.getStatusIcon());
        assertEquals(String.format("[%s][ ] %s", TaskStub.getShorthand(), "Test Description 1"),
                t1.getReadableString());
    }

    @Test
    public void testSerialize_valid_success() throws IOException {
        byte[] actual = StreamUtils.buildOutputStream((dOut) -> {
            Task t1 = new TaskStub("Test Description 1");
            t1.serialize(dOut);
        });

        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(TaskStub.getTypeId());
            dOut.writeUTF("Test Description 1");
            dOut.writeBoolean(false);
        });

        assertArrayEquals(reference, actual);

        actual = StreamUtils.buildOutputStream((dOut) -> {
            Task t1 = new TaskStub("Test 2");
            t1.setDone(true);
            t1.serialize(dOut);
        });

        reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(TaskStub.getTypeId());
            dOut.writeUTF("Test 2");
            dOut.writeBoolean(true);
        });

        assertArrayEquals(reference, actual);
    }
}
