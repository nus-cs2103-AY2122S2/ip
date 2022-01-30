public class Todo extends Task{

    public Todo(String title) {
        super(title);
    }

    public String toString(){
        if (this.checked) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

}
