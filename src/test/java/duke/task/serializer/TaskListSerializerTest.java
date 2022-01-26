package duke.task.serializer;

import duke.exception.DukeIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.StreamUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListSerializerTest {
    @Test
    public void testInflate_valid_success() throws IOException, DukeIoException {
        byte[] taskData = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(1);
            dOut.writeUTF("Test Description 1");
            dOut.writeBoolean(true);
        });

        byte[] data = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeInt(4);
            for (int i = 0; i < 4; i++) {
                dOut.writeInt(taskData.length);
                dOut.write(taskData);
            }
        });

        TaskList list = TaskListSerializer.inflate(new ByteArrayInputStream(data));
        assertEquals(4, list.getTaskCount());
    }

    @Test
    public void testInflate_invalidData_recordSkipped() throws IOException, DukeIoException {
        byte[] taskData = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(1);
            dOut.writeUTF("Test Description 1");
            dOut.writeBoolean(true);
        });

        byte[] invalidTaskData = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeShort(4);
            dOut.writeUTF("Test Description 1");
            dOut.writeBoolean(true);
        });

        byte[] data = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeInt(5);
            dOut.writeInt(invalidTaskData.length);
            dOut.write(invalidTaskData);
            for (int i = 0; i < 3; i++) {
                dOut.writeInt(taskData.length);
                dOut.write(taskData);
            }
            dOut.writeInt(invalidTaskData.length);
            dOut.write(invalidTaskData);
        });

        TaskList list = TaskListSerializer.inflate(new ByteArrayInputStream(data));
        assertEquals(3, list.getTaskCount());
    }

    @Test
    public void testDeflate_valid_success() throws DukeIoException, IOException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Test 1"));
        taskList.addTask(new Deadline("Test 2", LocalDateTime.parse("2022-12-12T12:34")));
        taskList.addTask(new Event("Test 3", LocalDateTime.parse("2022-12-12T12:34")));

        ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        TaskListSerializer.deflate(taskList, memStream);

        byte[] deflated = memStream.toByteArray();
        memStream.close();

        ByteArrayInputStream memInStream = new ByteArrayInputStream(deflated);
        assertEquals(3, TaskListSerializer.inflate(memInStream).getTaskCount());
    }
}
