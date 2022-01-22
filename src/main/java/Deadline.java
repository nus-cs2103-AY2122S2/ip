public class Deadline extends Task {
	private final String TYPE = "[D]";
	private final String date;
	private final String NAME;

	public Deadline(String name, String date) {
		super(name);
		this.NAME = name;
		this.date = date;
	}

	@Override
	public String toString() {
		return this.NAME + "(" + date + ")";
	}

	@Override
	public String track() {
		return this.TYPE;
	}
}
