package commands;

import exceptions.DukeException;
import ui.Ui;
import storage.Storage;
import tasks.TaskList;

public abstract class Command {
	public final static String INDENT = "     ";

	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	public abstract boolean ends();

	public abstract TaskList getList();
}
