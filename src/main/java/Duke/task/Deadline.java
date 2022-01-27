package Duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline to be fulfilled. This deadline is a task that possesses a state/status that is by default
 * not done.
 */
public class Deadline extends Task {

	private final String TYPE = "D";
	private final String INITIAL_DATE_STR;
	private final String CONVERTED_DATE_STR;
	private final String NAME;
	private final LocalDate date;
	private final LocalTime time;

	/**
	 * Creates a task with a Deadline, that is initially undone.
	 *
	 * @param name    Name of the Deadline Task.
	 * @param dateStr Date of the deadline as String.
	 */
	public Deadline(String name, String dateStr) {
		super(name);
		this.NAME = name;
		this.INITIAL_DATE_STR = dateStr;

		String[] tokens = dateStr.split("\\s+");
		this.date = LocalDate.parse(tokens[0]);
		String hour = tokens[1].substring(0, 2);
		String min = tokens[1].substring(2, 4);
		this.time = LocalTime.parse(hour + ":" + min);
		this.CONVERTED_DATE_STR = convertDate();
	}

	/**
	 * Returns String format of date and time converted to preferred format.
	 * YYYY-MM-DD to (DD, Month (First 3 Letters), YYYY, HH:MM)
	 * E.g. 2012-11-11 1600 to 11 Nov 2012,
	 *
	 * @return String of preferred format.
	 */
	public String convertDate() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

		String str = this.date.format(dateFormatter) + ", " + this.time.format(timeFormatter);
		return str;
	}

	/**
	 * Returns the String representation of a Deadline.
	 *
	 * @return String representation of Deadline.
	 */
	@Override
	public String toString() {
		return this.NAME + "(by: " + CONVERTED_DATE_STR + ")";
	}

	/**
	 * Returns the type of Task. In this case, it is a Deadline.
	 *
	 * @return Type of Task.
	 */
	@Override
	public String track() {
		return "[" + this.TYPE + "]";
	}

	/**
	 * Returns the name of the Task.
	 *
	 * @return Name of Task.
	 */
	public String getName() {
		return this.NAME;
	}

	/**
	 * Returns the deadline.
	 *
	 * @return Deadline of Task.
	 */
	public String getDate() {
		return this.CONVERTED_DATE_STR;
	}

	public String getUnconvertedDate() {
		return this.INITIAL_DATE_STR;
	}
}
