public class Deadline extends Task{
    public Deadline(String description) throws DukeException {
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
        return "[D]" + super.toString();
    }
}
