package Duke.task;

/**
 * Represents a type of task that has no deadline.
 * This task can be done or fulfilled anytime, and is by default not done.
 */
public class Todo extends Task {
	private final String TYPE = "T";
	private final String NAME;

	/**
	 * Creates a task to be done, that is initially undone.
	 *
	 * @param name Name of the task.
	 */
	public Todo(String name) {
		super(name);
		this.NAME = name;
	}

	/**
	 * Returns the String representation of this Task.
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * Returns the type of this Task. In this case, it is a task that needs to be completed in the future.
	 *
	 * @return Type of Task.
	 */
	@Override
	public String track() {
		return "[" + this.TYPE + "]";
	}

	/**
	 * Returns the name of the Task.
	 *
	 * @return Name of Task.
	 */
	public String getName() {
		return this.NAME;
	}
}