/**
 * Represents a deadline to be fulfilled. This deadline is a task that possesses a state/status that is by default
 * not done.
 */
public class Deadline extends Task {
	private final String TYPE = "[D]";
	private final String date;
	private final String NAME;

	/**
	 * Creates a task with a Deadline, that is initially undone.
	 *
	 * @param name Name of the Deadline Task.
	 * @param date Date of the deadline.
	 */
	public Deadline(String name, String date) {
		super(name);
		this.NAME = name;
		this.date = date;
	}

	/**
	 * Returns the String representation of a Deadline.
	 *
	 * @return String representation of Deadline.
	 */
	@Override
	public String toString() {
		return this.NAME + "(" + date + ")";
	}

	/**
	 * Returns the type of Task. In this case, it is a Deadline.
	 *
	 * @return Type of Task.
	 */
	@Override
	public String track() {
		return this.TYPE;
	}
}
