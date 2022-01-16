public class Event extends Task{
    public Event(String input, int number, String deadline) {
        super(input, number, deadline, "event");
    }
    @Override
    public String toString(){
        String s = String.format("%d. [E][%s] %s (at: %s)\n", number+1, getStatus(), name, deadline);
        return s;
    }
}
