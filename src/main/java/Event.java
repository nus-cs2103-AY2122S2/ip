public class Event extends Task {
	private final String TYPE = "[E]";
	private final String TIME;
	private final String NAME;

	public Event(String name, String time) {
		super(name);
		this.NAME = name;
		this.TIME = time;
	}

	@Override
	public String toString() {
		return this.NAME + "(" + this.TIME + ")";
	}

	@Override
	public String track() {
		return this.TYPE;
	}
}
