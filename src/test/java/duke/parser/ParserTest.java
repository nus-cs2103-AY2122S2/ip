package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;

import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class ParserTest {
    /**
     * Tests if the parser parses an add command correctly
     */
    @Test
    public void parseAddTest() throws DukeException {
        String inputCommand = "deadline d1 \\by 2020-02-12 1600";
        Command deadline = new AddCommand(new Deadline("d1",
                LocalDateTime.parse("2020-02-12 1600", Parser.formatter)), inputCommand.split(" "));
        Command deadlineResult = Parser.parse(inputCommand);
        assertEquals(deadline, deadlineResult);

        inputCommand = "event e1 \\at 2020-02-12 1600";
        Command event = new AddCommand(new Event("e1",
                LocalDateTime.parse("2020-02-12 1600", Parser.formatter)), inputCommand.split(" "));
        Command eventResult = Parser.parse(inputCommand);
        assertEquals(event, eventResult);

        inputCommand = "todo t1";
        Command todo = new AddCommand(new ToDo("t1"), inputCommand.split(" "));
        Command todoResult = Parser.parse(inputCommand);
        assertEquals(todo, todoResult);
    }

    @Test
    public void parseDeleteTest() throws DukeException {
        String inputCommand = "delete 1";
        Command delete = new DeleteCommand(1, inputCommand.split(" "));
        Command result = Parser.parse(inputCommand);
        assertEquals(delete, result);
    }

    @Test
    public void parseListTest() throws DukeException {
        String inputCommand = "list";
        Command list = new ListCommand(inputCommand.split(" "));
        Command result = Parser.parse(inputCommand);
        assertEquals(list, result);
    }

    @Test
    public void parseByeTest() throws DukeException {
        String inputCommand = "bye";
        Command bye = new ByeCommand(inputCommand.split(" "));
        Command result = Parser.parse(inputCommand);
        assertEquals(bye, result);
    }

    @Test
    public void parseMarkTest() throws DukeException {
        String inputCommand = "mark 1";
        Command mark = new MarkCommand(1, MarkCommand.Mark.MARK, inputCommand.split(" "));
        Command result = Parser.parse(inputCommand);
        assertEquals(mark, result);

        inputCommand = "unmark 1";
        Command unmark = new MarkCommand(1, MarkCommand.Mark.UNMARK, inputCommand.split(" "));
        Command result2 = Parser.parse(inputCommand);
        assertEquals(unmark, result2);
    }

    @Test
    public void parseSortTest() throws DukeException {
        String inputCommand = "sort date";
        Command sortDate = new SortCommand(TaskList.SortType.DATE, inputCommand.split(" "));
        Command result = Parser.parse(inputCommand);
        assertEquals(sortDate, result);

        inputCommand = "sort content";
        Command sortContent = new SortCommand(TaskList.SortType.CONTENT, inputCommand.split(" "));
        Command result2 = Parser.parse(inputCommand);
        assertEquals(sortContent, result2);
    }
}
