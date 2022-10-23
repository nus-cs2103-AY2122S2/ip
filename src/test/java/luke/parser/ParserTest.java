package luke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import luke.commands.Command;
import luke.data.TaskList;

class ParserTest {
    private static final String[] DESCRIPTIONS = {"eat", "sleep", "code"};
    private static final String[] FILLERS = {"dinner", "lunch", "project"};
    private static final String[] DATES = {"01/01/2022", "02/02/2022", "03/03/2022"};
    private static final String[] TIMES = {"06:00", "1200", "1800", "23:59"};

    @Test
    public void deadlineCommand_validInput_correctCommandResult() {
        String successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        for (int i = 0; i < DESCRIPTIONS.length; i++) {
            for (int j = 0; j < DATES.length; j++) {
                for (int k = 0; k < TIMES.length; k++) {
                    String description = DESCRIPTIONS[i];
                    String datetime = String.format("%s %s", DATES[j], TIMES[k]);
                    String commandString = String.format("deadline %s /by %s", description, datetime);
                    String expectedCommandResult = String.format("[D][ ] %s\n\t(by: %s)",
                            description, DateTimeParser.toString(DateTimeParser.toLocalDateTime(datetime)));
                    String expected = String.format(successMessage, expectedCommandResult, 1);
                    testCommand(commandString, expected, new TaskList());
                }
            }
        }
    }

    @Test
    public void deadlineCommand_invalidArgument_incorrectCommandResult() {
        String commandString = String.format("deadline %s", DESCRIPTIONS[0]);
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expected = String.format(errorMsg, "deadline command requires the by argument.");
        testCommand(commandString, expected, new TaskList());

        commandString = "deadline";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "deadline has invalid number of arguments.");
        testCommand(commandString, expected, new TaskList());

        commandString = String.format("deadline %s /by tmr", DESCRIPTIONS[0]);
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expectedErrorMsg = String.format("The force does not comprehend the date:\n%s",
                "Text 'tmr' could not be parsed, unparsed text found at index 0");
        expected = String.format(errorMsg, expectedErrorMsg);
        testCommand(commandString, expected, new TaskList());

        commandString = "deadline /today";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "deadline command requires the description argument.");
        testCommand(commandString, expected, new TaskList());

        commandString = "deadline sleep /today";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "deadline command requires the by argument.");
        testCommand(commandString, expected, new TaskList());
    }

    @Test
    public void todoCommand_validArgument_correctCommandResult() {
        String successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        String commandString = "todo this work";
        String expectedCommandResult = "[T][ ] this work";
        String expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, new TaskList());

        commandString = "todo this should work too /not this";
        expectedCommandResult = "[T][ ] this should work too";
        expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, new TaskList());
    }


    @Test
    public void todoCommand_invalidArgument_incorrectCommandResult() {
        String commandString = "todo";
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expected = String.format(errorMsg, "todo has invalid number of arguments.");
        testCommand(commandString, expected, new TaskList());

        commandString = "todo /this does not work";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "todo command requires the description argument.");
        testCommand(commandString, expected, new TaskList());
    }

    @Test
    public void eventCommand_invalidArgument_incorrectCommandResult() {
        String commandString = String.format("event %s", DESCRIPTIONS[0]);
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expected = String.format(errorMsg, "event command requires the at argument.");
        testCommand(commandString, expected, new TaskList());

        commandString = "event";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "event has invalid number of arguments.");
        testCommand(commandString, expected, new TaskList());

        commandString = String.format("event %s /at Sunday noon", DESCRIPTIONS[0]);
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expectedErrorMsg = String.format("The force does not comprehend the date:\n%s",
            "Text 'Sunday noon' could not be parsed, unparsed text found at index 0");
        expected = String.format(errorMsg, expectedErrorMsg);
        testCommand(commandString, expected, new TaskList());

        commandString = "event /at Sunday noon";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "event command requires the description argument.");
        testCommand(commandString, expected, new TaskList());
    }

    @Test
    public void unmarkCommand_invalidInput_incorrectCommandResult() {
        String commandString = "unmark 1";
        String expected = "The force cannot find the task.\nPlease try again :(";
        testCommand(commandString, expected, new TaskList());

        commandString = "unmark abcdef";
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "The force cannot convert the value to a number.");
        testCommand(commandString, expected, new TaskList());
    }

    @Test
    public void deleteCommand_validInput_correctCommandResult() {
        String commandString = String.format("todo %s", DESCRIPTIONS[0]);
        Command command = Parser.parse(commandString);
        TaskList taskList = new TaskList();
        command.execute(taskList);
        String expected = "Forcing it out... Success! I've removed the following task:\n\t[T][ ] %s";
        expected = String.format(expected, DESCRIPTIONS[0]);
        commandString = String.format("delete 1");
        testCommand(commandString, expected, taskList);
    }

    @Test
    public void markCommand_validInput_correctCommandResult() {
        String commandString = String.format("todo %s", DESCRIPTIONS[0]);
        Command command = Parser.parse(commandString);
        TaskList taskList = new TaskList();
        command.execute(taskList);

        commandString = String.format("mark 1");
        String expected = "Using the force... Great! I have forced this task as done.";
        expected = String.format(expected, taskList.get(0));
        testCommand(commandString, expected, taskList);

        commandString = String.format("list");
        expected = String.format("Currently, you have the following tasks:\n\t1. [T][X] %s", DESCRIPTIONS[0]);
        testCommand(commandString, expected, taskList);
    }

    @Test
    public void findCommand_validInput_correctCommandResult() {
        TaskList taskList = new TaskList();

        String commandString = String.format("find %s", DESCRIPTIONS[0]);
        Command command = Parser.parse(commandString);
        command.execute(taskList);

        String expected = "The force is unable to find any task with the keyword...\n"
                + "The keyword parsed is \"%s\".";
        expected = String.format(expected, DESCRIPTIONS[0]);
        testCommand(commandString, expected, taskList);

        commandString = String.format("todo %s %s", DESCRIPTIONS[0], FILLERS[0]);
        command = Parser.parse(commandString);
        command.execute(taskList);

        commandString = String.format("todo %s %s", DESCRIPTIONS[2], FILLERS[2]);
        command = Parser.parse(commandString);
        command.execute(taskList);

        commandString = String.format("todo %s %s", DESCRIPTIONS[0], FILLERS[1]);
        command = Parser.parse(commandString);
        command.execute(taskList);

        commandString = String.format("find %s", DESCRIPTIONS[0]);
        expected = "The force found the following matching tasks:\n\t1. [T][ ] %s %s\n\t3. [T][ ] %s %s";
        expected = String.format(expected, DESCRIPTIONS[0], FILLERS[0], DESCRIPTIONS[0], FILLERS[1]);
        testCommand(commandString, expected, taskList);

        commandString = String.format("list");
        expected = "Currently, you have the following tasks:\n\t1. [T][ ] %s %s\n\t2. [T][ ] %s %s\n\t3. [T][ ] %s %s";
        expected = String.format(expected, DESCRIPTIONS[0], FILLERS[0],
                DESCRIPTIONS[2], FILLERS[2], DESCRIPTIONS[0], FILLERS[1]);
        testCommand(commandString, expected, taskList);
    }

    @Test
    public void findCommand_invalidInput_incorrectCommandResult() {
        String commandString = "find";
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expected = String.format(errorMsg, "find command requires a keyword argument.");

        testCommand(commandString, expected, new TaskList());
    }

    @Test
    public void recurCommand_invalidInput_incorrectCommandResult() {
        String commandString = "recur";
        String errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        String expected = String.format(errorMsg, "recur has invalid number of arguments.");
        testCommand(commandString, expected, new TaskList());

        commandString = "recur event sleep /at 12/12/2022 0000";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "recur command requires the every argument.");
        testCommand(commandString, expected, new TaskList());

        commandString = "recur event sleep /at 12/12/2022 0000 /every second";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        errorMsg = String.format(errorMsg, "The force does not comprehend the date:\n%s");
        expected = String.format(errorMsg, "\"second\" is not a valid argument for every.");
        testCommand(commandString, expected, new TaskList());

        commandString = "recur /every day";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        expected = String.format(errorMsg, "recur command requires the task argument.");
        testCommand(commandString, expected, new TaskList());

        commandString = "recur eat sleep code /every day";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        errorMsg = String.format(errorMsg, "recur command encountered an error:\n%s");
        expected = String.format(errorMsg, "Command not supported.");
        testCommand(commandString, expected, new TaskList());

        commandString = "recur mark 1 /every day";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        errorMsg = String.format(errorMsg, "recur command encountered an error:\n%s");
        expected = String.format(errorMsg, "Command not supported.");
        testCommand(commandString, expected, new TaskList());

        commandString = "recur todo /every day";
        errorMsg = "Oops, the force has encountered an error:\n%s\nPlease try again :(";
        errorMsg = String.format(errorMsg, "recur command encountered an error:\n%s");
        expected = String.format(errorMsg, "todo command requires the description argument.");
        testCommand(commandString, expected, new TaskList());
    }

    @Test
    public void recurCommandAdd_validInput_correctCommandResult() {
        LocalDateTime currentDateTimePlusOne = LocalDateTime.now().plusDays(1);
        String dateTimeCommandString = DateTimeParser.toCommandString(currentDateTimePlusOne);
        String dateTimeString = DateTimeParser.toString(currentDateTimePlusOne);
        String commandString = String.format("recur event sleep /at %s /every day", dateTimeCommandString);
        String successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        String expectedCommandResult = String.format("[R][E][ ] sleep\n\t(at: %s) (every: day)", dateTimeString);
        String expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, new TaskList());

        LocalDateTime currentDateTimeMinusOne = LocalDateTime.now().minusDays(1);

        LocalDateTime expectedDateTime = currentDateTimeMinusOne.plusWeeks(1);
        dateTimeCommandString = DateTimeParser.toCommandString(currentDateTimeMinusOne);
        dateTimeString = DateTimeParser.toString(expectedDateTime);
        commandString = String.format("recur event sleep /at %s /every week", dateTimeCommandString);
        successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        expectedCommandResult = String.format("[R][E][ ] sleep\n\t(at: %s) (every: week)", dateTimeString);
        expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, new TaskList());

        expectedDateTime = currentDateTimeMinusOne.plusMonths(1);
        dateTimeCommandString = DateTimeParser.toCommandString(currentDateTimeMinusOne);
        dateTimeString = DateTimeParser.toString(expectedDateTime);
        commandString = String.format("recur event sleep /at %s /every month", dateTimeCommandString);
        successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        expectedCommandResult = String.format("[R][E][ ] sleep\n\t(at: %s) (every: month)", dateTimeString);
        expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, new TaskList());

        commandString = String.format("recur todo teach /every month", dateTimeCommandString);
        successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        expectedCommandResult = String.format("[R][T][ ] teach (every: month)", dateTimeString);
        expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, new TaskList());

        expectedDateTime = currentDateTimeMinusOne.plusYears(1);
        dateTimeCommandString = DateTimeParser.toCommandString(currentDateTimeMinusOne);
        dateTimeString = DateTimeParser.toString(expectedDateTime);
        commandString = String.format("recur deadline sleep /by %s /every year", dateTimeCommandString);
        successMessage = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
        expectedCommandResult = String.format("[R][D][ ] sleep\n\t(by: %s) (every: year)", dateTimeString);
        expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, new TaskList());
    }

    @Test
    public void recurCommand_markTaskAsDone_correctCommandResult() {
        TaskList tasklist = new TaskList();
        LocalDateTime currentDateTime = LocalDateTime.now().plusMinutes(1);
        String dateTimeCommandString = DateTimeParser.toCommandString(currentDateTime);
        String dateTimeString = DateTimeParser.toString(currentDateTime);
        String commandString = String.format("recur event sleep /at %s /every day", dateTimeCommandString);
        Command command = Parser.parse(commandString);
        command.execute(tasklist);
        commandString = "mark 1";
        command = Parser.parse(commandString);
        command.execute(tasklist);

        commandString = "list";
        String successMessage = "Currently, you have the following tasks:\n\t%s";
        String expectedCommandResult = String.format("1. [R][E][X] sleep\n\t(at: %s) (every: day)", dateTimeString);
        String expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, tasklist);
    }

    @Test
    public void recurCommand_todoLoadFromStorageMark_markRemoved() {
        String storedData = "recur todo homework /every day /next 13/02/2022 04:01 | 1";
        TaskList tasklist = new TaskList();
        String[] inputs = storedData.split("\\|");
        String commandString = inputs[0];
        Command command = Parser.parse(commandString);
        command.execute(tasklist);
        tasklist.get(0).setDoneStatus(Integer.parseInt(inputs[1].strip()));

        commandString = "list";
        String successMessage = "Currently, you have the following tasks:\n\t%s";
        String expectedCommandResult = "1. [R][T][ ] homework (every: day)";
        String expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, tasklist);
    }

    @Test
    public void recurCommand_todoLoadFromStorageMark_markRemained() {
        String storedData = "recur todo homework /every day /next 13/02/2100 04:01 | 1";
        LocalDateTime expectedDate = DateTimeParser.toLocalDateTime("13/02/2100 04:01");
        TaskList tasklist = new TaskList();
        String[] inputs = storedData.split("\\|");
        String commandString = inputs[0];
        Command command = Parser.parse(commandString);
        command.execute(tasklist);
        tasklist.get(0).setDoneStatus(Integer.parseInt(inputs[1].strip()));

        commandString = "list";
        String successMessage = "Currently, you have the following tasks:\n\t%s";
        String expectedCommandResult = "1. [R][T][X] homework (every: day)";
        String expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, tasklist);
    }

    @Test
    public void recurCommand_eventLoadFromStorageMark_markRemained() {
        String storedData = "recur event sleep /at 14/02/2100 12:12 /every day /next 13/02/2022 00:00 | 1";
        LocalDateTime expectedDate = DateTimeParser.toLocalDateTime("14/02/2100 12:12");
        Function<LocalDateTime, LocalDateTime> recurFunction = DateTimeParser.getDateTimeIncrementFunction("day");
        while (expectedDate.isBefore(LocalDateTime.now())) {
            expectedDate = recurFunction.apply(expectedDate);
        }
        String dateTimeString = DateTimeParser.toString(expectedDate);
        TaskList tasklist = new TaskList();
        String[] inputs = storedData.split("\\|");
        String commandString = inputs[0];
        Command command = Parser.parse(commandString);
        command.execute(tasklist);
        tasklist.get(0).setDoneStatus(Integer.parseInt(inputs[1].strip()));

        commandString = "list";
        String successMessage = "Currently, you have the following tasks:\n\t%s";
        String expectedCommandResult = String.format("1. [R][E][X] sleep\n\t(at: %s) (every: day)", dateTimeString);
        String expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, tasklist);
    }

    @Test
    public void recurCommand_eventLoadFromStorageMark_markRemoved() {
        String storedData = "recur event sleep /at 13/02/2022 12:12 /every day /next 14/02/2100 03:47 | 1";
        LocalDateTime expectedDate = DateTimeParser.toLocalDateTime("13/02/2022 12:12");
        Function<LocalDateTime, LocalDateTime> recurFunction = DateTimeParser.getDateTimeIncrementFunction("day");
        while (expectedDate.isBefore(LocalDateTime.now())) {
            expectedDate = recurFunction.apply(expectedDate);
        }
        String dateTimeString = DateTimeParser.toString(expectedDate);
        TaskList tasklist = new TaskList();
        String[] inputs = storedData.split("\\|");
        String commandString = inputs[0];
        Command command = Parser.parse(commandString);
        command.execute(tasklist);
        tasklist.get(0).setDoneStatus(Integer.parseInt(inputs[1].strip()));

        commandString = "list";
        String successMessage = "Currently, you have the following tasks:\n\t%s";
        String expectedCommandResult = String.format("1. [R][E][ ] sleep\n\t(at: %s) (every: day)", dateTimeString);
        String expected = String.format(successMessage, expectedCommandResult, 1);
        testCommand(commandString, expected, tasklist);
    }



    private void testCommand(String commandString, String expectedOutput, TaskList taskList) {
        Command command = Parser.parse(commandString);
        assertEquals(expectedOutput, command.execute(taskList).getResult());
    }
}
