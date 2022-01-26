package duke.command;

import duke.Duke;
import duke.UI;
import duke.exception.DukeException;
import duke.task.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    @Test
    public void testDelete_valid_success(){
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new TodoTask("Test Title 1"));
        taskList.add(new TodoTask("Test Title 2"));
        taskList.add(new DeadlineTask("Test Title 3", "2022-01-01", "11:11"));
        taskList.add(new EventTask("Test Title 4", "2022-01-01", "11:11"));
        //Test deleting one of each type of task
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));
            new DeleteCommand("1").execute(taskList, ui);
            new DeleteCommand("2").execute(taskList, ui);
            new DeleteCommand("2").execute(taskList, ui);
            outStream.flush();
            String lines = outStream.toString();
            assertEquals("    ____________________________________________________________\n" +
                    "     Noted. I've removed this task:\n" +
                    "       [T][ ] Test Title 1\n" +
                    "     Now you have 3 tasks in the list.\n" +
                    "    ____________________________________________________________\n\n" +
                    "    ____________________________________________________________\n" +
                    "     Noted. I've removed this task:\n" +
                    "       [D][ ] Test Title 3 (by: 2022-01-01 11:11)\n" +
                    "     Now you have 2 tasks in the list.\n" +
                    "    ____________________________________________________________\n\n" +
                    "    ____________________________________________________________\n" +
                    "     Noted. I've removed this task:\n" +
                    "       [E][ ] Test Title 4 (at: 2022-01-01 11:11)\n" +
                    "     Now you have 1 task in the list.\n" +
                    "    ____________________________________________________________\n\n", lines);

        } catch (DukeException | IOException e){
            fail();
        }
    }

    @Test
    public void testDelete_invalidInput_exceptionThrown(){
        UI ui =new UI();
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new TodoTask("Test Title 1"));
        taskList.add(new TodoTask("Test Title 2"));
        taskList.add(new DeadlineTask("Test Title 3", "2022-01-01", "11:11"));
        taskList.add(new EventTask("Test Title 4", "2022-01-01", "11:11"));

        //if user tries to delete nothing
        try{
            new DeleteCommand("");
            fail();
        } catch (DukeException e){
            assertEquals("OOPS!!! Task to delete cannot be empty:(", e.getMessage());
        }
        //if user tries to enter something that is not a number
        try{
            new DeleteCommand("invalid");
            fail();
        } catch (DukeException e){
            assertEquals("OOPS!!! Invalid task number, "
                    + "please select a valid task to delete using the task's number", e.getMessage());
        }
        //if user tries to delete a number out of the range of the list
        try{
            new DeleteCommand("8").execute(taskList, ui);
        } catch(DukeException e){
            assertEquals("OOPS!!! Invalid task number, "
                    + "please select a valid task to delete using the task's number", e.getMessage());
        }
        //if user tries to delete a number below 1
        try{
            new DeleteCommand("-2").execute(taskList, ui);
        } catch(DukeException e){
            assertEquals("OOPS!!! Invalid task number, "
                    + "please select a valid task to delete using the task's number", e.getMessage());
        }
    }
}
