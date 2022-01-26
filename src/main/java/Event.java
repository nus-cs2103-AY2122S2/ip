import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
	LocalDateTime dateTime;

	public Event(String description) {
		super(description);
	}

	public Event(String description, LocalDateTime dateTime) {
		super(description);
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "E " + super.toString() + " | AT: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy " +
				"HH:mm"));
	}
}
