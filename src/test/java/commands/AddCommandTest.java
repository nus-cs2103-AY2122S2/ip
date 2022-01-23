package commands;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.ListTask;
import task.ToDo;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void addCommandExecute_normalInput_success() {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        Deadline deadline = new Deadline("report1",dateTime);
        Event event = new Event("report2",dateTime);
        ToDo todo = new ToDo("report3");
        AddCommand c1=new AddCommand(deadline);
        AddCommand c2=new AddCommand(event);
        AddCommand c3=new AddCommand(todo);

        ListTask tasks=new ListTask();
        assertEquals(AddCommand.MESSAGE_ADD+ deadline.toString() + "\nNow you have 1 tasks in the list.",c1.execute(tasks));
        assertEquals(AddCommand.MESSAGE_ADD+ event.toString() + "\nNow you have 2 tasks in the list.",c2.execute(tasks));
        assertEquals(AddCommand.MESSAGE_ADD+ todo.toString() + "\nNow you have 3 tasks in the list.",c3.execute(tasks));

        }

}
