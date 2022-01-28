package Duke;

import java.util.ArrayList;

import Duke.task.Task;
import Duke.task.TaskStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

	@Test
	void add() {
		TaskList taskList = new TaskList(100);
		TaskStub taskStub = new TaskStub("Hello");
		taskList.add(taskStub);
		assertEquals(1, taskList.getCount());
	}

	@Test
	void delete() {
		TaskList taskList = new TaskList(100);
		taskList.delete(0);
		assertEquals(0, taskList.getCount());
	}

	@Test
	void getTask() {
		TaskList taskList = new TaskList(100);
		TaskStub taskStub = new TaskStub("Hello");
		taskList.add(taskStub);
		assertEquals("Hello", taskList.getTask(0).getName());
	}

	@Test
	void getCount() {
		TaskList taskList = new TaskList(100);
		assertEquals(0, taskList.getCount());
	}
}