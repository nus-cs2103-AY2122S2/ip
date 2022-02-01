package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;
//import java.beans.Transient;

public class PikachuTest {
    /**
     * Marks a task as completed and checks if Pikachu marks it correctly.
     */
    @Test
    public void markTest() {
        Pikachu pikachu = new Pikachu();
        ArrayList<duke.tasks.Task> currInputList = pikachu.getInputList();
        pikachu.parseInput("todo Sample");
        pikachu.parseInput("mark 1");
        assertTrue(currInputList.get(0).getStatus());
    }

    /**
     * Creates a todo, deadline and event task and checks if Pikachu stores it accurately.
     */
    @Test
    public void toStringTest() {
        Pikachu pikachu = new Pikachu();
        ArrayList<duke.tasks.Task> currInputList = pikachu.getInputList();

        //ToDo task
        pikachu.parseInput("Todo 1");
        assertEquals(currInputList.get(0).toString(), "[T][ ] 1");
        //Deadline task
        pikachu.parseInput("Deadline 2 /1999-12-12 2359");
        assertEquals(currInputList.get(1).toString(), "[D][ ] 2 (By 12-12-1999 23:59)");
        //Event task
        pikachu.parseInput("Event 3 /1999-12-12 2358 1999-12-12 2359");
        assertEquals(currInputList.get(2).toString(), "[E][ ] 3 (From 12-12-1999 23:58 to 12-12-1999 23:59)");
    }

    /**
     * Enters invalid inputs and checks if Pikachu returns the correct error message.
     */
    @Test
    public void invalidInputTest () throws IOException {
        //Sets up output stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Pikachu pikachu = new Pikachu();

        //Testing un-parsable input
        pikachu.parseInput("pikachu you're stupid");
        assertEquals("Pikachu does not understand...", outContent.toString().trim());
        outContent.reset();

        //Testing creating a todo task with no taskname
        pikachu.parseInput("Todo");
        assertEquals("Task description is empty!", outContent.toString().trim());
        outContent.reset();

        //Testing creating an event where start-time is after end-time
        pikachu.parseInput("Event impossible /1999-12-12 2359 1999-11-11 2359");
        assertEquals("End time cannot be earlier than start time!", outContent.toString().trim());
        outContent.reset();

        //Restores output stream
        System.setOut(System.out);
    }
    
}