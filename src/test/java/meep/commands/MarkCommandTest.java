package meep.commands;

import static meep.commands.MarkCommand.MESSAGE_MARK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import meep.task.Deadline;
import meep.task.Event;
import meep.task.ListTask;
import meep.task.ToDo;


public class MarkCommandTest {

    @Test
    public void markCommandExecute_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        Deadline deadline = new Deadline("report1", dateTime);
        Deadline deadline2 = new Deadline("report1", dateTime);
        Event event = new Event("report2", dateTime);
        Event event2 = new Event("report2", dateTime);
        ToDo todo = new ToDo("report3");
        ToDo todo2 = new ToDo("report3");

        ListTask tasks = new ListTask();

        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.addTask(todo);

        MarkCommand c1 = new MarkCommand(1);
        MarkCommand c2 = new MarkCommand(2);
        MarkCommand c3 = new MarkCommand(3);

        deadline2.markDone();
        event2.markDone();
        todo2.markDone();

        assertEquals(MESSAGE_MARK + deadline2.toString(), c1.execute(tasks));
        assertEquals(MESSAGE_MARK + event2.toString(), c2.execute(tasks));
        assertEquals(MESSAGE_MARK + todo2.toString(), c3.execute(tasks));
    }
}
