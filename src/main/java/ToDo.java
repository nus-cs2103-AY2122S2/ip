public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    public String getPrefix() {
        return "T";
    }

    public String getPostfix() {
        return "00000000";
    }


    @Override
    public String toString() {
        String prefix = "[T]";
        return prefix + super.toString();
    }
}
