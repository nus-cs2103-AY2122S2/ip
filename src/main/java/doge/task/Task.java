package doge.task;

import java.time.LocalDateTime;

/**
 * Represents the task details, the completion status and the date/time if applicable.
 */
public class Task {
	protected String description;
	protected LocalDateTime dateTime;
	protected boolean isDone;

	/**
	 * Constructor for class Task.
	 *
	 * @param description the task details
	 */
	public Task(String description) {
		this.description = description.trim();
		this.isDone = false;
	}

	/**
	 * Another constructor for class Task.
	 *
	 * @param description the task details
	 * @param dateTime the date and time of the task
	 */
	public Task(String description, LocalDateTime dateTime) {
		this.description = description.trim();
		this.dateTime = dateTime;
		this.isDone = false;
	}

	/**
	 * Returns the completion status of the task.
	 *
	 * @return the completion status of the task
	 */
	public boolean isDone() {
		return this.isDone;
	}

	/**
	 * Returns the status icon of the task depending on its completion status.
	 *
	 * @return the status icon of the task
	 */
	public String getStatusIcon() {
		return isDone ? "✓" : " ";
	}

	/**
	 * Returns the task details.
	 *
	 * @return the task details
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the task details.
	 *
	 * @param d the new description to replace the current task details
	 */
	public void setDescription(String d) {
		this.description = d;
	}

	/**
	 * Sets the date/time of the task.
	 *
	 * @param dateTime the new date/time to replace the current task date/time
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Returns the task's date/time
	 *
	 * @return the task's date/time
	 */
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	/**
	 * Marks the task as completed
	 */
	public void mark() {
		this.isDone = true;
	}

	/**
	 * Unmarks the task as not completed
	 */
	public void unmark() {
		this.isDone = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "| " + this.getStatusIcon() + " | " + this.description;
	}
}
