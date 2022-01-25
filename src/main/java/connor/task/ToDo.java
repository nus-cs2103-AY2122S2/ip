package connor.task;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc, TaskType.TODO);
    }

    @Override
    public String toString() {
        return getType() + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ToDo)) {
            return false;
        } else {
            ToDo t = (ToDo) o;
            return this.getDesc().equals(t.getDesc())
                    && this.isDone().equals(t.isDone());
        }
    }
}
