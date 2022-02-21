public class EventTask extends Task{
    private String dateStr;

    public EventTask(String description, boolean isCompleted, String dateStr){
        super(description, isCompleted);
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString() + "(at: " + dateStr + ")";
    }

    //E | 0 | project meeting | Aug 6th 2-4pm
    @Override
    public String toFileString(){

        return "E" + super.toFileString() + " | "  + this.dateStr + " | ";
    }
}
