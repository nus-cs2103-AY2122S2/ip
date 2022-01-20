import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        List<Task> tasks = new ArrayList<Task>();

        while (true) {
            try {
                String[] instruction = sc.nextLine().split(" ");
                switch(instruction[0]){
                    case "bye": {
                        System.out.println("Bye. Hope to see you again soon!\n");
                        return;
                    }

                    case "list": {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + ". " + tasks.get(i));
                        }
                        break;
                    }

                    case "mark": {
                        int taskNum = Integer.parseInt(instruction[1]);
                        tasks.get(taskNum - 1).markAsDone();
                        System.out.println(String.format("Nice!, I have marked this task as done: \n %s", tasks.get(taskNum - 1)));
                        break;
                    }

                    case "unmark": {
                        int taskNum = Integer.parseInt(instruction[1]);
                        tasks.get(taskNum - 1).markAsNotDone();
                        System.out.println(String.format("Ok, I have marked this task as not done: \n %s", tasks.get(taskNum - 1)));
                        break;
                    }

                    case "todo": {
                        if(instruction.length == 1) {
                            throw new InvalidArgumentException();
                        }
                        String instructionName = String.join(" ", Arrays.copyOfRange(instruction, 1, instruction.length));
                        Task newTodo = new Todo(instructionName);
                        tasks.add(newTodo);
                        System.out.println("Got it. I've added this task\n" + newTodo);
                        System.out.println(String.format("You have %d tasks in the list", tasks.size()));
                        break;
                    }

                    case "event": {
                        if(instruction.length == 1) {
                            throw new InvalidArgumentException();
                        }
                        int index = Arrays.asList(instruction).indexOf("/at");
                        String instructionName = String.join(" ", Arrays.copyOfRange(instruction, 1, index));
                        String time = String.join(" ", Arrays.copyOfRange(instruction, index + 1, instruction.length));
                        Task newEvent = new Event(instructionName, time);
                        tasks.add(newEvent);
                        System.out.println("Got it. I've added this task\n" + newEvent);
                        System.out.println(String.format("You have %d tasks in the list", tasks.size()));
                        break;
                    }

                    case "deadline": {
                        if(instruction.length == 1) {
                            throw new InvalidArgumentException();
                        }
                        int index = Arrays.asList(instruction).indexOf("/by");
                        String instructionName = String.join(" ", Arrays.copyOfRange(instruction, 1, index));
                        String time = String.join(" ", Arrays.copyOfRange(instruction, index + 1, instruction.length));
                        Task newDeadline = new Deadline(instructionName, time);
                        tasks.add(newDeadline);
                        System.out.println("Got it. I've added this task\n" + newDeadline);
                        System.out.println(String.format("You have %d tasks in the list", tasks.size()));
                        break;
                    }

                    default: {
                        throw new InvalidCommandException();
                    }
                }
                System.out.println("");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
