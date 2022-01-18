import java.util.Arrays;
import java.util.Scanner;

public class Juke {
    private static final Juke INSTANCE = new Juke();
    
    private Task[] taskList;
    private final int taskListSize = 100;
    private int index;
    
    public Juke() {
        this.taskList = new Task[this.taskListSize];
        this.index = 0;
    }
    
    private void run(Scanner input) {
        this.greet();
        while(true) {
            this.printMarker();
            
            String[] args = input.nextLine().stripLeading().stripTrailing().split("\\s+");
            if (args.length > 0) {
                switch (args[0]) {
                    case "":
                        break;
                    case "bye":
                        this.bye();
                        break;
                    case "list":
                        this.displayTaskList();
                        break;
                    case "mark":
                        if (args.length > 1) {
                            try {
                                this.markTaskAsDone(Integer.parseInt(args[1]));
                            } catch (NumberFormatException e) {
                                this.formattedPrint("Invalid argument for mark.");
                            }
                        } else {
                            this.formattedPrint("Please specify an argument for mark.");
                        }
                        break;
                    case "unmark":
                        if (args.length > 1) {
                            try {
                                this.markTaskAsNotDone(Integer.parseInt(args[1]));
                            } catch (NumberFormatException e) {
                                this.formattedPrint("Invalid argument for unmark.");
                            }
                        } else {
                            this.formattedPrint("Please specify an argument for unmark.");
                        }
                        break;
                    default:
                        this.addToTaskList(String.join(" ", args));
                }
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
            this.taskList[index] = new Task(text);
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
                str += (i + 1) + ". " + this.taskList[i].fancyPrint() + "\n";
            }
            this.formattedPrint(str.stripTrailing());
            return;
        }
        this.formattedPrint("Task list is empty.");
    }
    
    private void markTaskAsDone(int arg) {
        if (arg <= 0 || arg > this.index) {
            this.formattedPrint("Invalid index for mark.");
            return;
        }
        this.taskList[arg - 1].markAsDone();
        this.formattedPrint("Marked task \u00ab" + this.taskList[arg - 1].toString() + "\u00bb as done.");
    }
    
    private void markTaskAsNotDone(int arg) {
        if (arg <= 0 || arg > this.index) {
            this.formattedPrint("Invalid index for mark.");
            return;
        }
        this.taskList[arg - 1].markAsNotDone();
        this.formattedPrint("Marked task \u00ab" + this.taskList[arg - 1].toString() + "\u00bb as not done.");
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
