package commands;

import exceptions.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

public class UnMarkCommandTest {
	UnMarkCommand cmd;

	@BeforeEach
	void setUp() {
		cmd = new UnMarkCommand(3);
	}

	@Test
	void getListBeforeExecute() {
		assertNull(cmd.getList());
	}

	@Test
	void doesNotEnd() {
		assertTrue(!cmd.endsProgram());
	}

	@Test
	void unMarks() {
		TaskList tasks = new TaskList();
		tasks.add(new ToDo("test"));
		tasks.add(new ToDo("test2"));
		tasks.add(new ToDo("test3"));
		Task marked = tasks.get(2);
		marked.mark();
		tasks.set(2, marked);
		assertEquals("1", tasks.get(2).getMark());
		try {
			cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
			assertEquals("0", cmd.getList().get(2).getMark());
		} catch (DukeException e) {
			e.getMessage();
		}

	}

	@Test
	void throwsOutOfRangeException() {
		TaskList tasks = new TaskList();
		tasks.add(new ToDo("test"));
		tasks.add(new ToDo("test2"));

		try {
			cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
			fail();
		} catch (DukeException e) {
			assertEquals( "☹ OOPS!!! The value input is not in the list", e.getMessage());
		}
	}
}
