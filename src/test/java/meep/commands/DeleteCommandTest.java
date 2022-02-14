package meep.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import meep.task.Deadline;
import meep.task.Event;
import meep.task.ListTask;
import meep.task.ToDo;

public class DeleteCommandTest {
    @Test
    public void deleteCommandExecute_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        Deadline deadline = new Deadline("report1", dateTime);
        Event event = new Event("report2", dateTime);
        ToDo todo = new ToDo("report3");

        ListTask tasks = new ListTask();

        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.addTask(todo);

        DeleteCommand c1 = new DeleteCommand(1);
        DeleteCommand c2 = new DeleteCommand(1);
        DeleteCommand c3 = new DeleteCommand(1);
        DeleteCommand c4 = new DeleteCommand(1);

        assertEquals(DeleteCommand.MESSAGE_DELETE + deadline.toString()
                + "\nNow you have 2 tasks in the list.", c1.execute(tasks));
        assertEquals(DeleteCommand.MESSAGE_DELETE + event.toString()
                + "\nNow you have 1 tasks in the list.", c2.execute(tasks));
        assertEquals(DeleteCommand.MESSAGE_DELETE + todo.toString()
                + "\nNow you have 0 tasks in the list.", c3.execute(tasks));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            c4.execute(tasks);
        });

    }

}
