public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    public String getPrefix() {
        return "T";
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public String getTime() {
        return "";
    }


    @Override
    public String toString() {
        String prefix = "[T]";
        return prefix + super.toString();
    }
}
