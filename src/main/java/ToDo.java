public class ToDo extends Task{
    protected String dead;

    public ToDo(String name, String dead) {
        super(name);
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
