public class Reminder {
    String[] reminders = new String[100];
    int pointer = 0;

    public Reminder() {

    }

    public String add(String task) {
        reminders[pointer] = task;
        pointer++;
        return "added: " + task;
    }

    public void list() {
        for (int i = 0; i < pointer; i++) {
            System.out.println("\t" + (i+1) + ". " + reminders[i]);
        }
    }
}