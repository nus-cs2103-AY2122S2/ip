public class Task {

	private final String title;
	private boolean done;

	public Task(String title) {
		this.title = title;
		this.done = false;
	}

	public boolean isCompleted() {
		return done;
	}

	public void mark() {
		done = true;
	}

	public void unmark() {
		done = false;
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", done ? "X" : " ", title);
	}
}
