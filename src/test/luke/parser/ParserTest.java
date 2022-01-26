package luke.parser;

import luke.commands.Command;
import luke.data.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    private static final String[] DESCRIPTIONS = {"eat", "sleep", "code"};
    private static final String[] DATES = {"01/01/2022", "02/02/2022", "03/03/2022"};
    private static final String[] TIMES = {"06:00", "12:00", "18:00", "23:59"};

    @Test
    public void deadlineCommand_validInput_correctCommandResult(){
        String successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        for (int i = 0; i < DESCRIPTIONS.length; i++) {
            for (int j = 0; j < DATES.length; j++) {
                for (int k = 0; k < TIMES.length; k++) {
                    String description = DESCRIPTIONS[i];
                    String datetime = String.format("%s %s", DATES[j],TIMES[k]);
                    String commandString = String.format("deadline %s /by %s", description,datetime);
                    Command command = Parser.parse(commandString);
                    String expectedCommandResult = String.format("[D][ ] %s (by: %s)",
                            description, DateTimeParser.toString(DateTimeParser.toLocalDateTime(datetime)));
                    String expected = String.format(successMessage, expectedCommandResult, 1);
                    assertEquals(expected, command.execute(new TaskList()).getResult());
                }
            }
        }
    }

    @Test
    public void eventCommand_invalidArgument_incorrectCommandResult(){
        String commandString = String.format("event %s", DESCRIPTIONS[0]);
        Command command = Parser.parse(commandString);
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expected = String.format(errorMsg, "event require the at argument.");
        assertEquals(expected, command.execute(new TaskList()).getResult());

        commandString = "event";
        command = Parser.parse(commandString);
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "The description of event cannot be empty.");
        assertEquals(expected, command.execute(new TaskList()).getResult());

        commandString = String.format("event %s /at Sunday noon", DESCRIPTIONS[0]);
        command = Parser.parse(commandString);
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "The force does not comprehend the date.");
        assertEquals(expected, command.execute(new TaskList()).getResult());
    }

    @Test
    public void unmarkCommand_invalidInput_incorrectCommandResult(){
        String commandString = "unmark 1";
        String expected = "The force cannot find the task.\nPlease try again :(";
        Command command = Parser.parse(commandString);
        assertEquals(expected, command.execute(new TaskList()).getResult());

        commandString = "unmark abcdef";
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "The force cannot convert the value to a number.");
        command = Parser.parse(commandString);
        assertEquals(expected, command.execute(new TaskList()).getResult());
    }

    @Test
    public void deleteCommand_validInput_correctCommandResult(){
        String commandString = String.format("todo %s", DESCRIPTIONS[0]);
        Command command = Parser.parse(commandString);
        TaskList taskList = new TaskList();
        command.execute(taskList);
        String expected = "Forcing it out... Success! I've removed the following task:\n\t[T][ ] %s";
        expected = String.format(expected, DESCRIPTIONS[0]);
        commandString = String.format("delete 1");
        command = Parser.parse(commandString);
        assertEquals(expected, command.execute(taskList).getResult());
    }

    @Test
    public void markCommand_validInput_correctCommandResult(){
        String commandString = String.format("todo %s", DESCRIPTIONS[0]);
        Command command = Parser.parse(commandString);
        TaskList taskList = new TaskList();
        command.execute(taskList);

        commandString = String.format("mark 1");
        String expected = "Using the force... Great! I have forced this task as done.";
        expected = String.format(expected, taskList.get(0));
        command = Parser.parse(commandString);
        assertEquals(expected, command.execute(taskList).getResult());

        commandString = String.format("list");
        expected = String.format("Currently, you have the following tasks:\n\t1. [T][X] %s", DESCRIPTIONS[0]);
        command = Parser.parse(commandString);
        assertEquals(expected, command.execute(taskList).getResult());
    }
}