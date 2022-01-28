import java.time.format.DateTimeParseException;

public class Event extends Task{
    private String eventDate;
    private Time time;

    public Event(String name, boolean isChecked, String taskLabel, String eventDate) {
        super(name, isChecked, taskLabel);
//        try {
            Time time = new Time(eventDate);
            this.time = time;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
//        }


    }

//    public Event(String name, boolean isChecked, String taskLabel, Time time) {
//        super(name, isChecked, taskLabel);
//        this.time = time;
//    }

    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString() + " (at: " + this.time + ")";
    }
}