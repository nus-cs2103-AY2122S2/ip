package com.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.duke.modules.Storage;
import com.duke.modules.TaskList;

public class CommandAddTest {
    @Test
    public void executeExceptionTest() {
        TaskList taskList = new TaskList(Storage.getInstance());
        CommandAdd addCommand = new CommandAdd(
                "deadline", "project /by 20/12/2022 2359", taskList);
        assertEquals(addCommand.execute().getResultMessage(),
                "Got it. I've added this task:"
                        + "\n\t[D][ ] project (by: Dec 20 2022 11:59PM)\n"
                        + "Now you have 1 task(s) in the list");

        //Invalid date format
        addCommand = new CommandAdd(
                "deadline", "project /by tomorrow 5th jan", taskList);
        assertEquals(addCommand.execute().getResultMessage(),
                "Valid date and time must be given in this format: DD/MM/YYYY HHmm"
                        + "\nEg: 24/12/2022 2359");

        //Invalid description
        addCommand = new CommandAdd(
                "deadline", "/by tomorrow 20/12/2022", taskList);
        assertEquals(addCommand.execute().getResultMessage(),
                "☹ OOPS!!! The description/date of a deadline cannot be empty.");

        addCommand = new CommandAdd(
                "todo", "", taskList);
        assertEquals(addCommand.execute().getResultMessage(),
                "☹ OOPS!!! The description of a todo cannot be empty.");

        addCommand = new CommandAdd(
                "event", "/at school", taskList);
        assertEquals(addCommand.execute().getResultMessage(),
                "☹ OOPS!!! The description/location of a event cannot be empty.");

        //Invalid location
        addCommand = new CommandAdd(
                "event", "project /at", taskList);
        assertEquals(addCommand.execute().getResultMessage(),
                "☹ OOPS!!! The description/location of a event cannot be empty.");
    }

    @AfterAll
    public static void deleteFile() {
        Storage testStorage = Storage.getInstance();
        File file = new File(testStorage.getDirectoryPath() + "listData.txt");
        if (file.exists()) {
            file.delete();
        }
    }
}
