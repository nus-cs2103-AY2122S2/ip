public class Todo extends Task {

    public Todo(String activity, String type ) {
        super(activity, type);
    }

    @Override
    public void getStatus() {
        if (this.status == 0) {
            System.out.println("[" + type + "][] " + activity);
        } else {
            System.out.println("[" + type + "][X] " + activity);
        }
    }
}
