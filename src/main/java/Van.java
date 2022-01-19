import java.util.*;

public class Van {
    public static void main(String[] args) {
        int counter = 0;
        String[] parse;
        String command = null;
        Task[] tasks = new Task[100];
        Scanner input = new Scanner(System.in);
        String divide = "-------------------------------------------";
        System.out.println("Hello I am Van");
        System.out.println("How may i assist you");
        System.out.println(divide);
        command = input.nextLine();
        parse = command.split("\\s+");
        System.out.println(divide);
        while (!command.equalsIgnoreCase("bye")) {
            switch(command.toLowerCase()) {
                case "list":
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + ". " + tasks[i].getStatus());
                    }
                    System.out.println(divide);
                    break;
                default:
                    switch(parse[0]) {
                        case "mark":
                            tasks[Integer.parseInt(parse[1]) - 1].setDone();
                            break;
                        case "unmark":
                            tasks[Integer.parseInt(parse[1]) - 1].setunDone();
                            break;
                        default:
                            tasks[counter] = new Task(command);
                            counter++;
                            System.out.println("added: " + command);
                            System.out.println(divide);
                    }

            }
            command = input.nextLine();
            parse = command.split("\\s+");
            System.out.println(divide);
        }
        System.out.println("Bye");
    }
}
