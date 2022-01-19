public class Task {

    int id;
    String name;
    boolean status;

    public Task(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        String currentStatus = this.status == true ? "X" : " ";
        String output = id + ". "
                + "[" + currentStatus + "] "
                + this.name;

        return output;
    }
}
