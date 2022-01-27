package doge.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the task details of the "deadline" type.
 */
public class Deadline extends Task {
	/**
	 * Constructor for class Deadline.
	 *
	 * @param description the task details
	 */
	public Deadline(String description) {
		super(description);
	}

	/**
	 * Another constructor for class Deadline.
	 *
	 * @param description the task details
	 * @param dateTime the date and time of the task
	 */
	public Deadline(String description, LocalDateTime dateTime) {
		super(description, dateTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "D " + super.toString() + " | DEADLINE: " + this.getDateTime().format(DateTimeFormatter.ofPattern("dd-MMM" +
				"-yyyy " +
				"HH:mm"));
	}
}
