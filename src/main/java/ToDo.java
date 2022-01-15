public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String identify(){
        if (super.getIsDone()) {
            return String.format("[T][X] %s", super.getDescription());
        } else {
            return String.format("[T][ ] %s", super.getDescription());
        }
    }
}
