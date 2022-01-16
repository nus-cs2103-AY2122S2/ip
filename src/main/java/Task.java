public class Task {
    String name;
    int status;

    public Task (String name, int status) {
        this.name = name;
        this.status = status;
    }

    public void taskDone() {
        System.out.println("Nice! I've marked this task as done");
        System.out.println("[X] " + name);
    }
    public void taskUndo() {
        System.out.println("Ooof! I've marked this task as undone");
        System.out.println("[] " + name);
    }

    public void getStatus() {
        if (this.status == 0) {
            System.out.println("[] " + name);
        } else {
            System.out.println("[X] " + name);
        }
    }

}
