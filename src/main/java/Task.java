import java.time.LocalDateTime;

public class Task {
	String description;
	LocalDateTime dateTime;
	boolean isDone;

	public Task(String description) {
		this.description = description.trim();
		this.isDone = false;
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
