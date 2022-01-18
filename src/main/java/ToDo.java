public class ToDo extends Task {

    ToDo(String d) {
        super(d);
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[T][" + tempStr + "] " + this.description;
    }

}