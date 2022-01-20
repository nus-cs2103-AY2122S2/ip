import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskList {

	private static final String TODO = "todo";
	private static final String DEADLINE = "deadline";
	private static final String EVENT = "event";

	private final List<Task> list;
	private final Set<String> set;
	private final Set<String> validTypes;

	public TaskList() {
		this.list = new ArrayList<>();
		this.set = new HashSet<>();

		this.validTypes = new HashSet<>();
		this.validTypes.add(TODO);
		this.validTypes.add(DEADLINE);
		this.validTypes.add(EVENT);
	}

	public Task getTask(int index) {
		return list.get(index);
	}

	public int getNumTasks() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Boolean isValidIndex(int index) {
		return index >= 0 && index < list.size();
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
					type.equals(DEADLINE)
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
				case DEADLINE:
					if (!otherArgs[0].equals("by")) {
						throw new ChatBotException(
							"The correct format for adding a deadline is deadline <name of task> /by <deadline of task>"
						);
					} else {
						Deadline deadline = new Deadline(title, other);
						list.add(deadline);
						return String.format(
							"This deadline has been added to your task list!%n%n             %s",
							deadline
						);
					}
				case EVENT:
					if (!otherArgs[0].equals("at")) {
						throw new ChatBotException(
							"The correct format for adding an event is event <name of task> /at <timestamp of task>"
						);
					} else {
						Event event = new Event(title, other);
						list.add(event);
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
			list.add(todo);
			return String.format(
				"This todo has been added to your task list!%n%n             %s",
				todo
			);
		}
	}

	public String delete(int index) throws ChatBotException {
		if (!isValidIndex(index).equals(true)) {
			throw new ChatBotException(
				"This is an invalid task index traveller! You can type list to check all task indexes!"
			);
		}

		Task removedTask = list.remove(index);
		return String.format(
			"This task has successfully been removed from your task list!%n%n             %s",
			removedTask
		);
	}

	private String combineArgs(String[] args) {
		String title = "";
		for (int i = 1; i < args.length; i++) {
			if (i != args.length - 1) {
				title = title.concat(args[i]).concat(" ");
			} else {
				title = title.concat(args[i]);
			}
		}
		return title;
	}

	public String summary() throws ChatBotException {
		if (isEmpty()) {
			throw new ChatBotException(
				"Your task list is empty traveller! Add some tasks first!"
			);
		}

		String summary = "";
		for (int i = 0; i < list.size(); i++) {
			String taskString = String.format(
				"             %d. %s%n",
				i + 1,
				list.get(i)
			);
			summary = summary.concat(taskString);
		}
		return summary;
	}

	public String mark(int index) {
		Task task = list.get(index);
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
		Task task = list.get(index);
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
}
