import java.util.Scanner;


public class Duke {
    static String line = "------------------------------------";
    static Scanner scanner = new Scanner(System.in);
    static Task[] arr = new Task[100];

    public static void main(String[] args) {
        int counter = 0;
        String logo = "/\\___ \\                            __           \n"
                + "\\/__/\\ \\     __     _ __   __  __ /\\_\\    ____  \n"
                + "   _\\ \\ \\  /'__`\\  /\\`'__\\/\\ \\/\\ \\\\/\\ \\  /',__\\ \n"
                + "  /\\ \\_\\ \\/\\ \\L\\.\\_\\ \\ \\/ \\ \\ \\_/ |\\ \\ \\/\\__, `\\\n"
                + "  \\ \\____/\\ \\__/.\\_\\\\ \\_\\  \\ \\___/  \\ \\_\\/\\____/\n"
                + "   \\/___/  \\/__/\\/_/ \\/_/   \\/__/    \\/_/\\/___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you today?");
        System.out.println(line);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Goodbye! I'll be here if you need anything else.");
                System.out.println(line);
                break;
            }
            else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ".[" + arr[i].toggleDone() + "] " + arr[i].name +"\n");
                }
                System.out.println(line);
            }
            else if (input.contains("mark") || input.contains("unmark")) {
                System.out.println(line);
                String s = input.replaceAll("\\D+","");
                int clean = Integer.parseInt(s) - 1;
                if (arr[clean] == null) {
                    System.out.println("Error! No tasked added");
                }
                else if (input.toCharArray()[0] != 'u') {
                    arr[clean].done = true;
                    System.out.println("Nice! I've marked this task as done:\n " +
                            "   ["  + arr[clean].toggleDone() + "] " + arr[clean].name);
                }
                else {
                    arr[clean].done = false;
                    System.out.println("OK, I've marked this task as not done yet:\n " +
                            "   ["  + arr[clean].toggleDone() + "] " + arr[clean].name);
                }
                System.out.println(line);
            }
            else {
                System.out.println(line);
                Task newTask = new Task(input);
                System.out.println("added: " + newTask.name);
                arr[counter] = newTask;
                counter += 1;
                System.out.println(line);
            }
        }
    }
}


