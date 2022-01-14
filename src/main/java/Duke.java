import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "Luke";
    private static final String LOGO = "  _           _        \n"
            +" | |         | |       \n"
            +" | |    _   _| | _____ \n"
            +" | |   | | | | |/ / _ \\\n"
            +" | |___| |_| |   <  __/\n"
            +" |______\\__,_|_|\\_\\___|\n";
    private Task[] taskList;
    private int taskIndex;

    Duke() {
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
                + String.format("[%s] %s\n", Duke.BOT_NAME, output)
                + "======================================================================";
        System.out.println(msg);
    }

    public void start() {
        Command cmd = new Command();
        Scanner sc = new Scanner(System.in);
        greeting();
        do {
            cmd = Command.parseCommand(sc.nextLine());
            switch (cmd.getCommandType()) {
                case EXIT:
                    break;
                case LIST:
                    printTaskList();
                    break;
                case ADD:
                    printOutput(String.format("I have added \"%s\" into list.", cmd.getArguments()));
                    taskList[taskIndex++] = new Task(cmd.getArguments());
                    break;
                case MARK:
                    int index = Integer.valueOf(cmd.getArguments()) - 1;
                    boolean isDone = taskList[index].toggleIsDone();
                    String msg = isDone ? "Great! I have marked this task as done." : "Noted, I've mark this task as not done yet.";
                    printOutput(msg);
                    break;
            }
        } while(!cmd.isExitCmd());
        farewell();
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
