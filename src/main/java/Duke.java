import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String lines = "____________________________________________________________";
        String endline = "____________________________________________________________\n";
        System.out.println("Hello!!! I am Duke, a new born chatbot\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> allTasks = new ArrayList<>();
        System.out.println("How may I serve you?");
        while (true) {

                String input = sc.nextLine();
                String[] parts = input.split(" ");
            try {
                //level-1 feature: for exit
                if (input.equals("bye")) {
                    System.out.println(lines);
                    System.out.println("Bye! See you again");
                    System.out.println(endline);
                    break;
                }

                if (input.equals("list")) {
                    System.out.println(lines);
                    System.out.println("Here are all your tasks:");
                    for (int i = 0; i < allTasks.size(); i++) {
                        System.out.println((i + 1) + "." + allTasks.get(i).toString());
                    }
//                for(Task t :allTasks) {
//                    System.out.println("1. " + t.getDescription());
//                }
                    System.out.println(lines + "\n");
                    continue;
                }
                if (parts[0].equals("mark")) {
                    Task markTask = allTasks.get(Integer.parseInt(parts[1]) - 1);
                    System.out.println(lines);
                    if (markTask.isDone == true) {
                        System.out.println("You have already done this task!");
                        System.out.println(markTask.markAsDone());
                        continue;
                    }
                    System.out.println("Good Job! You have marked this task as done!");
                    System.out.println(markTask.markAsDone());
                    System.out.println(endline);
                    continue;
                }
                if (parts[0].equals("unmark")) {
                    Task markTask = allTasks.get(Integer.parseInt(parts[1]) - 1);
                    System.out.println(lines);
                    if (markTask.isDone == false) {
                        System.out.println("This task is already in undone status");
                        System.out.println(markTask.markAsUndone());
                        continue;
                    }
                    System.out.println("OK, I have marked this as not done yet:");
                    System.out.println(markTask.markAsUndone());
                    System.out.println(endline);
                    continue;
                }
                if (parts[0].equals("todo")) {
//                String todoDesription = Arrays.toString(Arrays.copyOfRange(parts,1,parts.length));
                    String todoDesription = input.substring(5);
                    Task todo = new Todo(todoDesription);
                    allTasks.add(todo);
                    System.out.println(lines);
                    System.out.println("Got it, I have added a TODO task:");
                    System.out.println(todo.toString());
                    System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println(endline);
                    continue;
                }
                if (parts[0].equals("deadline")) {
                    String[] split1 = input.split("/by ");
                    String deadlineDesription = split1[0].substring(9);
                    String deadlineDate = split1[1];
                    Task deadline = new Deadline(deadlineDesription, deadlineDate);
                    allTasks.add(deadline);
                    System.out.println(lines);
                    System.out.println("Got it, I have added a DEADLINE task:");
                    System.out.println(deadline.toString());
                    System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println(endline);
                    continue;
                }
                if (parts[0].equals("event")) {
                    String[] split1 = input.split("/at ");
                    String eventDesription = split1[0].substring(6);
                    String eventDate = split1[1];
                    Task event = new Event(eventDesription, eventDate);
                    allTasks.add(event);
                    System.out.println(lines);
                    System.out.println("Got it, I have added an EVENT task:");
                    System.out.println(event.toString());
                    System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println(endline);
                    continue;
                }
                throw new DukeException("Sorry, I don't know what you mean");
            } catch (DukeException e) {
                System.out.println(e);
            }
//            System.out.println(lines);
//            allTasks.add(new Task(input));
//            System.out.println("added: " + input);
//            System.out.println(endline);

        }

    }
}
