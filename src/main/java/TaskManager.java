import java.util.ArrayList;

public class TaskManager {
    private final String line = "-------------------------------------------";
    private ArrayList<Task> tasks;
    private boolean status;


    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.status = true;
        String start = "Hello there! I'm \n" +
                " __          ___      .______      .______     ____    ____ \n" +
                "|  |        /   \\     |   _  \\     |   _  \\    \\   \\  /   / \n" +
                "|  |       /  ^  \\    |  |_)  |    |  |_)  |    \\   \\/   /  \n" +
                "|  |      /  /_\\  \\   |      /     |      /      \\_    _/   \n" +
                "|  `----./  _____  \\  |  |\\  \\----.|  |\\  \\----.   |  |     \n" +
                "|_______/__/     \\__\\ | _| `._____|| _| `._____|   |__|     \n" +
                "                                                            \n"+
                "What can I do for you?";
        System.out.println(line);
        System.out.println(start);
        System.out.println(line);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void execute(String[] spliced) {
        switch (spliced[0]) {
            case "bye":
                this.status = false;
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;

            case "list":
                System.out.println(this);
                break;

            case "todo":
                Task newTodo = new ToDo(spliced[1]);
                tasks.add(newTodo);
                System.out.println(line);
                System.out.println("Got it, I added: \n"+ newTodo);
                System.out.printf("Now you have %d item(s) in your list \n", tasks.size());
                System.out.println(line);
                break;

            case "deadline":
                Task newDeadline = new Deadline(spliced[1]);
                tasks.add(newDeadline);
                System.out.println(line);
                System.out.println("Got it, I added: \n"+ newDeadline);
                System.out.printf("Now you have %d items in your list \n", tasks.size());
                System.out.println(line);
                break;

            case "event":
                Task newEvent = new Event(spliced[1]);
                tasks.add(newEvent);
                System.out.println(line);
                System.out.println("Got it, I added: \n"+ newEvent);
                System.out.printf("Now you have %d items in your list \n", tasks.size());
                System.out.println(line);
                break;

            case "unmark":
                Integer index = Integer.parseInt(spliced[1]);
                if (tasks.get(index - 1) != null) {
                    tasks.get(index - 1).unmark();
                }
                break;

            case "mark":
                Integer index2 = Integer.parseInt(spliced[1]);
                if (tasks.get(index2 - 1) != null) {
                    tasks.get(index2 - 1).mark();
                }
                break;
        }
    }

    public boolean open() {
        return this.status;
    }

    @Override
    public String toString() {
        String s = line + "\n";
        s += "Here's your list, Good Sir:\n";
        for (int i = 0; i < tasks.size(); i++) {
            s += i + 1 + ". "+ tasks.get(i).toString() + "\n";
        }
        s += line;
        return s;
    }


}
