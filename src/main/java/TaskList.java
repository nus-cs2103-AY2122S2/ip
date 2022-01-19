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

	public String add(String[] titleArgs, String[] otherArgs)
		throws ChatBotException {
		String type = titleArgs[0];
		if (!validTypes.contains(type)) {
			throw new ChatBotException();
		}
		if (titleArgs.length <= 1) {
			throw new ChatBotException(
				String.format(
					"You need to key in the title of your %s traveller!",
					type
				)
			);
		}
		if (otherArgs.length <= 1) {
			throw new ChatBotException(
				String.format(
					"You need to key in %s traveller!",
					type.equals("deadline")
						? "the due date and time of your deadline"
						: "the timestamp of your event"
				)
			);
		}

		String title = combineArgs(titleArgs);
		String other = combineArgs(otherArgs);

		if (set.contains(title)) {
			throw new ChatBotException(
				String.format("This %s is already in your task list!", type)
			);
		} else {
			set.add(title);
			switch (type) {
				case "deadline":
					if (!otherArgs[0].equals("by")) {
						throw new ChatBotException(
							"The correct format for adding a deadline is deadline <name of task> /by <deadline of task>"
						);
					} else {
						Deadline deadline = new Deadline(title, other);
						list[numItems++] = deadline;
						return String.format(
							"This deadline has been added to your task list!%n%n             %s",
							deadline
						);
					}
				case "event":
					if (!otherArgs[0].equals("at")) {
						throw new ChatBotException(
							"The correct format for adding an event is event <name of task> /at <timestamp of task>"
						);
					} else {
						Event event = new Event(title, other);
						list[numItems++] = event;
						return String.format(
							"This event has been added to your task list!%n%n             %s",
							event
						);
					}
				default:
					throw new ChatBotException();
			}
		}
	}

	public String addToDo(String[] args) throws ChatBotException {
		if (!validTypes.contains(args[0])) {
			throw new ChatBotException();
		}

		if (args.length <= 1) {
			throw new ChatBotException(
				"You need to key in the title of your todo traveller!"
			);
		}

		String title = combineArgs(args);
		if (set.contains(title)) {
			throw new ChatBotException(
				"This todo is already in your task list!"
			);
		} else {
			set.add(title);
			ToDo todo = new ToDo(title);
			list[numItems++] = todo;
			return String.format(
				"This todo has been added to your task list!%n%n             %s",
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
