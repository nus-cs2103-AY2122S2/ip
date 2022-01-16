/**
 * Represents the instruction "unmark".
 */
final class UnmarkAsDone extends Instruction {

    private Task toUnmark;

    protected UnmarkAsDone(int index) {
        this.toUnmark = TaskManager.getTaskIndex(index);
        super.setDescription("unmark");
    }

    @Override
    protected String act() {
        TaskManager.markAsNotDone(this.toUnmark);
        return "I've marked this task as not done yet:\n" + this.toUnmark.toString();
    }
}
