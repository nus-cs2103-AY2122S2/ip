import java.util.ArrayList;
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
            
            String[] args = input.nextLine().strip().split("\\s+");
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
                    case "todo":
                        this.addTodoToTaskList(args);
                        break;
                    case "deadline":
                        this.addDeadlineToTaskList(args);
                        break;
                    case "event":
                        this.addEventToTaskList(args);
                        break;
                    case "mark":
                        this.markTaskAsDone(args);
                        break;
                    case "unmark":
                        this.markTaskAsNotDone(args);
                        break;
                    default:
                        this.echo(args);
                }
            }
        }
    }
    
    private void greet() {
        String logo = "                                                             _____   \n"
                + "          _____  ______   _____     ______   _______    _____\\    \\  \n"
                + "         |\\    \\_\\     \\  \\    \\   |\\     \\  \\      \\  /    / |    | \n"
                + "         \\ \\     \\\\    |  |    |    \\\\     \\  |     /|/    /  /___/| \n"
                + "          \\|      ||   |  |    |     \\|     |/     //|    |__ |___|/ \n"
                + "           |      ||    \\_/   /|      |     |_____// |       \\       \n"
                + "   ______  |      ||\\         \\|      |     |\\     \\ |     __/ __    \n"
                + "  /     / /      /|| \\         \\__   /     /|\\|     ||\\    \\  /  \\   \n"
                + " |      |/______/ | \\ \\_____/\\    \\ /_____/ |/_____/|| \\____\\/    |  \n"
                + " |\\_____\\      | /   \\ |    |/___/||     | / |    | || |    |____/|  \n"
                + " | |     |_____|/     \\|____|   | ||_____|/  |____|/  \\|____|   | |  \n"
                + "  \\|_____|                  |___|/                          |___|/   \n";
        System.out.println(logo);
        this.formattedPrint("Greetings Executor!");
    }
    
    private void bye() {
        this.formattedPrint("See you again!");
        System.exit(0);
    }
    
    private void echo(String[] args) {
        this.formattedPrint(String.join(" ", args));
    }
    
    private void addTodoToTaskList(String[] args) {
        if (args.length > 1) {
            if (index < this.taskListSize && index >= 0) {
                String text = "";
                for (int i = 1; i < args.length; i++) {
                    text += args[i] + " ";
                }
                text = text.strip();
                this.taskList[index] = new Todo(text);
                this.formattedPrint("Todo added: " + text);
                index++;
            } else {
                this.formattedPrint("Task list is full, todo could not be added.");
            }
        } else {
            this.formattedPrint("Please specify an argument for todo.");
        }
    }
    
    private void addDeadlineToTaskList(String[] args) {
        if (args.length > 3) {
            boolean isValid = false;
            int argIdx = 0;
            for (int j = 2; j < args.length - 1; j++) {
                if (args[j].equals("-by")) {
                    isValid = true;
                    argIdx = j;
                    break;
                }
            }
            if (isValid) {
                if (index < this.taskListSize && index >= 0) {
                    String text = "";
                    for (int i = 1; i < argIdx; i++) {
                        text += args[i] + " ";
                    }
                    text = text.strip();
                    String time = "";
                    for (int i = argIdx + 1; i < args.length; i++) {
                        time += args[i] + " ";
                    }
                    time = time.strip();
                    this.taskList[index] = new Deadline(text, time);
                    this.formattedPrint("Deadline added: " + text);
                    index++;
                } else {
                    this.formattedPrint("Task list is full, deadline could not be added.");
                }
            } else {
                this.formattedPrint("Missing argument for deadline");
            }
        } else {
            this.formattedPrint("Please specify an argument for deadline.");
        }
    }
    
    private void addEventToTaskList(String[] args) {
        if (args.length > 3) {
            boolean isValid = false;
            int argIdx = 0;
            for (int j = 2; j < args.length - 1; j++) {
                if (args[j].equals("-at")) {
                    isValid = true;
                    argIdx = j;
                    break;
                }
            }
            if (isValid) {
                if (index < this.taskListSize && index >= 0) {
                    String text = "";
                    for (int i = 1; i < argIdx; i++) {
                        text += args[i] + " ";
                    }
                    text = text.strip();
                    String time = "";
                    for (int i = argIdx + 1; i < args.length; i++) {
                        time += args[i] + " ";
                    }
                    time = time.strip();
                    this.taskList[index] = new Event(text, time);
                    this.formattedPrint("Event added: " + text);
                    index++;
                } else {
                    this.formattedPrint("Task list is full, event could not be added.");
                }
            } else {
                this.formattedPrint("Missing argument for event");
            }
        } else {
            this.formattedPrint("Please specify an argument for event.");
        }
    }
    
    private void displayTaskList() {
        if (index > 0) {
            String str = "";
            for (int i = 0; i < index; i++) {
                str += (i + 1) + ". " + this.taskList[i].toString() + "\n";
            }
            this.formattedPrint(str.stripTrailing());
        } else {
            this.formattedPrint("Task list is empty.");
        }
    }
    
    private void markTaskAsDone(String[] args) {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.index) {
                    this.formattedPrint("Invalid index for mark.");
                    return;
                }
                this.taskList[idx - 1].markAsDone();
                this.formattedPrint("Marked task \u00ab" + this.taskList[idx - 1].getDescription()
                    + "\u00bb as done.");
            } catch (NumberFormatException e) {
                this.formattedPrint("Invalid argument for mark.");
            }
        } else {
            this.formattedPrint("Please specify an argument for mark.");
        }
    }
    
    private void markTaskAsNotDone(String[] args) {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.index) {
                    this.formattedPrint("Invalid index for mark.");
                    return;
                }
                this.taskList[idx - 1].markAsNotDone();
                this.formattedPrint("Marked task \u00ab" + this.taskList[idx - 1].getDescription()
                    + "\u00bb as not done.");
            } catch (NumberFormatException e) {
                this.formattedPrint("Invalid argument for unmark.");
            }
        } else {
            this.formattedPrint("Please specify an argument for unmark.");
        }
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
