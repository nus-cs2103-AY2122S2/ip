package heylo.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void toString_listCommand_noArg() {
        Command command = new Command("list");
        assertEquals("Command{command='list', argument='null', extraInfo='null'}",
                command.toString());
    }

    @Test
    public void toString_todoCommand_noExtra() {
        Command command = new Command("todo todo");
        assertEquals("Command{command='todo', argument='todo', extraInfo='null'}",
                command.toString());
    }

    @Test
    public void toString_eventCommand_allArgs() {
        Command command = new Command("event event /by 2020-02-22");
        assertEquals("Command{command='event', argument='event ', extraInfo='2020-02-22'}",
                command.toString());
    }

    @Test
    public void run_invalidCommand_default() {
        Command command = new Command("/ random");
        String result = command.run();
        assertEquals(" Sorry, I don't understand what that means.\n", result);
    }

    @Test
    public void run_invalidArgs_errorMsg() {
        Command command = new Command("mark");
        String result = command.run();
        assertEquals(" Please enter the task number as well!\n"
                + " Command format: mark task-number\n", result);
    }
}
