package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.EOFException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.testutil.StreamUtils;

public class TaskFactoryTest {
    @Test
    public void testInflateType_valid_success() throws IOException {
        byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeUTF("Test Description 1");
            dOut.writeBoolean(true);
            dOut.writeUTF("2022-12-12T13:00");
        });

        // Test Factory
        StreamUtils.buildInputStream((dIn) -> {
            Task task = TaskFactory.inflate(TaskType.TODO, dIn);
            assertTrue(task instanceof Todo);
            assertTrue(task.isDone());
            assertEquals("Test Description 1", task.getDescription());
        }, reference);
        StreamUtils.buildInputStream((dIn) -> {
            Task task = TaskFactory.inflate(TaskType.DEADLINE, dIn);
            assertTrue(task instanceof Deadline);
            assertTrue(task.isDone());
            assertEquals("Test Description 1", task.getDescription());
        }, reference);
        StreamUtils.buildInputStream((dIn) -> {
            Task task = TaskFactory.inflate(TaskType.EVENT, dIn);
            assertTrue(task instanceof Event);
            assertTrue(task.isDone());
            assertEquals("Test Description 1", task.getDescription());
        }, reference);
    }

    @Test
    public void testInflateDoneState_valid_success() throws IOException {
        byte[] reference2 = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeUTF("Test 2");
            dOut.writeBoolean(false);
        });

        StreamUtils.buildInputStream((dIn) -> {
            Task task = TaskFactory.inflate(TaskType.TODO, dIn);
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
                Task task = TaskFactory.inflate(TaskType.TODO, dIn);
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
