package duke;

import duke.bot.BotMessage;
import duke.bot.JJBABotMessage;
import duke.command.Command;
import duke.command.CommandFeedback;
import duke.command.add.TodoCommand;
import duke.console.Console;
import duke.exception.InvalidArgumentException;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void handleCommandFeedback_todoTask_success() {
        try {
            Command c = new TodoCommand(new Todo("todo testing"));
            CommandFeedback cf = c.execute(new TaskList());
            BotMessage bot = new JJBABotMessage();
            Duke duke = new Duke(bot);
            duke.handleCommandFeedback(c, cf);
            assertEquals(Console.formatMessage(bot.getAddMessage(cf.taskList, cf.task)) +
                    Console.lineFormat + System.lineSeparator(), outContent.toString());

        } catch (InvalidArgumentException e) {
            fail("Unexpected exception thrown!");
        }
    }
}