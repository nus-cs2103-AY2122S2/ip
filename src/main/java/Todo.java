public class Todo extends Task {
	private final String TYPE = "[T]";

	public Todo(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String track() {
		return this.TYPE;
	}
}