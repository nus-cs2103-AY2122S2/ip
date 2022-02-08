package duke.task.serializer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import duke.exception.DukeIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.StreamUtils;


public class TaskListSerializerTest {
    @Test
    public void testInflate_valid_success() throws IOException, DukeIoException {
        byte[] taskData = new byte[]{ 1, 2, 3, 4, 5 };
        byte[] data = StreamUtils.buildOutputStream((dOut) -> {
            dOut.writeInt(4);
            for (int i = 0; i < 4; i++) {
                dOut.writeInt(taskData.length);
                dOut.write(taskData);
            }
        });

        try (MockedStatic<TaskSerializer> theMock = Mockito.mockStatic(TaskSerializer.class)) {
            theMock.when(() -> TaskSerializer.inflate(any())).thenReturn(new Todo(""));

            TaskList list = TaskListSerializer.inflate(new ByteArrayInputStream(data));
            assertEquals(4, list.getTaskCount());
        }

    }

    @Test
    public void testInflate_invalidData_recordSkipped() throws IOException, DukeIoException {
        try (MockedStatic<TaskSerializer> theMock = Mockito.mockStatic(TaskSerializer.class)) {
            byte[] data = StreamUtils.buildOutputStream((dOut) -> {
                dOut.writeInt(5);
                for (int i = 0; i < 5; i++) {
                    dOut.writeInt(1);
                    dOut.write(0);
                }
            });

            theMock.when(() -> TaskSerializer.inflate(any()))
                    .thenThrow(new DukeIoException(""))
                    .thenReturn(new Todo(""))
                    .thenReturn(new Todo(""))
                    .thenReturn(new Todo(""))
                    .thenThrow(new DukeIoException(""));

            TaskList list = TaskListSerializer.inflate(new ByteArrayInputStream(data));
            assertEquals(3, list.getTaskCount());
        }
    }

    @Test
    public void testDeflate_valid_success() throws DukeIoException, IOException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Test 1"));
        taskList.addTask(new Deadline("Test 2", LocalDateTime.parse("2022-12-12T12:34")));
        taskList.addTask(new Event("Test 3", LocalDateTime.parse("2022-12-12T12:34")));

        try (MockedStatic<TaskSerializer> theMock = Mockito.mockStatic(TaskSerializer.class)) {
            theMock.when(() -> TaskSerializer.deflate(any()))
                    .thenReturn(new byte[]{1})
                    .thenReturn(new byte[]{2})
                    .thenReturn(new byte[]{3});

            ByteArrayOutputStream memStream = new ByteArrayOutputStream();
            TaskListSerializer.deflate(taskList, memStream);

            byte[] deflated = memStream.toByteArray();
            memStream.close();

            byte[] reference = StreamUtils.buildOutputStream((dOut) -> {
                dOut.writeInt(3);
                dOut.writeInt(1);
                dOut.write(1);
                dOut.writeInt(1);
                dOut.write(2);
                dOut.writeInt(1);
                dOut.write(3);
            });
            assertArrayEquals(reference, deflated);
        }
    }
}
