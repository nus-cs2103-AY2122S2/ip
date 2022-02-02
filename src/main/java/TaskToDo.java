public class TaskToDo extends Task {
    TaskToDo(String name) {
        super(name);
    }

    public String getPrefix() {
        return "T";
    }

    @Override
    public String getDate() {
        return "00000000";
    }

    @Override
    public String getTime() {
        return "0000";
    }


    @Override
    public String toString() {
        String prefix = "[T]";
        return prefix + super.toString();
    }
}
