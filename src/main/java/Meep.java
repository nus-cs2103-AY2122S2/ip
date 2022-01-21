import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Meep {
    public static void main(String[] args) {
        Utils.printLogo();
        String userInput = "In";

        FastIO sc = new FastIO();
        List<Task> taskList = new ArrayList<>();

        try {
            taskList.addAll(Utils.readTaskFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        while (true) {
            System.out.print("You:");
            userInput = sc.nextLine();

            String[] parsedInput = null;
            try {
                parsedInput = Utils.parseUserInput(userInput, taskList);
            } catch (InvalidInputException e) {
                System.out.print("Meep:");
                System.out.println(e.getMessage());
                continue;
            }
            String userCommand = parsedInput[0];

            if (userCommand.equals(Command.BYE.val)) {
                try {
                    Utils.saveTasktoFile(taskList);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Meep:Bye. Hope to see you again soon!");
                break;
            } else if (userCommand.equals(Command.LIST.val)) {

                // print all tasks
                if(parsedInput.length == 1) {
                    System.out.println("Meep:");
                    Utils.printTaskList(taskList);
                }else{
                    // print task before given date
                    try {
                        LocalDateTime formattedDate=Utils.parseDate(parsedInput[1]);
                        Utils.printTaskList(taskList,formattedDate);
                    } catch (DateTimeException e) {
                        System.out.print("Meep:");
                        System.out.println(e.getMessage());
                    }

                }

            } else if (userCommand.equals(Command.MARK.val)) {
                Task task = taskList.get(Integer.parseInt(parsedInput[1]) - 1);
                task.markDone();
                System.out.println("Meep:Nice! I've marked this task as done:");
                System.out.println("     " + task.toString());
            } else if (userCommand.equals(Command.UNMARK.val)) {
                Task task = taskList.get(Integer.parseInt(parsedInput[1]) - 1);
                task.unmarkDone();

                System.out.println("Meep:OK, I've marked this task as not done yet:");
                System.out.println("     " + task.toString());
            } else if (userCommand.equals(Command.DEADLINE.val)) {
                try {
                    Deadline deadline = new Deadline(parsedInput[1], Utils.parseDate(parsedInput[3]));
                    taskList.add(deadline);

                    System.out.println("Meep:Got it. I've added this task:");
                    System.out.println(deadline.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (DateTimeException e) {
                    System.out.print("Meep:");
                    System.out.println(e.getMessage());
                }
            } else if (userCommand.equals(Command.EVENT.val)) {
                try {
                    Event event = new Event(parsedInput[1], Utils.parseDate(parsedInput[3]));
                    taskList.add(event);

                    System.out.println("Meep:Got it. I've added this task:");
                    System.out.println(event.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (DateTimeException e) {
                    System.out.print("Meep:");
                    System.out.println(e.getMessage());
                }

            } else if (userCommand.equals(Command.TODO.val)) {
                ToDo todo = new ToDo(parsedInput[1]);
                taskList.add(todo);

                System.out.println("Meep:Got it. I've added this task:");
                System.out.println(todo.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else if (userCommand.equals(Command.DELETE.val)) {
                int index = Integer.parseInt(parsedInput[1]) - 1;
                Task task = taskList.get(index);
                taskList.remove(index);
                System.out.println("Meep: Noted. I've removed this task:");
                System.out.println(task.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        }
        sc.close();
    }
}