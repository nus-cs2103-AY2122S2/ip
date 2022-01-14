import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void logo() {
        System.out.println(" ____                         _____       _\n"
                + "| |_) |_   _ ___ ___ _   _  | (___   __ _| | ____ _\n"
                + "|  _ <| | | / __/ __| | | |  \\___ \\ / _` | |/ / _` |\\\n"
                + "| |_) | |_| \\__ \\__ \\ |_| |  ____) | (_| |   < (_| |\n"
                + "|____/ \\__,_|___/___/\\__, | |_____/ \\__,_|_|\\_\\__,_|\n"
                + "                      __/ |\n"
                + "                      |___/ \n");
    }

    public static void mascot() {
        System.out.println("⣿⣿⣿⣿⣿⣿⣿⢿⠟⠛⠿⠻⠿⠿⠟⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "⣿⣿⣿⡿⠛⢙⣨⣥⣶⣶⣿⢿⣿⣿⣷⣦⣅⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "⣿⣿⠟⢀⡴⠟⠋⢉⣀⣠⣤⣤⣤⣀⠉⠻⣿⣧⡈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "⣿⣿⠀⠁⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣷⠀⢻⣿⣇⠝⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "⣿⣿⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡀⣼⡿⠟⠀⠙⣛⣬⠱⣿⣿⣿⣿⣿⣿\n"
                + "⣿⣿⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋⢀⠄⠁⣠⣶⣾⣿⣿⣿⡆⣼⣿⣿⣿⣿⣿\n"
                + "⣿⣿⠀⣀⠙⣛⣛⣻⠛⠋⣉⣢⣤⣾⠃⣰⡄⠸⣿⣿⣿⣿⣿⣷⠘⣿⣿⣿⣿⣿\n"
                + "⣿⣿⣤⢹⣷⣶⣶⣶⣾⣿⣿⣿⣿⣿⡄⠸⣷⠀⢻⣿⣿⡿⠟⠛⠡⣿⣿⣿⣿⣿\n"
                + "⣿⣿⣿⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠄⠻⠇⢈⠁⠀⠀⠲⠠⠞⠿⣿⣿⣿⣿\n"
                + "⣿⣿⣿⣷⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⢤⠀⠀⢲⣿⣿⣿⣷⣤⡉⣻⣿⣿\n"
                + "⣿⣿⣿⣿⣧⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣳⡀⢻⣿⣿⣿⣿⣷⠐⣿⣿\n"
                + "⣿⣿⣿⣿⣿⣯⡈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⡇⡆⣿⣿⣿⣿⡟⣀⣿⣿\n"
                + "⣿⣿⣿⣿⣿⣿⣷⡀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⢃⡿⠿⠛⡋⣀⣾⣿⣿\n"
                + "⣿⣿⣿⣿⣿⣿⣿⣷⣀⠹⣿⣿⣿⣿⣿⣿⣿⠿⠋⢁⣠⣿⡦⠐⠀⢈⡙⢿⣿⣿\n"
                + "⣿⣿⣿⣿⣿⣿⣿⣿⠋⢀⣿⣿⣿⣿⠟⢃⣤⣤⡀⠻⣿⣇⣠⣴⡿⠄⠹⣧⡸⣿\n"
                + "⣿⣿⣿⣿⣿⣿⡿⠃⢠⣾⣿⣿⡿⢋⣤⣿⣿⣿⣿⣄⠈⢿⡿⠋⣠⣤⣀⠈⣡⣿\n"
                + "⣿⣿⣿⠅⣀⣈⠁⣰⣿⣿⡿⠋⣤⣾⣿⣿⣿⣿⣿⣿⣷⣵⣂⣽⣿⣿⣿⣿⣿⣿\n"
                + "⣿⣿⣿⣄⠘⢿⣿⣿⠟⠋⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
                + "⣿⣿⣿⣿⣷⣤⣬⣅⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n"
        );
    }

    public static void main(String[] args) {
        // Create an empty array list
        ArrayList<Task> taskArr = new ArrayList<>();

        line();
        System.out.println("Tell me... have you seen a RED imposter among us?");
        System.out.println("If you have seen this SUSSY imposter, please let me know immediately... otherwise how may I be of assistance?");
        line();

        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine(); // read string

            // Create a String array to read mark/unmark function
            String[] strs =input.split(" ");

            if (input.equalsIgnoreCase("bye")) {
                line();
                System.out.println("Better watch out for the imposter AMONG US!");
                mascot();
                logo();
                line();
                break;
            } else if(input.equalsIgnoreCase("list")) {
                line();
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskArr.size(); i++) {
                    System.out.println("     " + (i + 1) + "." + taskArr.get(i));
                }
                line();
            } else if(strs[0].equalsIgnoreCase("mark")) {
                line();
                int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
                Task currTask = taskArr.get(listIndex - 1);
                currTask.mark();
                System.out.println("     Your progressive bar has increased! Keep going to stop the imposter!");
                System.out.println("     " + currTask);
                line();
            } else if(strs[0].equalsIgnoreCase("unmark")) {
                line();
                int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
                Task currTask = taskArr.get(listIndex - 1);
                currTask.unmark();
                System.out.println("     Uh oh, every task left undone lets the imposter edge closer...");
                System.out.println("     " + currTask);
                line();
            } else {
                Task task = new Task(input);
                taskArr.add(task);
                line();
                System.out.println("     added: " + task);
                line();
            }
        }
    }
}








