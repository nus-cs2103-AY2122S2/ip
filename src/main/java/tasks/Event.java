package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
	private LocalDateTime date;

	public Event(String detail, String date) {
		super(detail);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/M/yyyy HHmm");
		this.date = LocalDateTime.parse(date, formatter);
	}

	@Override
	public String getDate(){
		return date;
	}

	@Override
	public String getType(){
		return "E";
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
		String markedPrint;
		if (marked) {
			markedPrint = "[E][X] ";
		} else {
			markedPrint = "[E][ ] ";
		}
		return markedPrint + detail + "(at:" + date.format(formatter) + ")";
	}
}
