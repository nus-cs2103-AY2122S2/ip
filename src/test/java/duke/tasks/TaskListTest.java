package duke.tasks;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTodoTask_valid_success() {
        String[] inputs = {"todo", "Buy", "Book"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addTodoTask(inputs));
            System.out.println("Test case succeeded!");
        } catch (Exception e) {
            System.out.println("Exception caught! Test case failed!");
        }
    }

    @Test
    public void addTodoTask_missingDescription_emptyDescriptionError() {
        String[] inputs = {"todo"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addTodoTask(inputs));
            System.out.println("Exception missed! Test case failed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_valid_success() {
        String[] inputs = {"deadline", "Buy", "Book", "/by", "2015-05-05"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addDeadlineTask(inputs));
            System.out.println("Test case succeeded!");
        } catch (Exception e) {
            System.out.println("Exception caught! Test case failed!");
        }
    }

    @Test
    public void addDeadlineTask_missingBySeparator_invalidDateError() {
        String[] inputs = {"deadline", "Buy", "Book", "2015-05-05"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addDeadlineTask(inputs));
            System.out.println("Exception missed! Test case failed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_missingDescription_emptyDescriptionError() {
        String[] inputs = {"deadline", "/by", "2015-05-05"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addDeadlineTask(inputs));
            System.out.println("Exception missed! Test case failed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addEventTask_valid_success() {
        String[] inputs = {"event", "Buy", "Book", "/at", "2015-05-05", "02:00-03:00"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addEventTask(inputs));
            System.out.println("Test case succeeded!");
        } catch (Exception e) {
            System.out.println("Exception caught! Test case failed!");
        }
    }

    @Test
    public void addEventTask_missingTimeRange_invalidTimeError() {
        String[] inputs = {"event", "Buy", "Book", "/at", "2015-05-05", "02:00"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addEventTask(inputs));
            System.out.println("Exception missed! Test case failed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addEventTask_missingDescription_emptyDescriptionError() {
        String[] inputs = {"event", "/at", "2015-05-05", "02:00"};
        TaskList taskList = new TaskList();
        try {
            System.out.println("Task created: " + taskList.addEventTask(inputs));
            System.out.println("Exception missed! Test case failed!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
