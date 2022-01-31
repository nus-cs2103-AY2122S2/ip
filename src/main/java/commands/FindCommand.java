package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Class which handles finding of tasks in list using a keyword
 */
public class FindCommand extends Command {
	private TaskList tasks;
	private final String keyword;

	public FindCommand(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Method to get the modified task list after command execution
	 * @return TaskList
	 */
	@Override
	public TaskList getList() {
		return tasks;
	}

	/**
	 * Method to see if command ends the main program loop
	 * @return true if it ends main program
	 */
	@Override
	public boolean endsProgram() {
		return false;
	}

	/**
	 * method to execute the find command
	 * Finds all tasks that contain the keyword
	 * @param tasks tasks list to be modified
	 * @param ui to help with printing of messages
	 * @param storage To deal with saving of task list
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		this.tasks = tasks;
		TaskList temp = new TaskList();
		for (int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getDetail().contains(keyword)){
				temp.add(tasks.get(i));
			}
		}

		if(temp.size() == 0){
			ui.printFormatted(new String[]{"There are no tasks matching in your list"});
		} else {
			System.out.println(INDENT + "Here are the matching tasks in your list");
			for (int i = 0; i < temp.size(); i++) {
				System.out.println(INDENT + (i + 1) + "." + temp.get(i));
			}
		}
	}
}
