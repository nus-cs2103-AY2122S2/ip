package chatbot.util;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private static final String[] todoTitleArgs = {"todo", "sometodo"};
    private static final String[] deadlineTitleArgs = {"deadline", "somedeadline"};
    private static final String[] deadlineOtherArgs = {"by", "24/04/2024"};
    private static final String[] eventTitleArgs = {"event", "someevent"};
    private static final String[] eventOtherArgs = {"at", "23/03/2023 1800"};

    @Test
    public void testAddTodo() throws ChatBotException {
        TaskList taskList = taskListWithJustOneTodo();

        assertEquals(1, taskList.getNumTasks());
        assertEquals("sometodo", taskList.getTask(0).getTitle());
        assertEquals("T", taskList.getTask(0).getType());
        assertNull(taskList.getTask(0).getTimestamp());

        ChatBotException thrown = assertThrows(ChatBotException.class, () ->
            taskList.addToDo(new String[]{"todo"})
        );
        assertEquals("You need to key in the title of your todo traveller!", thrown.getMessage());
    }

    @Test
    public void testAddDeadline() throws ChatBotException {
        TaskList taskList = taskListWithJustOneDeadline();

        assertEquals(1, taskList.getNumTasks());
        assertEquals("somedeadline", taskList.getTask(0).getTitle());
        assertEquals("D", taskList.getTask(0).getType());
        assertEquals(new Timestamp("24/04/2024"), taskList.getTask(0).getTimestamp());

        ChatBotException thrown;
        thrown = assertThrows(ChatBotException.class, () ->
            taskList.add(new String[]{"deadline"}, new String[]{"by", "24/04/2024"})
        );
        assertEquals("You need to key in the title of your deadline traveller!", thrown.getMessage());

        thrown = assertThrows(ChatBotException.class, () ->
            taskList.add(new String[]{"deadline", "somedeadline2"}, new String[]{"by"})
        );
        assertEquals("You need to key in the due date and time of your deadline traveller!",
                thrown.getMessage());

        thrown = assertThrows(ChatBotException.class, () ->
            taskList.add(new String[]{"deadline", "somedeadline2"}, new String[]{"at", "24/04/2024"})
        );
        assertEquals("The correct format for adding a deadline is "
                        + "deadline <name of task> /by <date or timestamp of task>",
                thrown.getMessage());
    }

    @Test
    public void testAddEvent() throws ChatBotException {
        TaskList taskList = taskListWithJustOneEvent();

        assertEquals(1, taskList.getNumTasks());
        assertEquals("someevent", taskList.getTask(0).getTitle());
        assertEquals("E", taskList.getTask(0).getType());
        assertEquals(new Timestamp("23/03/2023 1800"), taskList.getTask(0).getTimestamp());

        ChatBotException thrown;
        thrown = assertThrows(ChatBotException.class, () ->
            taskList.add(new String[]{"event"}, new String[]{"by", "24/04/2024"})
        );
        assertEquals("You need to key in the title of your event traveller!", thrown.getMessage());

        thrown = assertThrows(ChatBotException.class, () ->
            taskList.add(new String[]{"event", "someevent2"}, new String[]{"at"})
        );
        assertEquals("You need to key in the timestamp of your event traveller!", thrown.getMessage());

        thrown = assertThrows(ChatBotException.class, () ->
            taskList.add(new String[]{"event", "someevent2"}, new String[]{"by", "23/03/2023 1800"})
        );
        assertEquals("The correct format for adding an event is event <name of task> /at <date or timestamp of task>",
                thrown.getMessage());
    }

    @Test
    public void testDelete() throws ChatBotException {
        TaskList taskList = taskListWithJustOneTodo();
        taskList.delete(0);

        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testGetTasksOnDate() throws ChatBotException {
        TaskList taskList = fullTaskList();

        String deadlineActual = taskList.getTasksOnDate(new Timestamp("24/04/2024"));
        String deadlineExpected =
                "             1. [D][ ] somedeadline (by: 24 April 2024)".concat(System.lineSeparator());

        String eventActual = taskList.getTasksOnDate(new Timestamp("23/03/2023"));
        String eventExpected =
                "             1. [E][ ] someevent (at: 23 March 2023, 6:00 PM)".concat(System.lineSeparator());

        String falseActual = taskList.getTasksOnDate(new Timestamp("01/01/2021"));
        String falseExpected = "You have no tasks on this date traveller!";

        assertEquals(deadlineExpected, deadlineActual);
        assertEquals(eventExpected, eventActual);
        assertEquals(falseExpected, falseActual);
    }

    @Test
    public void testMarkAndUnmark() throws ChatBotException {
        TaskList taskList = taskListWithJustOneTodo();
        taskList.mark(0);
        assertEquals("X", taskList.getTask(0).getDone());
        taskList.unmark(0);
        assertEquals(" ", taskList.getTask(0).getDone());
    }

    @Test
    public void testSaveAndLoad() throws ChatBotException {
        TaskList taskList = taskListWithJustOneTodo();
        Storage storage = new Storage("../../data", "data.txt");

        storage.saveChanges(taskList);
        taskList = new TaskList();
        assertTrue(taskList.isEmpty());

        storage.loadData(taskList);
        assertEquals(1, taskList.getNumTasks());
        assertEquals("sometodo", taskList.getTask(0).getTitle());
        assertEquals("T", taskList.getTask(0).getType());
        assertNull(taskList.getTask(0).getTimestamp());
    }


    private TaskList taskListWithJustOneTodo() throws ChatBotException {
        TaskList taskList = new TaskList();
        taskList.addToDo(todoTitleArgs);
        return taskList;
    }

    private TaskList taskListWithJustOneDeadline() throws ChatBotException {
        TaskList taskList = new TaskList();
        taskList.add(deadlineTitleArgs, deadlineOtherArgs);
        return taskList;
    }

    private TaskList taskListWithJustOneEvent() throws ChatBotException {
        TaskList taskList = new TaskList();
        taskList.add(eventTitleArgs, eventOtherArgs);
        return taskList;
    }

    private TaskList fullTaskList() throws ChatBotException {
        TaskList taskList = new TaskList();
        taskList.addToDo(todoTitleArgs);
        taskList.add(deadlineTitleArgs, deadlineOtherArgs);
        taskList.add(eventTitleArgs, eventOtherArgs);
        return taskList;
    }
}

