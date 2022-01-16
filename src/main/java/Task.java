public class Task {
    private int Id;
    private String name;
    private boolean done;

    public Task(int id, String n, boolean d) {
        Id = id;
        name = n;
        done = d;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return done;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.getId()).append(".");
        if (this.done) {
            res.append("[X] ");
        } else {
            res.append("[] ");
        }
        res.append(this.name);
        res.append("\n");
        return res.toString();
    }
}
