public class Event extends Task{

    public Event(String description) {
        super(description);
        String[] strArr = description.split("/at");
        this.description = strArr[0] + "(" + "at:" + strArr[1] + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

}
