import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mickey {
    public static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Save s = new Save("src/main/java/data.txt");
        try {
            tasks.addAll(s.loadTasks());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Hey there! I'm Mickey, your Mouse assistant.\nWhat can I do for you today?\n");
        for (String cmd = sc.next(); !cmd.equals("bye"); cmd = sc.next()) {
            try {
                String in = sc.nextLine().trim();

                switch (cmd) {
                    case "todo":
                        if (in.length() == 0) {
                            throw new MickeyException.MissingInputException("\tWhoa! Minnie won't want to see a " +
                                    "missing todo.");
                        }
                        ToDo newToDo = new ToDo(in);
                        tasks.add(newToDo);
                        System.out.println("\tAw, gee! New todo:\n\t\t" + newToDo);
                        System.out.println("\tHooray! You now have " + tasks.size() + " tasks");
                        break;

                    case "deadline":
                        if (in.length() == 0) {
                            throw new MickeyException.MissingInputException("\tWhoa! Minnie won't want to see a " +
                                    "missing deadline.");
                        }
                        String[] deadline = Arrays.stream(in.split("/by", 2)).map(String::trim)
                                .toArray(String[]::new);
                        if (deadline.length == 1 || deadline[1].length() == 0) {
                            throw new MickeyException.MissingInputException("\tOops! Pluto must have eaten the date.");
                        }
                        Deadline newDeadline = new Deadline(deadline);
                        tasks.add(newDeadline);
                        System.out.println("\tAw, gee! New deadline:\n\t\t" + newDeadline);
                        System.out.println("\tHooray! You now have " + tasks.size() + " tasks");
                        break;

                    case "event":
                        if (in.length() == 0) {
                            throw new MickeyException.MissingInputException("\tWhoa! Minnie won't want to see a " +
                                    "missing event.");
                        }
                        String[] event = Arrays.stream(in.split("/at", 2)).map(String::trim)
                                .toArray(String[]::new);
                        if (event.length == 1 || event[1].length() == 0) {
                            throw new MickeyException.MissingInputException("\tOops! Pluto must have eaten the date.");
                        }
                        Event newEvent = new Event(event);
                        tasks.add(new Event(event));
                        System.out.println("\tAw, gee! New event:\n\t\t" + newEvent);
                        System.out.println("\tYou now have " + tasks.size() + " tasks");
                        break;

                    case "list":
                        if (tasks.size() > 0) {
                            System.out.println("\tOh boy! You have " + tasks.size() + " tasks:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println("\t\t" + (i + 1) + ". " + tasks.get(i));
                            }
                        } else {
                            System.out.println("\tHooray! You have no tasks");
                        }
                        break;

                    case "mark":
                        if (in.length() == 0) {
                            throw new MickeyException.MissingInputException("\tWhoa! Minnie won't want to see a " +
                                    "missing task.");
                        }
                        int toMark = Integer.parseInt(in) - 1;
                        if (toMark >= tasks.size()) {
                            throw new MickeyException.InvalidMarkException("No such task!");
                        }
                        System.out.println("\tThat is swell! You have completed this task:");
                        System.out.println("\t\t" + tasks.get(toMark).markAsDone());
                        break;

                    case "unmark":
                        if (in.length() == 0) {
                            throw new MickeyException.MissingInputException("\tWhoa! Minnie won't want to see a " +
                                    "missing task.");
                        }
                        int toUnmark = Integer.parseInt(in) - 1;
                        if (toUnmark >= tasks.size()) {
                            throw new MickeyException.InvalidMarkException("\tNo such task!");
                        }
                        System.out.println("\tHot dog! Complete this soon:");
                        System.out.println("\t\t" + tasks.get(toUnmark).markAsUndone());
                        break;

                    case "delete":
                        if (in.length() == 0) {
                            throw new MickeyException.MissingInputException("\tWhoa! Minnie won't want to see a " +
                                    "missing task.");
                        }
                        int toDelete = Integer.parseInt(in) - 1;
                        if (toDelete >= tasks.size()) {
                            throw new MickeyException.InvalidMarkException("\tNo such task!");
                        }
                        System.out.println("\tAlrighty. I've removed this task.");
                        System.out.println("\t\t" + tasks.remove(toDelete));
                        System.out.println("\tYou now have " + tasks.size() + " tasks");
                        break;

                    default:
                        System.out.println("\tOh no! This is a disaster! I don't know what that means");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\tToodles! See ya real soon!");
        s.saveTasks(tasks);
        tasks.clear();
    }
}