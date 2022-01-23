package commands;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.ListTask;
import task.ToDo;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void listCommandExecute_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        Deadline deadline = new Deadline("report1", dateTime);
        Event event = new Event("report2", dateTime);
        ToDo todo = new ToDo("report3");

        ListTask tasks = new ListTask();

        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.addTask(todo);

        ListCommand c1 = new ListCommand();

        String result = "\n     1.  [D][ ] report1 (by: Jul 29 2015 07:30 PM)\n" +
                "     2.  [E][ ] report2 (on: Jul 29 2015 07:30 PM)\n" +
                "     3.  [T][ ] report3\n";

        assertEquals(result,c1.execute(tasks));


    }

}