package Duke.command;

import Duke.Save;
import Duke.TaskList;
import Duke.Ui;
import Duke.task.Task;

import java.util.ArrayList;

/**
 * This FindCommand class will find a task name that has a matching given keyword when executed.
 */
public class FindCommand extends Command {
	private final String KEYWORD;

	public FindCommand(String keyword) {
		this.KEYWORD = keyword;
	}

	/**
	 * Executes command by matching a keyword and printing the matched tasks that contains keyword.
	 *
	 * @param tasks TaskList of tasks.
	 * @param ui    Ui provided.
	 * @param save  Saved history.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Save save) {
		ArrayList<Task> taskArrList = tasks.getCurrentList();
		ArrayList<Boolean> keywordMatchedList = new ArrayList<>(tasks.getCount());
		boolean hasMatch = false;

		taskArrList.forEach(x -> {
			boolean hasKeyword = x.getName().contains(this.KEYWORD);
			keywordMatchedList.add(hasKeyword);
		});

		Boolean[] keywordMatchArr = new Boolean[tasks.getCount()];
		keywordMatchArr = keywordMatchedList.toArray(keywordMatchArr);

		for (Boolean x : keywordMatchArr) {
			hasMatch = hasMatch || x;
		}

		System.out.println("\t____________________________________________________________");
		if (hasMatch) {
			int matchedTaskCount = 1;
			System.out.println("\t Here are the matching tasks in your list:");
			for (int i = 0; i < tasks.getCount(); i++) {
				if (keywordMatchedList.get(i)) {
					Task task = tasks.getTask(i);
					String str = "\t " + matchedTaskCount + "." + task.track() + task.getStatus() + " " + task;
					matchedTaskCount++;
					System.out.println(str);
				}
			}
		} else {
			System.out.println("\t No tasks found with keyword '" + this.KEYWORD + "'.");
		}
		System.out.println("\t____________________________________________________________");
	}
}
