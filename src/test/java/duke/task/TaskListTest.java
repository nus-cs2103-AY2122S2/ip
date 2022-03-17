package duke.task;

import static duke.commons.core.Messages.MESSAGE_ADD_TASK;
import static duke.commons.core.Messages.MESSAGE_MARK_TASK_DONE;
import static duke.commons.core.Messages.MESSAGE_MARK_TASK_UNDONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
