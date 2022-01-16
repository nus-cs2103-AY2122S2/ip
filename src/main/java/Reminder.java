public class Reminder {
    Task[] reminders = new Task[100];
    int pointer = 0; // points to next empty slot in the array

    public void add(Task task) {
        reminders[pointer++] = task;
        System.out.println("\tadded: " + task.description);
    }

    public void mark(int position, boolean done) {
        reminders[--position].setDone(done);
    }

    public void list() {
        System.out.println("\tHere are the tasks in your list: ");
        if (pointer == 0) {
            System.out.println("\tYou have no task in your list.");
        } else {
            for (int i = 0; i < pointer; i++) {
                Task task = reminders[i];
                System.out.println("\t" + (i + 1) + ". [" + task.getStatusIcon() + "] " + task.description);
            }
        }
    }
}