public class Event extends Task{

    public Event(String description) throws DukeException {
        super(description);
        try {
            String[] strArr = description.split("/at");
            this.description = strArr[0] + "(" + "at:" + strArr[1] + ")";
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

}
