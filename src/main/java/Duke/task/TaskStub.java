package Duke.task;

public class TaskStub extends Task {
	public final String DESCRIPTION;

	public TaskStub(String description) {
		super(description);
		this.DESCRIPTION = description;
	}

	@Override
	public String track() {
		return "[ ]";
	}

	@Override
	public String getName() {
		return this.DESCRIPTION;
	}
}
