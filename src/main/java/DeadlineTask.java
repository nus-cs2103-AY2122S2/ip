public class DeadlineTask extends Task{
    private String date;

    public DeadlineTask(String description, boolean isCompleted,String date){
        super(description, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]"+super.toString() + "(by: " + this.date + ")";
    }

    @Override
    public String toFileString(){
        return "D" + super.toFileString() + " | " + this.date;
    }
}
