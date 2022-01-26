package Duke;

/**
 * Represents an upcoming event associated with its time of occurrence. This event is a task that possesses a
 * state/status that is by default not done.
 */
public class Event extends Task {
	private final String TYPE = "[E]";
	private final String TIME;
	private final String NAME;

	/**
	 * Creates an Event that is initially undone.
	 *
	 * @param name Name of the Event.
	 * @param time Time of the upcoming Event.
	 */
	public Event(String name, String time) {
		super(name);
		this.NAME = name;
		this.TIME = time;
	}

	/**
	 * Returns the String representation of a Deadline.
	 *
	 * @return String representation of Deadline.
	 */
	@Override
	public String toString() {
		return this.NAME + "(at: " + this.TIME + ")";
	}

	/**
	 * Returns the type of Task. In this case, it is an Event.
	 *
	 * @return Type of Task.
	 */
	@Override
	public String track() {
		return this.TYPE;
	}
}
