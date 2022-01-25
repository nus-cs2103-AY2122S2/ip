public class Deadline extends Task{
    public String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    public String toString(){
        if (this.checked) {
            return "[D][X] " + this.title + "(by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.title + "(by: " + this.deadline + ")";
        }
    }
}
