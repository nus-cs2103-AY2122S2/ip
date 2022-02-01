package com.duke.command;

import com.duke.modules.Storage;
import com.duke.modules.TaskList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandDeleteTest {
    @Test
    public void validArgumentTest() {
        TaskList taskList = new TaskList(Storage.getInstance());

        CommandAdd addCommand = new CommandAdd("todo", "groceries", taskList);
        addCommand.execute();
        CommandDelete deleteCommand = new CommandDelete("1", taskList);
        assertEquals(deleteCommand.execute().getResultMessage(),
                "Noted. I've removed this task:\n" +
                "\t[T][ ] groceries");
    }

    @Test
    public void notNumericArgumentTest() {
        TaskList taskList = new TaskList(Storage.getInstance());

        CommandAdd addCommand = new CommandAdd("todo", "groceries", taskList);
        addCommand.execute();
        CommandDelete deleteCommand = new CommandDelete("a", taskList);
        assertEquals(deleteCommand.execute().getResultMessage(),
                "Valid numerical number must be given:" + "\nEg: delete 12");
    }

    @Test
    public void notWithinListRangeArgumentTest() {
        TaskList taskList = new TaskList(Storage.getInstance());

        CommandAdd addCommand = new CommandAdd("todo", "groceries", taskList);
        addCommand.execute();
        CommandDelete deleteCommand = new CommandDelete("5", taskList);
        assertEquals(deleteCommand.execute().getResultMessage(),
                "Invalid task chosen, please ensure that the number given is correct");
    }

    @Test
    public void emptyArgumentTest() {
        TaskList taskList = new TaskList(Storage.getInstance());

        CommandAdd addCommand = new CommandAdd("todo", "groceries", taskList);
        addCommand.execute();
        CommandDelete deleteCommand = new CommandDelete("", taskList);
        assertEquals(deleteCommand.execute().getResultMessage(),
                "Please choose which task you would like to delete");
    }

    @Test
    public void emptyListTest() {
        TaskList taskList = new TaskList(Storage.getInstance());

        CommandDelete deleteCommand = new CommandDelete("1", taskList);
        assertEquals(deleteCommand.execute().getResultMessage(),
                "Your list is empty.");
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