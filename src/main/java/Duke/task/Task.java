package Duke.task;

/**
 * Represents a task to be done. This task possesses a state/status that is by default not done. Task also has a
 * tracker that keeps track of the type of task.
 */
public abstract class Task {
	private String status = "[ ]"; // for all new tasks added to list, they are initially not done.
	private final String DESCRIPTION;
	private boolean isDone = false;
	private final int id;
	public static int count = 0;

	/**
	 * Creates a Task that is initially undone.
	 *
	 * @param description Description of Task.
	 */
	public Task(String description) {
		this.DESCRIPTION = description;
		this.id = count;
		count++;
	}

	/**
	 * Marks the task as done.
	 */
	public void mark() {
		if (!isDone) {
			isDone = true;
			this.status = "[X]";
		}
	}

	/**
	 * Marks the task as undone.
	 */
	public void unmark() {
		if (isDone) {
			isDone = false;
			this.status = "[ ]";
		}
	}

	/**
	 * Returns the current status of the Task.
	 *
	 * @return Current position of Task.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Returns the type of Task.
	 *
	 * @return Type of Task.
	 */
	public abstract String track();

	/**
	 * Returns the String representation of a Task
	 *
	 * @return String representation of Task.
	 */
	@Override
	public String toString() {
		return this.DESCRIPTION;
	}

	/**
	 * Returns the name of the Task.
	 *
	 * @return Name of Task.
	 */
	public abstract String getName();
}
