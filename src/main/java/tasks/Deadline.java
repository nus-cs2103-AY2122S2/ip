package tasks;

public class Deadline extends Task {
	private String deadline;

	public Deadline(String detail, String deadline) {
		super(detail);
		this.deadline = deadline;
	}

	@Override
	public String getDate(){
		return deadline;
	}

	@Override
	public String getType(){
		return "D";
	}

	@Override
	public String toString() {
		if (marked) {
			return "[D][X] " + detail + "(by:" + deadline + ")";
		} else {
			return "[D][ ] " + detail + "(by:" + deadline + ")";
		}
	}
}
