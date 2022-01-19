public class ToDo extends Task {
    public ToDo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static ToDo createTask(String[] tokens) {
        String item = "";
        for (String token : tokens) {
            if (token.equals("todo")) {
                continue;
            } else {
                item += token + " ";
            }
        }
        return new ToDo(item.trim());
    }
}
