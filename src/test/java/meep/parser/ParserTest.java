package meep.parser;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import meep.commands.AddCommand;
import meep.exception.InvalidInputException;
import meep.task.Deadline;
import meep.task.Event;
import meep.task.ListTask;
import meep.task.ToDo;


public class ParserTest {
    @Test
    public void checkTaskFormat_normalInput_success() throws InvalidInputException {
        Parser parser = new Parser();
        assertEquals("test", parser.checkEmptyTask("test"));
    }

    @Test
    public void checkTaskFormat_emptyInput_exceptionThrown() {
        Parser parser = new Parser();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            parser.checkEmptyTask("");
        }, "InvalidInputException was expected");
        assertEquals("Task description can not be empty!", thrown.getMessage());
    }

    @Test
    public void parseListIndex_normalInput_success() throws InvalidInputException {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        Deadline deadline = new Deadline("read book", dateTime);
        Event event = new Event("report2", dateTime);
        ToDo todo = new ToDo("report3");

        ListTask tasks = new ListTask();

        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.addTask(todo);

        Parser parser = new Parser();

        final String filePath = "/java/test.txt";
        String home = System.getProperty("user.dir");
        // works on *nix
        // works on Windows
        String path = home + filePath;
        assertEquals(0, parser.parseListIndex("0", tasks.getList()));
        assertEquals(1, parser.parseListIndex("1", tasks.getList()));
        assertEquals(2, parser.parseListIndex("2", tasks.getList()));
    }

    @Test
    public void parseListIndex_invalidInput_exceptionThrown() {
        LocalDateTime dateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);
        Deadline deadline = new Deadline("report1", dateTime);
        Event event = new Event("report2", dateTime);
        ToDo todo = new ToDo("report3");

        ListTask tasks = new ListTask();

        tasks.addTask(deadline);
        tasks.addTask(event);
        tasks.addTask(todo);

        Parser parser = new Parser();

        InvalidInputException thrown1 = assertThrows(InvalidInputException.class, () -> {
            parser.parseListIndex("invalid", tasks.getList());
        }, "InvalidInputException was expected");
        assertEquals("Invalid task number. Please enter a valid integer.", thrown1.getMessage());

        InvalidInputException thrown2 = assertThrows(InvalidInputException.class, () -> {
            parser.parseListIndex("-1", tasks.getList());
        }, "InvalidInputException was expected");
        assertEquals("Task number out of range.", thrown2.getMessage());
    }

    @Test
    public void parseTaskFormat_normalInput_success() throws InvalidInputException {
        Parser parser = new Parser();
        String[] result = {"return book ", "by 02/12/2019 18:00"};
        assertArrayEquals(result, parser.parseTaskFormat("return book /by 02/12/2019 18:00"));
    }

    @Test
    public void parseTaskFormat_invalidInput_exceptionThrown() {
        Parser parser = new Parser();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            parser.parseTaskFormat("NO_SLASH");
        }, "InvalidInputException was expected");
        assertEquals("Invalid format. eg. deadline return book /by 02/12/2019 18:00.", thrown.getMessage());
    }


    @Test
    public void checkPrepositionFormat_normalInput_success() throws InvalidInputException {
        Parser parser = new Parser();
        assertEquals("02/12/2019 18:00",
                parser.checkPrepositionFormat("by 02/12/2019 18:00", AddCommand.COMMAND_DEADLINE));
        assertEquals("02/12/2019 18:00",
                parser.checkPrepositionFormat("on 02/12/2019 18:00", AddCommand.COMMAND_EVENT));
        assertEquals("02/12/2019 18:00",
                parser.checkPrepositionFormat("at 02/12/2019 18:00", AddCommand.COMMAND_EVENT));
    }

    @Test
    public void checkPrepositionFormat_invalidInput_exceptionThrown() {
        Parser parser = new Parser();
        InvalidInputException thrown1 = assertThrows(InvalidInputException.class, () -> {
            parser.checkPrepositionFormat("by", AddCommand.COMMAND_DEADLINE);
        }, "InvalidInputException was expected");
        assertEquals("Invalid format. eg. deadline return book /by 02/12/2019 18:00.", thrown1.getMessage());

        InvalidInputException thrown2 = assertThrows(InvalidInputException.class, () -> {
            parser.checkPrepositionFormat("invalidPreposition 02/12/2019 18:00", AddCommand.COMMAND_DEADLINE);
        }, "InvalidInputException was expected");
        assertEquals("Invalid Prepositions for deadline command. eg. '/by'.", thrown2.getMessage());

        InvalidInputException thrown3 = assertThrows(InvalidInputException.class, () -> {
            parser.checkPrepositionFormat("invalidPreposition 02/12/2019 18:00", AddCommand.COMMAND_EVENT);
        }, "InvalidInputException was expected");
        assertEquals("Invalid Prepositions for event command. eg. '/on' or '/at' .", thrown3.getMessage());
    }

    @Test
    public void parseDate_normalInput_success() throws InvalidInputException {
        Parser parser = new Parser();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse("02/12/2019 18:00", format);
        assertEquals(formattedDate, parser.parseDate("02/12/2019 18:00"));
    }

    @Test
    public void parseDate_invalidInput_exceptionThrown() {
        Parser parser = new Parser();
        String invalidDate = "2/12/2019 18:00";
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            parser.parseDate(invalidDate);
        }, "InvalidInputException was expected");
        assertEquals("'" + invalidDate
                + "' can't be formatted! Please format the date/time as dd/MM/yyyy HH:mm", thrown.getMessage());
    }

}
