public abstract class Task {
	private String status = "[ ]"; // for all new tasks added to list, they are initially not done.
	private final String DESCRIPTION;
	private final int id;
	public static int count = 0;

	public Task(String description) {
		this.DESCRIPTION = description;
		this.id = count;
		count++;
	}

	public void mark() {
		this.status = "[X]";
	}

	public void unmark() {
		this.status = "[ ]";
	}

	public String getStatus() {
		return this.status;
	}

	public abstract String track();

	@Override
	public String toString() {
		return this.DESCRIPTION;
	}
}
