import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

class Duke {

    static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("enter a command\n" +
                "use command 'help' to see list of commands");

        String userInput = sc.nextLine();
        String[] split = userInput.split(" ");

        while (!(userInput.equals("bye"))) {
            if (userInput.equals("list")) {
                for (int i = 0; i < toDoList.size(); i++) {
                    Task t = toDoList.get(i);
                    System.out.printf("%d. " + t.toString(), i + 1);
                }
            } else if (split[0].equals("help")) {
                System.out.println("try the following commands:\n"
                        + "   list (list out all tasks in your todo list)\n"
                        + "   todo <task> (add a basic task to your todo list)\n"
                        + "   deadline <task> /by <date> (add a task with a deadline to your todo list)\n"
                        + "   event <task> /at <date> (add a new event with its corresponding date to your todo list)\n"
                        + "   mark <task index> (mark that specific task as completed)\n"
                        + "   unmark <task index> (remove mark from specific task)\n"
                        + "   delete <task index> (remove task from todo list)\n"
                        + "   bye (close application)");
            } else if (split[0].equals("unmark")) {
                try {
                    int item = Integer.parseInt(split[1]);
                    try {
                        Task t = toDoList.get(item - 1);
                        t.unmark();
                        System.out.printf("Boo! more work to do: %s\n", t.getName());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(("the index you have entered does not exist!"));
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("unmark description cannot be empty!");
                } catch (NumberFormatException e) {
                    System.out.println("unmark description must be an integer!");
                }


            } else if (split[0].equals("mark")) {
                try {
                    int item = Integer.parseInt(split[1]);
                    try {
                        Task t = toDoList.get(item - 1);
                        t.mark();
                        System.out.printf("great job! I've marked this task as done: %s\n", t.getName());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(("the index you have entered does not exist!"));
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("mark description cannot be empty!");
                } catch (NumberFormatException e) {
                    System.out.println("mark description must be an integer!");
                }

            } else if (split[0].equals("todo")) {
                try {
                    String check = split[1];
                    String name = userInput.substring(5);
                    ToDo t = new ToDo(name);
                    toDoList.add(t);
                    System.out.printf("task added:\n%s", t);
                    System.out.printf("you now have %d tasks\n", toDoList.size());
                } catch (Exception e) {
                    System.out.println("the description of a todo cannot be empty!");
                }
            } else if (split[0].equals("deadline")) {
                try {
                    String check = split[1];
                    try {
                        String item = userInput.substring(9);
                        String[] divide = item.split("/");
                        String name = divide[0];
                        String dueDate = divide[1];
                        Deadline d = new Deadline(name, LocalDate.parse(dueDate.substring(3)));
                        toDoList.add(d);
                        System.out.printf("task added:\n%s", d);
                        System.out.printf("you now have %d tasks\n", toDoList.size());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("deadline description must contain a date!");
                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("deadline description cannot be empty!");
                }


            } else if (split[0].equals("event")) {
                try {
                    String check = split[1];
                    try {
                        String item = userInput.substring(6);
                        String[] divide = item.split("/");
                        String name = divide[0];
                        String time = divide[1];
                        Event e = new Event(name, LocalDate.parse(time.substring(3)));
                        toDoList.add(e);
                        System.out.printf("task added:\n%s", e);
                        System.out.printf("you now have %d tasks\n", toDoList.size());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("event description must contain a date!");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("event description cannot be empty!");
                }

            } else if (split[0].equals("delete")) {
                try {
                    int toDelete = Integer.parseInt(split[1]);
                    try {
                        Task t = toDoList.get(toDelete - 1);
                        toDoList.remove(toDelete - 1);
                        System.out.printf("task removed:\n%s", t.toString());
                        System.out.printf("you now have %d tasks\n", toDoList.size());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(("the index you have entered does not exist!"));
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("delete description cannot be empty!");
                } catch (NumberFormatException e) {
                    System.out.println("delete description must be an integer!");
                }


            } else {
                System.out.println("invalid command! try 'help' for list of commands");
            }
            userInput = sc.nextLine();
            split = userInput.split(" ");
        }
        System.out.println("goodbye!");
        sc.close();
    }


}

