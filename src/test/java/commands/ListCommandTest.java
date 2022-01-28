package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCommandTest {
	ListCommand cmd;

	@BeforeEach
	void setUp() {
		cmd = new ListCommand();
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
	void execute(){
		TaskList tasks = new TaskList();
		tasks.add(new ToDo("test"));
		System.out.println(tasks.size());
		assertNull(cmd.getList());
		cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
		assertEquals(1, cmd.getList().size());



	}
}
