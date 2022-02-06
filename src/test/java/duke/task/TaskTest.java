package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.EOFException;
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

    @Test
    public void testInflate_valid_success() throws IOException {
        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeUTF("Test Description 1");
            dOut.writeBoolean(true);
            dOut.writeUTF("2022-12-12T13:00");
        });

        byte[] reference2 = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeUTF("Test 2");
            dOut.writeBoolean(false);
        });

        // Test Factory
        StreamUtils.buildInputStream((dIn) -> {
            Task task = Task.inflate(TaskType.TODO, dIn);
            assertTrue(task instanceof Todo);
            assertTrue(task.isDone());
            assertEquals("Test Description 1", task.getDescription());
        }, reference);
        StreamUtils.buildInputStream((dIn) -> {
            Task task = Task.inflate(TaskType.DEADLINE, dIn);
            assertTrue(task instanceof Deadline);
            assertTrue(task.isDone());
            assertEquals("Test Description 1", task.getDescription());
        }, reference);
        StreamUtils.buildInputStream((dIn) -> {
            Task task = Task.inflate(TaskType.EVENT, dIn);
            assertTrue(task instanceof Event);
            assertTrue(task.isDone());
            assertEquals("Test Description 1", task.getDescription());
        }, reference);

        StreamUtils.buildInputStream((dIn) -> {
            Task task = Task.inflate(TaskType.TODO, dIn);
            assertNotNull(task);
            assertFalse(task.isDone());
            assertEquals("Test 2", task.getDescription());
        }, reference2);
    }

    @Test
    public void testInflate_invalid_exception() throws IOException {
        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.write(256);
            dOut.write(222);
            dOut.writeUTF("Garbage data");
        });

        try {
            StreamUtils.buildInputStream((dIn) -> {
                Task task = Task.inflate(TaskType.TODO, dIn);
                assertTrue(task instanceof Todo);
                assertTrue(task.isDone());
                assertEquals("Test Description 1", task.getDescription());
            }, reference);
            fail();
        } catch (IOException ex) {
            assertTrue(ex instanceof EOFException);
        }
    }
}
