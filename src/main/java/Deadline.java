import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
	LocalDateTime dateTime;

	public Deadline(String description) {
		super(description);
	}

	public Deadline(String description, LocalDateTime dateTime) {
		super(description);
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "D " + super.toString() + " | DEADLINE: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd-MMM" +
				"-yyyy " +
				"HH:mm"));
	}
}
