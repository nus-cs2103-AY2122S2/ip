public class Deadline extends Task {

    private String deadlineDate;

    public Deadline(String description, String deadlineDate){
        super(description, "D");
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineDate(){
        return this.deadlineDate;
    }

    public String toSaveDataFormat() {
        String isDone = (super.checkIsDone() == true) ? "1" : "0";
        return String.format("%s|%s|%s|%s\n", super.getTag(), isDone, super.getTaskDescription(), this.getDeadlineDate());
    }

    @Override
    public String toString() {
        return super.toString() + " (By: " + this.getDeadlineDate() + ")";
    }

}
