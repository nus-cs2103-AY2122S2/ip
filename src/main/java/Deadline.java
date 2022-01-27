public class Deadline extends Task{

    Deadline(String task, String time) {
        super(task, "D", time);
    }

    @Override
    String saveFormat() {
        return super.saveFormat() + " ### " + this.time;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + this.date.format(formatter) + ")";
    }
}
