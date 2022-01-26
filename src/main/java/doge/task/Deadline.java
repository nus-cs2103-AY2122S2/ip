package doge.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
	public Deadline(String description) {
		super(description);
	}

	public Deadline(String description, LocalDateTime dateTime) {
		super(description, dateTime);
	}

	@Override
	public String toString() {
		return "D " + super.toString() + " | DEADLINE: " + this.getDateTime().format(DateTimeFormatter.ofPattern("dd-MMM" +
				"-yyyy " +
				"HH:mm"));
	}
}
