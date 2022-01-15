import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Create an empty array list
    private static ArrayList<Task> taskArr = new ArrayList<>();

    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void logo() {
        System.out.println(" ____                         _____       _\n"
                + "| |_) |_   _ ___ ___ _   _  | (___   __ _| | ____ _\n"
                + "|  _ <| | | / __/ __| | | |  \\___ \\ / _` | |/ / _` |\\\n"
                + "| |_) | |_| \\__ \\__ \\ |_| |  ____) | (_| |   < (_| |\n"
                + "|____/ \\__,_|___/___/\\__, | |_____/ \\__,_|_|\\_\\__,_|\n"
                + "                      __/ |\n"
                + "                      |___/ \n");
    }

    public static void totalTasks(int num) {
        if(num == 1) {
            System.out.println("     You currently have " + num + " task in your device.");
        } else {
            System.out.println("     You currently have " + num + " tasks in your device.");
        }
    }

    public static void mark(int num) {
        line();
        Task currTask = taskArr.get(num - 1);
        currTask.mark();
        System.out.println("     Your progressive bar has increased! Keep going to stop the imposter!");
        System.out.println("     " + currTask);
        line();
    }

    public static void unmark(int num) {
        line();
        Task currTask = taskArr.get(num - 1);
        currTask.unmark();
        System.out.println("     Uh oh, every task left undone lets the imposter edge closer...");
        System.out.println("     " + currTask);
        line();
    }

    public static void addToList(Task task) {
        taskArr.add(task);
        System.out.println("       " + task.toString());
        totalTasks(taskArr.size());
    }

    public static void deleteFromList(Task task) {
        taskArr.remove(task);
        line();
        System.out.println("     Aite, this task has been deleted!");
        System.out.println("       " + task);
        totalTasks(taskArr.size());
        line();
    }

    public static void printList() {
        line();
        System.out.println("     Here are the tasks in your device:");
        for (int i = 0; i < taskArr.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskArr.get(i).toString());
        }
        line();
    }

    public static void main(String[] args) {
        line();
        System.out.println("Tell me... have you seen a RED imposter among us?");
        System.out.println("If you have seen this SUSSY imposter, please let me know immediately... otherwise how may I be of assistance?");
        line();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            try {
                String input = sc.nextLine(); // read string

                // Handle empty command
                if(input.equals("")) {
                    throw new EmptyInputException();
                }

                // Create a String array to read various functions
                String[] strs = input.split(" ");

                // Store first word as variable
                String firstWord = strs[0];

                if(input.equalsIgnoreCase("bye")) {
                    line();
                    System.out.println("Better watch out for the imposter AMONG US!");
                    logo();
                    line();
                    break;
                } else if(input.equalsIgnoreCase("list")) {
                    printList();
                } else if(firstWord.equalsIgnoreCase("mark")) {
                    int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
                    mark(listIndex);
                } else if(firstWord.equalsIgnoreCase("unmark")) {
                    int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
                    unmark(listIndex);
                } else if(firstWord.equalsIgnoreCase("todo")) {
                    String subString = input.substring(4); // take the remaining of the input String
                    if(subString.length() == 0) {
                        throw new IncompleteInputException(firstWord);
                    } else {
                        Task toDo = new ToDo(subString);
                        line();
                        System.out.println("     Remember to complete your task!");
                        addToList(toDo);
                        line();
                    }
                } else if(firstWord.equalsIgnoreCase("deadline")) {
                    String subString = input.substring(8); // take the remaining of the input String
                    if(subString.length() == 0) {
                        throw new IncompleteInputException(firstWord);
                    } else {
                        String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                        String deadlineDate = temp[1].substring(3);
                        Task deadline = new Deadline(temp[0], deadlineDate);
                        line();
                        System.out.println("     This task is on a timer!");
                        addToList(deadline);
                        line();
                    }
                } else if(firstWord.equalsIgnoreCase("event")) {
                    String subString = input.substring(5); // take the remaining of the input String
                    if(subString.length() == 0) {
                        throw new IncompleteInputException(firstWord);
                    } else {
                        String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                        String eventDate = temp[1].substring(3);
                        Task event = new Event(temp[0], eventDate);
                        line();
                        System.out.println("     Emergency event on this date!");
                        addToList(event);
                        line();
                    }
                } else if(firstWord.equalsIgnoreCase("delete")) {
                    int listIndex = Integer.parseInt(strs[1]); // retrieve the index after delete
                    Task taskToBeDeleted = taskArr.get(listIndex - 1);
                    deleteFromList(taskToBeDeleted);
                } else {
                    throw new UnknownInputException();
                }
            } catch(EmptyInputException | UnknownInputException | IncompleteInputException ie) {
                line();
                System.out.println(ie.getMessage());
                line();
            }
        }
    }
}
