import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String bar = "-------------------------------------------------"; //Reusable horizontal bar display
        String indent = "      "; //Reusable indentation display

        System.out.println(bar);
        System.out.println("Hi I'm Zen!\n" + "How can I help ?");
        System.out.println(bar);
        System.out.println();

        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        int count = 0;

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine().trim(); //Whitespace is taken into consideration
        String command = getCommand(input); //Command is the first word of any input

        while (!command.equals("bye")) {

            switch (command) {

                case "list":
                    System.out.println(bar);
                    System.out.println(indent + "Here are the tasks in your list:");
                    displayList(count, listOfTasks);
                    System.out.println(bar);
                    break;

                case "mark":
                    System.out.println(bar);
                    try {
                        int number = getIndex(input);
                        Task markT = listOfTasks.get(number - 1);
                        System.out.println(indent + "Nice! I've marked this task as done!");
                        markT.markTask();
                        System.out.println(indent + markT.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                        System.out.println("Please enter a valid number!");
                    }
                    System.out.println(bar);
                    break;

                case "unmark":
                    System.out.println(bar);
                    try {
                        int num = getIndex(input);
                        Task unmarkT = listOfTasks.get(num - 1);
                        System.out.println(indent + "OK, I've marked this task as not done yet");
                        unmarkT.unmarkTask();
                        System.out.println(indent + unmarkT.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                        System.out.println("Please enter a valid number!");
                    }
                    System.out.println(bar);
                    break;

                case "delete":
                    System.out.println(bar);
                    try {
                        int deletedIndex = getIndex(input);
                        Task deletedTask = listOfTasks.get(deletedIndex - 1);
                        listOfTasks.remove(deletedIndex - 1);
                        count--;
                        System.out.println(indent + "Noted! I've removed this task:");
                        System.out.println(indent + deletedTask.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                        System.out.println("Please enter a valid number!");
                        System.out.println(bar);
                        break;
                    }
                    System.out.println(indent + "Now you have " + count + " tasks in the list");
                    System.out.println(bar);
                    break;

                case "todo":
                    System.out.println(bar);
                    try {
                        Todo td = Todo.formatInput(input);
                        System.out.println(indent + "Got it! I've added this task:");
                        System.out.println(indent + td.toString());
                        listOfTasks.add(td);
                    } catch (DukeException e) {
                        System.out.println("Please enter a valid description!");
                        System.out.println(bar);
                        break;
                    }
                    count++;
                    System.out.println(indent + "Now you have " + count + " tasks in the list");
                    System.out.println(bar);
                    break;
                    
                case "deadline":
                    System.out.println(bar);
                    try {
                        Deadline dl = Deadline.formatInput(input);
                        System.out.println(indent + "Got it! I've added this task:");
                        System.out.println(indent + dl.toString());
                        listOfTasks.add(dl);
                    } catch (DukeException e) {
                        System.out.println("Please enter a valid description/date!");
                        System.out.println(bar);
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please enter a date (eg. /by Tuesday)!");
                        System.out.println(bar);
                        break;
                    }
                    count++;
                    System.out.println(indent + "Now you have " + count + " tasks in the list");
                    System.out.println(bar);
                    break;

                case "event":
                    System.out.println(bar);
                    try {
                        Event ev = Event.formatInput(input);
                        System.out.println(indent + "Got it! I've added this task:");
                        System.out.println(indent + ev.toString());
                        listOfTasks.add(ev);
                    } catch (DukeException e) {
                        System.out.println("Please enter a valid description/date!");
                        System.out.println(bar);
                        break;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please enter a date (eg. /at Monday 3-4pm)!");
                        System.out.println(bar);
                        break;
                    }
                    count++;
                    System.out.println(indent + "Now you have " + count + " tasks in the list");
                    System.out.println(bar);
                    break;

                default:
                    System.out.println(bar);
                    System.out.println("Please enter a valid command word (eg. list, mark, todo)!");
                    System.out.println(bar);
            }
            System.out.println();
            input = sc.nextLine().trim();
            command = getCommand(input);
        }

        System.out.println(bar);
        System.out.println(indent + "Bye! See you soon !");
        System.out.println(bar);
    }

    //Function that prints out all string elements in an array, line by line
    public static void displayList(int lenOfArray, ArrayList<Task> arr) {
        if (lenOfArray == 0) {
            System.out.println("      " + "Nothing added yet!");
        }
        for (int i = 0; i < lenOfArray; i++) {
            Task t = arr.get(i);
            System.out.println("      " + (i + 1) + ". " + t.toString());
        }
    }

    //Gets the first word of every line of input, labelled as the "command" word
    public static String getCommand(String input) {
        int index = input.indexOf(' ');
        return (index >= 0) ? input.substring(0, index) : input;
    }

    //Gets the index of the numerical value in the "mark/unmark" input
    public static int getIndex(String input) throws DukeException {
        String seperator = " ";
        int pos = input.indexOf(seperator);
        int res = Integer.parseInt(input.substring(pos + 1).trim());
        if (res < 0) {
            throw new DukeException("Negative numbers cannot be used");
        }
        return res;
    }
}
