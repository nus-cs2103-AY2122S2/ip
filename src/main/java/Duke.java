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
                    try {
                        Task markTask = allTasks.get(Integer.parseInt(parts[1]) - 1);
                        System.out.println(lines);
                        if (markTask.isDone == true) {
                            System.out.println("You have already done this task!");
                            System.out.println(markTask.markAsDone());
                            System.out.println(endline);
                            continue;
                        }
                        System.out.println("Good Job! You have marked this task as done!");
                        System.out.println(markTask.markAsDone());
                        System.out.println(endline);
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You have entered invalid task or that task does not exist!");
                    }
                }
                if (parts[0].equals("unmark")) {
                    try {
                        Task markTask = allTasks.get(Integer.parseInt(parts[1]) - 1);
                        System.out.println(lines);
                        if (markTask.isDone == false) {
                            System.out.println("This task is already in undone status");
                            System.out.println(markTask.markAsUndone());
                            System.out.println(endline);
                            continue;
                        }
                        System.out.println("OK, I have marked this as not done yet:");
                        System.out.println(markTask.markAsUndone());
                        System.out.println(endline);
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You have entered invalid task or that task does not exist!");
                    }
                }
                if (parts[0].equals("todo")) {
//                String todoDesription = Arrays.toString(Arrays.copyOfRange(parts,1,parts.length));
                    if (parts.length == 1) {
                        throw new TodoException("☹ OOPS!!! The description of a todo cannot be empty.(please insert again)");
                    }
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
                    if (parts.length == 1) {
                        throw new DeadlineException("Emm, what is your task again? (please insert again)");
                    }
                    String[] split1 = input.split("/by ");
                    if (split1.length == 1) {
                        throw new DeadlineException("You need to tell me your deadline date\n e.g deadline <yourtask> /by <deadline date>");
                    }
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
                    if (parts.length == 1) {
                        throw new EventException("The format should be: event <description> /at <date>");
                    }
                    String[] split1 = input.split("/at ");
                    if (split1.length == 1) {
                        throw new EventException("You need to tell me your event date\n e.g event <description> /at <date>");
                    }
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
                if (parts[0].equals("delete")) {
                    try {
                        if (parts.length == 1) {
                            System.out.println("Which task are you deleting? Insert a number like this: delete <task number>");
                            continue;
                        }
                        if (parts.length > 2) {
                            throw new NumberFormatException();
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        Task taskToBeDelete = allTasks.get(index);
                        System.out.println(lines);
                        System.out.println("Okay, I have removed this task:");
                        System.out.println(taskToBeDelete);
                        allTasks.remove(index);
                        System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
                        System.out.println(endline);
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        throw new DeleteException("This task does not exist, there are " + allTasks.size() + " tasks now");
                    }
//                  catch (ArrayIndexOutOfBoundsException e) {
//                        throw new DeleteException("You need to delete like this: delete <task number> , do not put extra wording");
                    catch (NumberFormatException e) {
                        throw new DeleteException("format must be: delete <task number> , other format is not acceptable");
                    }
                }
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
//            System.out.println(lines);
//            allTasks.add(new Task(input));
//            System.out.println("added: " + input);
//            System.out.println(endline);

        }

    }
}
