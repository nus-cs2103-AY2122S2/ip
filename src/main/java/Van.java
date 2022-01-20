import java.util.*;

public class Van {
    public static void main(String[] args) {
        int counter = 0;
        String[] parse, para;
        String command = null;
        Task[] tasks = new Task[100];
        Scanner input = new Scanner(System.in);
        String divide = "-------------------------------------------";
        System.out.println("Hello I am Van");
        System.out.println("How may i assist you");
        System.out.println(divide);
        command = input.nextLine();
        parse = command.split("\\s+", 2);
        System.out.println(divide);
        while (!command.equalsIgnoreCase("bye")) {
            switch(parse[0].toLowerCase()) {
                case "list":
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + ". " + tasks[i].getStatus());
                    }
                    System.out.println(divide);
                    break;
                case "deadline":
                    para = parse[1].split("/by");
                    tasks[counter] = new Deadline(para[0], para[1]);
                    System.out.println("Task added");
                    System.out.println("  " + tasks[counter].getStatus());
                    counter++;
                    System.out.println(counter + " tasks pending.");
                    System.out.println(divide);
                    break;
                case "event":
                    para = parse[1].split("/at");
                    tasks[counter] = new Event(para[0], para[1]);
                    System.out.println("Task added");
                    System.out.println("  " + tasks[counter].getStatus());
                    counter++;
                    System.out.println(counter + " tasks pending.");
                    System.out.println(divide);
                    break;
                case "todo":
                    tasks[counter] = new Todo(parse[1]);
                    System.out.println("Task added");
                    System.out.println("  " + tasks[counter].getStatus());
                    counter++;
                    System.out.println(counter + " tasks pending.");
                    System.out.println(divide);
                    break;
                case "mark":
                    tasks[Integer.parseInt(parse[1]) - 1].setDone();
                    break;
                case "unmark":
                    tasks[Integer.parseInt(parse[1]) - 1].setunDone();
                    break;
            }
            command = input.nextLine();
            parse = command.split("\\s+", 2);
            System.out.println(divide);
        }
        System.out.println("Bye");
    }
}
