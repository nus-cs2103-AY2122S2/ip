package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.task.Task;

public class ParserTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private ContactList contacts;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        String home = System.getProperty("user.home");
        Path directory = Paths.get(home, "Documents", "duke");
        Path filePath = Paths.get(home, "Documents", "duke", "data.txt");
        storage = new Storage(directory, filePath);
        contacts = new ContactList();
    }

    @Test
    public void parseAddDeadlineCommandTest() throws DukeException {
        Command command = Parser.parse("deadline Do homework /by 2022-02-21 23:59");
        command.execute(tasks, ui, storage, contacts);
        Task task = tasks.get(0);
        assertEquals(task.toDataString(), "D,false,Do homework,2022-02-21 23:59");
    }

    @Test
    public void parseAddEventCommandTest() throws DukeException {
        Command command = Parser.parse("event CS2103T lecture /at 2022-02-21 12:00");
        command.execute(tasks, ui, storage, contacts);
        Task task = tasks.get(0);
        assertEquals(task.toDataString(), "E,false,CS2103T lecture,2022-02-21 12:00");
    }

    @Test
    public void parseAddTodoCommandTest() throws DukeException {
        Command command = Parser.parse("todo Independent project");
        command.execute(tasks, ui, storage, contacts);
        Task task = tasks.get(0);
        assertEquals(task.toDataString(), "T,false,Independent project");
    }

}
