import java.util.Scanner;

public class Juke {
    private static final Juke INSTANCE = new Juke();
    
    private String[] taskList;
    private final int taskListSize = 100;
    private int index;
    
    public Juke() {
        this.taskList = new String[this.taskListSize];
        this.index = 0;
    }
    
    private void run(Scanner input) {
        this.greet();
        while(true) {
            this.printMarker();
            String cmd = input.nextLine();
            
            switch(cmd) {
                case "bye":
                    this.bye();
                    break;
                case "list":
                    this.displayTaskList();
                    break;
                default:
                    this.addToTaskList(cmd);
            }
        }
    }
    
    private void greet() {
        String logo = "                                                             _____   \n" +
            "          _____  ______   _____     ______   _______    _____\\    \\  \n" +
            "         |\\    \\_\\     \\  \\    \\   |\\     \\  \\      \\  /    / |    | \n" +
            "         \\ \\     \\\\    |  |    |    \\\\     \\  |     /|/    /  /___/| \n" +
            "          \\|      ||   |  |    |     \\|     |/     //|    |__ |___|/ \n" +
            "           |      ||    \\_/   /|      |     |_____// |       \\       \n" +
            "   ______  |      ||\\         \\|      |     |\\     \\ |     __/ __    \n" +
            "  /     / /      /|| \\         \\__   /     /|\\|     ||\\    \\  /  \\   \n" +
            " |      |/______/ | \\ \\_____/\\    \\ /_____/ |/_____/|| \\____\\/    |  \n" +
            " |\\_____\\      | /   \\ |    |/___/||     | / |    | || |    |____/|  \n" +
            " | |     |_____|/     \\|____|   | ||_____|/  |____|/  \\|____|   | |  \n" +
            "  \\|_____|                  |___|/                          |___|/   \n";
        System.out.println("Hello from\n" + logo);
    }
    
    private void bye() {
        this.formattedPrint("See you again!");
        System.exit(0);
    }
    
    private void echo(String text) {
        this.formattedPrint(text);
    }
    
    private void addToTaskList(String text) {
        if (index < this.taskListSize && index >= 0) {
            this.taskList[index] = text;
            this.formattedPrint("Task added: " + text);
            index++;
            return;
        }
        this.formattedPrint("Task list is full, task could not be added.");
    }
    
    private void displayTaskList() {
        if (index > 0) {
            String str = "";
            for (int i = 0; i < index; i++) {
                str += (i + 1) + ". " + this.taskList[i] + "\n";
            }
            this.formattedPrint(str.stripTrailing());
            return;
        }
        this.formattedPrint("Task list is empty.");
    }
    
    private void formattedPrint(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________\n");
    }
    
    private void printMarker() {
        System.out.print("\u232c ");
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        INSTANCE.run(input);
        
        input.close();
    }
}
