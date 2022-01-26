package narcibot;
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
        System.out.println("[T][ ] " + name);
    }

    public ToDo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    @Override
    public void markDone() {
        System.out.print("[T]");
        super.markDone();
        System.out.println();
    }

    @Override
    public void markNotDone() {
        System.out.print("[T]");
        super.markNotDone();
        System.out.println();
    }

    @Override
    public String save() {
        return "T|"+ super.save();
    }
}
