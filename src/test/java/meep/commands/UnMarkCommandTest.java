package meep.commands;

import static meep.commands.UnMarkCommand.MESSAGE_UNMARK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import meep.task.Deadline;
import meep.task.Event;
import meep.task.ListTask;
import meep.task.ToDo;


public class UnMarkCommandTest {

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

        UnMarkCommand c1 = new UnMarkCommand(1);
        UnMarkCommand c2 = new UnMarkCommand(2);
        UnMarkCommand c3 = new UnMarkCommand(3);

        deadline2.unmarkDone();
        event2.unmarkDone();
        todo2.unmarkDone();

        assertEquals(MESSAGE_UNMARK + deadline2.toString(), c1.execute(tasks));
        assertEquals(MESSAGE_UNMARK + event2.toString(), c2.execute(tasks));
        assertEquals(MESSAGE_UNMARK + todo2.toString(), c3.execute(tasks));
    }
}
