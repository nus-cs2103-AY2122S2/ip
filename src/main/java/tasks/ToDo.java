package tasks;

public class ToDo extends Task {

	public ToDo(String detail) {
		super(detail);
	}

	@Override
	public String getDate(){
		return "No Date";
	}

	@Override
	public String getType(){
		return "T";
	}

	@Override
	public String toString() {
		if (marked) {
			return "[T][X] " + detail;
		} else {
			return "[T][ ] " + detail;
		}
	}
}
