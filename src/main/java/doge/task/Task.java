package doge.task;

import java.time.LocalDateTime;

public class Task {
	protected String description;
	protected LocalDateTime dateTime;
	protected boolean isDone;

	public Task(String description) {
		this.description = description.trim();
		this.isDone = false;
	}

	public Task(String description, LocalDateTime dateTime) {
		this.description = description.trim();
		this.dateTime = dateTime;
		this.isDone = false;
	}

	public boolean isDone() {
		return this.isDone;
	}

	public String getStatusIcon() {
		return isDone ? "✓" : " ";
	}

	public String getDescription() {
		return this.description;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	public void mark() {
		this.isDone = true;
	}

	public void unmark() {
		this.isDone = false;
	}

	@Override
	public String toString() {
		return "| " + this.getStatusIcon() + " | " + this.description;
	}
}
