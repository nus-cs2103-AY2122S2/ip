package com.duke.command;

import com.duke.modules.Storage;
import com.duke.modules.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandByeTest {
    @Test
    public void executeSingleWordTest() {
        CommandBye byeCommand = new CommandBye("");
        assertEquals(byeCommand.execute().getResultMessage(),
                "LUMU: Goodbye. Hope to see you again soon");
    }

    @Test
    public void executeMultipleWordTest() {
        CommandBye byeCommand = new CommandBye("sdlffsdflsd fsdfdsf");
        assertEquals(byeCommand.execute().getResultMessage(),
                "A bye command should not be succeeded by any words. "
                        + "Did you meant to do something else?");
    }
}
