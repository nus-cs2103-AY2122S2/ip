/**
 * Represents the instruction "mark as done".
 */
final class MarkAsDone extends Instruction {

    Task toMark;
    protected MarkAsDone(int index) {
        super.setDescription("mark");
        this.toMark = TaskManager.getTaskIndex(index);
    }

    @Override
    protected String act() {
        TaskManager.markAsDone(this.toMark);
        return "Nice! I've marked this task as done:\n     " + this.toMark.toString();
    }
}
