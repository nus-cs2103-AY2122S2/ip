package meep.commands;

import static meep.commands.UnmarkCommand.MESSAGE_UNMARK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import meep.task.Deadline;
import meep.task.Event;
import meep.task.ListTask;
import meep.task.ToDo;


public class UnmarkCommandTest {

    @Test
    public void unmarkCommandExecute_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        Deadline deadline = new Deadline("report1", true, dateTime);
        Deadline deadline2 = new Deadline("report1", true, dateTime);
        Event event = new Event("report2", true, dateTime);
        Event event2 = new Event("report2", true, dateTime);
        ToDo todo = new ToDo("report3", true);
        ToDo todo2 = new ToDo("report3", true);

        ListTask tasks = new ListTask();

        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.addTask(todo);

        UnmarkCommand c1 = new UnmarkCommand(1);
        UnmarkCommand c2 = new UnmarkCommand(2);
        UnmarkCommand c3 = new UnmarkCommand(3);

        deadline2.unmarkDone();
        event2.unmarkDone();
        todo2.unmarkDone();

        assertEquals(MESSAGE_UNMARK + deadline2.toString(), c1.execute(tasks));
        assertEquals(MESSAGE_UNMARK + event2.toString(), c2.execute(tasks));
        assertEquals(MESSAGE_UNMARK + todo2.toString(), c3.execute(tasks));
    }
}
