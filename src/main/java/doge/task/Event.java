package doge.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the task details of the "event" type
 */
public class Event extends Task {

	/**
	 * Constructor for class Event.
	 *
	 * @param description the task details
	 */
	public Event(String description) {
		super(description);
	}

	/**
	 * Another constructor for class Event.
	 *
	 * @param description the task details
	 * @param dateTime the date and time of the task
	 */
	public Event(String description, LocalDateTime dateTime) {
		super(description, dateTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "E " + super.toString() + " | AT: " + this.getDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy " +
				"HH:mm"));
	}
}
