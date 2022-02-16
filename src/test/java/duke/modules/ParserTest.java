package duke.modules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ParserTest {

    public Parser initialiseParserWithTaskListStub() {
        ArrayList<Task> list = new ArrayList<>();
        Deadline deadline = new Deadline("Buy Groceries", "2022-01-01");
        Event event = new Event("Meeting", "2022-01-02");
        ToDo toDo = new ToDo("Homework");
        list.add(deadline);
        list.add(event);
        list.add(toDo);
        return new Parser(new TaskListStub(list));
    }

    @Test
    public void byeCommandTest() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("goodbye!\n", parser.parse("bye"));
        assertTrue(parser.byeCommandHasExecuted());
    }

    @Test
    public void listCommandTest() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("1. [D][ ] Buy Groceries (by: Saturday, 1 January 2022)\n" +
                "2. [E][ ] Meeting (at: Sunday, 2 January 2022)\n" +
                "3. [T][ ] Homework\n", parser.parse("list"));

    }

    @Test
    public void helpCommandTest() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("try the following commands:\n"
                + "   list (list out all tasks in your todo list)\n"
                + "   todo <task> (add a basic task to your todo list)\n"
                + "   deadline <task> /by <yyyy-mm-dd> (add a task with a deadline to your todo list)\n"
                + "   event <task> /at <yyyy-mm-dd> (add a new event with its corresponding date to your todo list)\n"
                + "   mark <task index> (mark that specific task as completed)\n"
                + "   unmark <task index> (remove mark from specific task)\n"
                + "   delete <task index> (remove task from todo list)\n"
                + "   bye (close application)\n", parser.parse("help"));
    }

    @Test
    public void unmarkCommandEmptyDescription() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("unmark description cannot be empty!\n", parser.parse("unmark"));
    }

    @Test
    public void unmarkCommandNonInteger() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("unmark description must be an integer!\n", parser.parse("unmark 1.2"));
    }

    @Test
    public void unmarkCommandOutOfBounds() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("the index you have entered does not exist!\n", parser.parse("unmark 5"));
    }

    @Test
    public void unmarkCommandSuccess() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("Boo! more work to do: Buy Groceries\n", parser.parse("unmark 1"));
    }

    @Test
    public void markCommandEmptyDescription() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("mark description cannot be empty!\n", parser.parse("mark"));
    }

    @Test
    public void markCommandNonInteger() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("mark description must be an integer!\n", parser.parse("mark one"));
    }

    @Test
    public void markCommandOutOfBounds() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("the index you have entered does not exist!\n", parser.parse("mark 4"));
    }

    @Test
    public void markCommandSuccess() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("great job! I've marked this task as done: Buy Groceries\n"
                , parser.parse("mark 1"));
    }

    @Test
    public void toDoCommandEmptyDescription() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("the description of a todo cannot be empty!\n", parser.parse("todo "));
    }

    @Test
    public void toDoCommandSuccess() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("task added:\n" + "[T][ ] study\n" + "you now have 4 tasks\n"
                , parser.parse("todo study"));
    }

    @Test
    public void deadlineCommandEmptyDescription() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("deadline description cannot be empty!\n", parser.parse("deadline "));
    }

    @Test
    public void deadlineCommandMissingDate() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("deadline description must contain a date!\n", parser.parse("deadline do "));
    }

    @Test
    public void deadlineCommandSuccess() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("task added:\n"
                        + "[D][ ] study (by: Thursday, 3 March 2022)\n"
                        + "you now have 4 tasks\n"
                , parser.parse("deadline study /by 2022-03-03") );
    }

    @Test
    public void eventCommandEmptyDescription() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("event description cannot be empty!\n", parser.parse("event "));
    }

    @Test
    public void eventCommandMissingDate() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("event description must contain a date!\n", parser.parse("event one"));
    }

    @Test
    public void eventCommandSuccess() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("task added:\n"
                + "[E][ ] virtual meeting (at: Monday, 4 April 2022)\n"
                + "you now have 4 tasks\n", parser.parse("event virtual meeting /at 2022-04-04"));
    }

    @Test
    public void deleteCommandEmptyDescription() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("delete description cannot be empty!\n", parser.parse("delete "));
    }

    @Test
    public void deleteCommandNonInteger() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("delete description must be an integer!\n", parser.parse("delete one"));
    }

    @Test
    public void deleteCommandOutOfBounds() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("the index you have entered does not exist!\n", parser.parse("delete 4"));
    }

    @Test
    public void deleteCommandSuccess() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("task removed:\n"
                + "[D][ ] Buy Groceries (by: Saturday, 1 January 2022)\n"
                + "you now have 2 tasks\n", parser.parse("delete 1") );
    }

    @Test
    public void invalidCommand() {
        Parser parser = initialiseParserWithTaskListStub();
        assertEquals("invalid command! try 'help' for list of commands\n", parser.parse("help1"));
    }
}
