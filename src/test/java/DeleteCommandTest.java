import lucifer.commands.ParseCommands;
import lucifer.task.Deadline;
import lucifer.task.Event;
import lucifer.task.Task;
import lucifer.task.Todo;
import lucifer.tasklist.TaskList;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

    @Test
    public void DeleteTask() {
        TaskList tasks = new TaskList();
        ParseCommands parser = new ParseCommands(tasks);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse("12-12-2022 1800", formatter);
        ArrayList<Task> list = tasks.getTaskList();
        list.add(new Todo("drink water"));
        list.add(new Deadline("eat", dateTime));
        list.add(new Event("sleep", dateTime));
        parser.parseCommand("delete 1");
        parser.parseCommand("delete 1");
        parser.parseCommand("delete 1");
        assertTrue(list.isEmpty());
    }
}
