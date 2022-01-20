public class InputList {

    private int count;
    private Task[] tasks;

    public InputList() {
        count = 0;
        tasks = new Task[100];
    }

    public void printList() {
        for(int i = 0; i < count; i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks[i].toString());
        }
    }

    public void add(Task newTask) {
        this.tasks[count] = newTask;
        count++;
        System.out.println("added: " + newTask.getDescription());
    }

    public void mark(int index) {
        tasks[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index - 1].toString());
    }

    public void unmark(int index) {
        tasks[index - 1].markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index - 1].toString());
    }
}
