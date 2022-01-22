public class ToDo extends Task{

    ToDo(String description){
        super(description);
    }

    public void print() {
        System.out.print("[T]");
        System.out.println("[" + (this.completed ? "x" : " ") +  "] " + this.description);
    }

    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[0] = TaskType.TODO.toString();
        details[3] = "";
        return details;
    }
}
