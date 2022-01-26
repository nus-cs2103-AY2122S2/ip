package doge.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
	public Event(String description) {
		super(description);
	}

	public Event(String description, LocalDateTime dateTime) {
		super(description, dateTime);
	}

	@Override
	public String toString() {
		return "E " + super.toString() + " | AT: " + this.getDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy " +
				"HH:mm"));
	}
}
