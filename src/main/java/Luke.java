import java.util.Scanner;

public class Luke {
    public static final String BOT_NAME = "Luke";
    private static final String LOGO = "  _           _        \n"
            +" | |         | |       \n"
            +" | |    _   _| | _____ \n"
            +" | |   | | | | |/ / _ \\\n"
            +" | |___| |_| |   <  __/\n"
            +" |______\\__,_|_|\\_\\___|\n";
    private Task[] taskList;
    private int taskIndex;

    Luke() {
        taskList = new Task[100];
        taskIndex = 0;
    }

    public void greeting() {
        System.out.println("Hello! I am \n" + LOGO);
        printOutput("How can I help you?");
    }

    public void farewell() {
        printOutput("I'll take my leave... Do you know who is my father?");
    }

    public void printTaskList() {
        String msg = "Currently, you have the following tasks:\n";
        for (int i = 0; i < taskIndex; i++) {
            msg += String.format("%d. %s\n", i+1, taskList[i]);
        }
        printOutput(msg.stripTrailing());
    }
    public void printOutput(String output) {
        String msg = "======================================================================\n"
                + String.format("[%s] %s\n", Luke.BOT_NAME, output)
                + "======================================================================";
        System.out.println(msg);
    }

    public void start() {
        Command cmd;
        Scanner sc = new Scanner(System.in);
        greeting();
        do {
            cmd = Command.parseCommand(sc.nextLine());
            switch (cmd.getCommandAction().getCommandActionType()) {
                case READ:
                    printTaskList();
                    break;
                case ADD:
                    Task task;
                    switch (cmd.getCommandAction()) {
                        case DEADLINE:
                            task = new Deadline(cmd.getArguments());
                            break;
                        case EVENT:
                            task = new Event(cmd.getArguments());
                            break;
                        default:
                            task = new ToDo(cmd.getArguments());
                            break;
                    }
                    taskList[taskIndex++] = task;
                    printOutput(String.format("I have added the following task into list: \n %s \n now you have %d tasks in the list.", task, taskIndex));
                    break;
                case UPDATE:
                    int index = Integer.valueOf(cmd.getArgumentByKey("index")) - 1;
                    switch (cmd.getCommandAction()) {
                        case MARK:
                            taskList[index].markAsDone();
                            printOutput("Great! I have marked this task as done.");
                            break;
                        case UNMARK:
                            taskList[index].unmarkAsDone();
                            printOutput("Noted, I've mark this task as not done yet.");
                            break;
                    }
                    break;
                default:
                    break;
            }
        } while(!cmd.isExitCmd());
        farewell();
        sc.close();
    }

    public static void main(String[] args) {
        Luke luke = new Luke();
        luke.start();
    }
}
