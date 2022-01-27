package Duke;

import java.util.ArrayList;

import Duke.task.Task;
import Duke.task.TaskStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

	@Test
	void add() {
		ArrayList<Task> taskArrList = new ArrayList<Task>(100);
		TaskList taskList = new TaskList(taskArrList);
		TaskStub taskStub = new TaskStub("Hello");
		taskList.add(taskStub);
		assertEquals(1, taskArrList.size());
	}

	@Test
	void delete() {
		ArrayList<Task> taskArrList = new ArrayList<Task>(100);
		TaskList taskList = new TaskList(taskArrList);
		taskList.delete(0);
		assertEquals(0, taskArrList.size());
	}

	@Test
	void getTask() {
		ArrayList<Task> taskArrList = new ArrayList<Task>(100);
		TaskList taskList = new TaskList(taskArrList);
		TaskStub taskStub = new TaskStub("Hello");
		taskList.add(taskStub);
		assertEquals("Hello", taskList.getTask(0).getName());
	}

	@Test
	void getCount() {
		ArrayList<Task> taskArrList = new ArrayList<Task>(100);
		TaskList taskList = new TaskList(taskArrList);
		assertEquals(0, taskList.getCount());
	}
}