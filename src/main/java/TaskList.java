import java.util.HashSet;
import java.util.Set;

public class TaskList {

	private final Task[] list;
	private final Set<String> set;
	private int numItems;
	private final Set<String> validTypes;

	public TaskList() {
		this.list = new Task[100];
		this.numItems = 0;
		this.set = new HashSet<>();

		this.validTypes = new HashSet<>();
		this.validTypes.add("todo");
		this.validTypes.add("deadline");
		this.validTypes.add("event");
	}

	public Task getTask(int index) {
		return list[index];
	}

	public int getNumTasks() {
		return numItems;
	}

	public Boolean isValidIndex(int index) {
		return index >= 0 && index < numItems;
	}

	public String add(String[] titleArgs, String[] otherArgs) {
		if (titleArgs.length <= 1 || !validTypes.contains(titleArgs[0])) {
			throw new IllegalArgumentException();
		}

		String title = combineArgs(titleArgs);
		String other = combineArgs(otherArgs);

		if (set.contains(title)) {
			return "This task is already in your task list!";
		} else {
			set.add(title);
			String type = titleArgs[0];
			String returnString =
				"This task has been added to your task list!%n%n             %s";
			switch (type) {
				case "deadline":
					if (!otherArgs[0].equals("by")) {
						throw new IllegalArgumentException();
					} else {
						Deadline deadline = new Deadline(title, other);
						list[numItems++] = deadline;
						return String.format(returnString, deadline);
					}
				case "event":
					if (!otherArgs[0].equals("at")) {
						throw new IllegalArgumentException();
					} else {
						Event event = new Event(title, other);
						list[numItems++] = event;
						return String.format(returnString, event);
					}
				default:
					throw new IllegalArgumentException();
			}
		}
	}

	public String addToDo(String[] args) {
		if (args.length <= 1 || !validTypes.contains(args[0])) {
			throw new IllegalArgumentException();
		}

		String title = combineArgs(args);
		if (set.contains(title)) {
			return "This task is already in your task list!";
		} else {
			set.add(title);
			ToDo todo = new ToDo(title);
			list[numItems++] = todo;
			return String.format(
				"This task has been added to your task list!%n%n             %s",
				todo
			);
		}
	}

	private String combineArgs(String[] input) {
		String title = "";
		for (int i = 1; i < input.length; i++) {
			if (i != input.length - 1) {
				title = title.concat(input[i]).concat(" ");
			} else {
				title = title.concat(input[i]);
			}
		}
		return title;
	}

	public void summary() {
		for (int i = 0; i < numItems; i++) {
			System.out.printf("             %d. %s%n", i + 1, list[i]);
		}
		System.out.println();
	}

	public String mark(int index) {
		Task task = list[index];
		if (task.isCompleted()) {
			return "This task was already completed! No need to mark it again.";
		} else {
			task.mark();
			return String.format(
				"This task has been marked as completed in your task list!%n%n             %s",
				task
			);
		}
	}

	public String unmark(int index) {
		Task task = list[index];
		if (!task.isCompleted()) {
			return "This task has not been completed yet! No need to unmark it.";
		} else {
			task.unmark();
			return String.format(
				"This task has been unmarked in your task list!%n%n             %s",
				task
			);
		}
	}

	public boolean isEmpty() {
		return numItems == 0;
	}
}
