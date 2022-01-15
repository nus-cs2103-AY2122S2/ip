public class ToDo extends Task{

    ToDo(String description){
        super(description);
    }

    public void print() {
        System.out.print("[T]");
        System.out.println("[" + (this.completed ? "x" : " ") +  "] " + this.description);
    }
}
