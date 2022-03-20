package duke.task;

import static duke.commons.core.Messages.MESSAGE_ADD_TASK;
import static duke.commons.core.Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;
import static duke.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static duke.commons.core.Messages.MESSAGE_MARK_TASK_DONE;
import static duke.commons.core.Messages.MESSAGE_MARK_TASK_UNDONE;
import static duke.commons.core.Messages.MESSAGE_REMOVE_TASK_DONE;
import static duke.task.util.TaskListTestUtil.DELETE_FARAWAY_TASK_VALID;
import static duke.task.util.TaskListTestUtil.DELETE_FIRST_TASK_VALID;
import static duke.task.util.TaskListTestUtil.MAKE_DEADLINE_EMPTY_DESC_INVALID;
import static duke.task.util.TaskListTestUtil.MAKE_DEADLINE_INPUT_VALID;
import static duke.task.util.TaskListTestUtil.MAKE_EVENT_INPUT_VALID;
import static duke.task.util.TaskListTestUtil.MAKE_TODO_INPUT_INVALID;
import static duke.task.util.TaskListTestUtil.MAKE_TODO_INPUT_VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.ResponseFormatter;

class TaskListTest {
    private List<Task> tasksStub;
    private TaskList taskListSut;

    private Task todoStub;
    private Task deadlineStub;
    private Task eventStub;

    @BeforeEach
    void init() {
        tasksStub = new ArrayList<>();
        taskListSut = new TaskList();

        todoStub = new Todo(false, "homework");
        deadlineStub = new Deadline(false, "assignment", " 05/03/2022 2359");
        eventStub = new Event(false, "wedding", " 11/12/2022 1800");
    }

    @AfterEach
    void teardown() {
        tasksStub.clear();
    }

    @Test
    void getTasks_init_success() {
        tasksStub.add(todoStub);
        tasksStub.add(deadlineStub);
        tasksStub.add(eventStub);
        assertEquals(tasksStub, new TaskList(tasksStub).getTasks());
    }

    @Test
    void processNewTask_todo_success() {
        tasksStub.add(todoStub);
        assertEquals(ResponseFormatter.printFeedbackFooter(MESSAGE_ADD_TASK,
                todoStub, tasksStub), taskListSut.processNewTask(todoStub));
    }

    @Test
    void processNewTask_deadline_success() {
        tasksStub.add(deadlineStub);
        assertEquals(ResponseFormatter.printFeedbackFooter(MESSAGE_ADD_TASK,
                deadlineStub, tasksStub), taskListSut.processNewTask(deadlineStub));
    }

    @Test
    void processNewTask_event_success() {
        tasksStub.add(eventStub);
        assertEquals(ResponseFormatter.printFeedbackFooter(MESSAGE_ADD_TASK,
                eventStub, tasksStub), taskListSut.processNewTask(eventStub));
    }

    @Test
    void handleMark_todo_success() {
        String[] markInputArrayStub = {"mark", "1"};
        todoStub.setDone();
        tasksStub.add(todoStub);
        taskListSut.getTasks().add(todoStub);

        assertEquals(ResponseFormatter.printFeedbackFooter(MESSAGE_MARK_TASK_DONE, todoStub, tasksStub),
                taskListSut.handleMark(markInputArrayStub));
    }

    @Test
    void handleUnMark_todo_success() {
        String[] unMarkInputArrayStub = {"unmark", "1"};
        todoStub.setUndone();
        tasksStub.add(todoStub);
        taskListSut.getTasks().add(todoStub);

        assertEquals(ResponseFormatter.printFeedbackFooter(MESSAGE_MARK_TASK_UNDONE, todoStub, tasksStub),
                taskListSut.handleUnMark(unMarkInputArrayStub));
    }

    @Test
    void handleTodo_todo_success() {
        Parser parser = new Parser(MAKE_TODO_INPUT_VALID);
        String[] inputArray = parser.getInputArray();

        tasksStub.add(todoStub);
        taskListSut.handleTodo(inputArray, MAKE_TODO_INPUT_VALID);

        assertEquals(tasksStub.get(0), taskListSut.getTasks().get(0));
    }

    @Test
    void handleTodo_emptyTodo_fail() {
        Parser parser = new Parser(MAKE_TODO_INPUT_INVALID);
        String[] inputArray = parser.getInputArray();
        assertEquals(ResponseFormatter.printDukeException(
                new DukeException(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION,
                        Todo.TASK_NAME)), "Please try again:"),
                        taskListSut.handleTodo(inputArray, MAKE_TODO_INPUT_INVALID));
    }

    @Test
    void handleDeadline_emptyDesc_fail() {
        assertEquals(ResponseFormatter.printDukeException(
                new DukeException(String.format(MESSAGE_EMPTY_TASK_DESCRIPTION,
                        Deadline.TASK_NAME)), "Please try again:"),
                        taskListSut.handleDeadline(MAKE_DEADLINE_EMPTY_DESC_INVALID));
    }

    @Test
    void handleDeadline_deadline_success() {
        tasksStub.add(deadlineStub);
        taskListSut.handleDeadline(MAKE_DEADLINE_INPUT_VALID);

        assertEquals(tasksStub.get(0), taskListSut.getTasks().get(0));
    }

    @Test
    void handleEvent_event_success() {
        tasksStub.add(eventStub);
        taskListSut.handleEvent(MAKE_EVENT_INPUT_VALID);

        assertEquals(tasksStub.get(0), taskListSut.getTasks().get(0));
    }

    @Test
    void handleDelete_deleteFirstTask_success() {
        Parser parser = new Parser(DELETE_FIRST_TASK_VALID);

        taskListSut.getTasks().add(todoStub);

        assertEquals(ResponseFormatter.printFeedbackFooter(MESSAGE_REMOVE_TASK_DONE, todoStub, tasksStub),
                taskListSut.handleDelete(parser.getInputArray()));
    }

    @Test
    void handleDelete_deleteNonExistTask_fail() {
        Parser parser = new Parser(DELETE_FARAWAY_TASK_VALID);

        taskListSut.getTasks().add(todoStub);

        assertEquals(ResponseFormatter.printDukeException(
                        new DukeException(MESSAGE_INVALID_INDEX), "Please try again:"),
                taskListSut.handleDelete(parser.getInputArray()));
    }
}
