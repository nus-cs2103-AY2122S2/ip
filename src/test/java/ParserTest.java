import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {

    @Test
    public void parseToTask_emptyInput_null() throws Exception{
        assertNull(Parser.parseToTask(""));
    }

    @Test
    public void parseToTask_invalidTaskType_null() throws Exception{
        assertNull(Parser.parseToTask("anyinput TaskName /by 11/11/2011 1500"));
        assertNull(Parser.parseToTask("anyinput TaskName /at 11/11/2011 1500"));
    }

    @Test
    public void parseToTask_emptyTaskName_exceptionThrown(){
        try{
            Task task = Parser.parseToTask("todo ");
        } catch (DukeException e){
            assertEquals("Todo Name is empty!",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("deadline  /by 11/11/2011 1500");
        } catch (DukeException e){
            assertEquals("Deadline Name is empty!",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("event  /at 11/11/2011 1500");
        } catch (DukeException e){
            assertEquals("Event Name is empty!",e.getMessage());
        }
    }

    @Test
    public void parseToTask_emptyDate_exceptionThrown(){
        try{
            Task task = Parser.parseToTask("deadline name /by ");
        } catch (DukeException e){
            assertEquals("No Date Specified!",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("deadline name /by");
        } catch (DukeException e){
            assertEquals("Wrong format entered! Please enter <Deadline Name> /at <Deadline>",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("event name /at");
        } catch (DukeException e){
            assertEquals("Wrong format entered! Please enter <Event Name> /at <Event Date>",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("event name /at ");
        } catch (DukeException e){
            assertEquals("No Date Specified!",e.getMessage());
        }
    }

    @Test
    public void parseToTask_wrongDateFormat_exceptionThrown(){
        try{
            Task task = Parser.parseToTask("deadline name /by 11112011 1500");
        } catch (DukeException e){
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("deadline name /by 11-11-2011 1500");
        } catch (DukeException e){
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("deadline name /by 11/11/2011 15:00");
        } catch (DukeException e){
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("deadline name /by 11/11/11 1500");
        } catch (DukeException e){
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",e.getMessage());
        }

    }

    @Test
    public void parseToTask_wrongSeparator_exceptionThrown(){
        try{
            Task task = Parser.parseToTask("deadline name /at 11/11/2011 1500");
        } catch (DukeException e){
            assertEquals("Wrong format entered! Please enter <Deadline Name> /at <Deadline>",e.getMessage());
        }

        try{
            Task task = Parser.parseToTask("event name /by 11/11/2011 1500");
        } catch (DukeException e){
            assertEquals("Wrong format entered! Please enter <Event Name> /at <Event Date>",e.getMessage());
        }
    }
}
