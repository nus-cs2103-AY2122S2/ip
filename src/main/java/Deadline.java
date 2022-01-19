public class Deadline extends Task {

    private String deadlineDate;

    public Deadline(String description, String deadlineDate){
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineDate(){
        return this.deadlineDate;
    }

    public String toString() {
        return "[D]" + super.toString() + " (By: " + this.getDeadlineDate() + ")";
    }

}
