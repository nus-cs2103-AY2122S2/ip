package tasks;

public class Event extends Task {
	private String date;

	public Event(String detail, String date) {
		super(detail);
		this.date = date;
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
		if (marked) {
			return "[E][X] " + detail + "(at:" + date + ")";
		} else {
			return "[E][ ] " + detail + "(at:" + date + ")";
		}
	}
}
