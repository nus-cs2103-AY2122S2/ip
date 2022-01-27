package doge.task;

/**
 * Represents the task details of the "todo" type.
 */
public class Todo extends Task {
	/**
	 * Constructor for the class Todo
	 *
	 * @param description the task details
	 */
	public Todo(String description) {
		super(description);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "T " + super.toString();
	}
}
