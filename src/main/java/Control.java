import java.util.ArrayList;

public class Control {
    private ArrayList<Task> tasks;
    public Control() {
        String start =
                "________________________________\n"
                        + "Hello! I am Duke_two.\n"
                        + "Your Personal Assistant.\n"
                        + "What can I do for you?\n"
                        + "________________________________";
        this.tasks = new ArrayList<>();
        System.out.println(start);
    }

    public void add(String taskStr) {
        Task task = new Task(taskStr, false);
        this.tasks.add(task);
        System.out.println("________________________________");
        System.out.println("From Duke_two: \n\tAdded to your tasks: " + task.toString());
    }

    public void bye() {
        String bye = "GoodBye! I hope to see you again!";
        System.out.println(bye);
    }

    public void list() {
        int leng = tasks.toArray().length;
        String isTaskCheck = "";
        for (int i = 0; i < leng; i++) {
            Task task = tasks.get(i);
            int num = i + 1;
            isTaskCheck = task.getChecked() ? "X" : " ";
            System.out.println(num + ": [" + isTaskCheck + "] " + task.toString());
        }
    }

    public void taskCheck(String taskStr) {
        String[] taskArr = taskStr.split(" ");
        int index = Integer.parseInt(taskArr[1]) - 1;
        Task task = this.tasks.get(index);
        String isTaskCheck = "";
        if (taskArr[0].equals("mark")) {
            task.setChecked(true);
            isTaskCheck = task.getChecked() ? "X" : " ";
            System.out.println("Nice! I've marked this task as done: \n\t[" + isTaskCheck + "] " + task.toString());
        } else {
            this.tasks.get(index).setChecked(false);
            isTaskCheck = task.getChecked() ? "X" : " ";
            System.out.println("Alright, I've marked this task as not done yet: \n\t[" + isTaskCheck + "] " + task.toString());
        }
    }


}
