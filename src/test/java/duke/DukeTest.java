package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.bot.BotMessage;
import duke.bot.JjbaBotMessage;
import duke.command.Command;
import duke.command.CommandFeedback;
import duke.command.add.TodoCommand;
import duke.exception.InvalidArgumentException;
import duke.task.TaskList;
import duke.task.Todo;



class DukeTest {

    @Test
    public void handleCommandFeedback_todoTask_success() {
        try {
            Command c = new TodoCommand(new Todo("todo testing"));
            CommandFeedback cf = c.execute(new TaskList());
            BotMessage bot = new JjbaBotMessage();
            Duke duke = new Duke(bot);
            String output = duke.handleCommandFeedback(c, cf);
            assertEquals(bot.getAddMessage(cf.taskList, cf.task), output);

        } catch (InvalidArgumentException e) {
            fail("Unexpected exception thrown!");
        }
    }
}
