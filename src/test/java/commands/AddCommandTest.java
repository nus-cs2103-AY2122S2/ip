package commands;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
	AddCommand cmd;

	@Test
	void getListBeforeExecuteToDo() {
		cmd = new AddCommand("todo", "return book");
		assertNull(cmd.getList());
	}

	@Test
	void doesNotEndToDo() {
		cmd = new AddCommand("todo", "return book");
		assertTrue(!cmd.endsProgram());
	}

	@Test
	void getListBeforeExecuteEvent() {
		cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
		assertNull(cmd.getList());
	}

	@Test
	void doesNotEndEvent() {
		cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
		assertTrue(!cmd.endsProgram());
	}

	@Test
	void getListBeforeExecuteDeadline() {
		cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
		assertNull(cmd.getList());
	}

	@Test
	void doesNotEndDeadline() {
		cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
		assertTrue(!cmd.endsProgram());
	}

	@Test
	void addsToList() {
		TaskList tasks = new TaskList();
		assertEquals(0, tasks.size());
		cmd = new AddCommand("todo", "return book");
		cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
		tasks = cmd.getList();
		assertEquals(1, tasks.size());

		cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
		cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
		tasks = cmd.getList();
		assertEquals(2, tasks.size());

		cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
		cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
		tasks = cmd.getList();
		assertEquals(3, tasks.size());
	}

	@Test
	void addsCorrectTaskType(){
		TaskList tasks = new TaskList();
		assertEquals(0, tasks.size());
		cmd = new AddCommand("todo", "return book");
		cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
		assertEquals("T", cmd.getList().get(0).getType());
		cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
		cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
		assertEquals("E", cmd.getList().get(1).getType());
		cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
		cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
		assertEquals("D", cmd.getList().get(2).getType());
	}
}
