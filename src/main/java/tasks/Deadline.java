package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
	private LocalDateTime deadline;

	public Deadline(String detail, String deadline) {
		super(detail);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/M/yyyy HHmm");
		this.deadline = LocalDateTime.parse(deadline, formatter);
	}

	@Override
	public String getDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/M/yyyy HHmm");
		return deadline.format(formatter);
	}

	@Override
	public String getType(){
		return "D";
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
		return markedPrint + detail + "(by:" + deadline.format(formatter) + ")";
	}
}
