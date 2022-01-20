import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Task> list = new ArrayList<>(); // arraylist to keep track of task
    private boolean endProgram = false; // state to terminate the program

    public static void main(String[] args)  {
        new Duke().startProgram();
    }

    private void startProgram() {
        welcomeMessage();

        while(!endProgram) {
            try {
                String input = scanner.nextLine(); // user input

                // handle user input to command, description and time of task if applicable
                UserInput userInput = new UserInput(input);
                String command = userInput.getCommand();
                String description = userInput.getDescription();
                String time = userInput.getTime();

                // exit program when user input "bye"
                if(command.equals("bye")) {
                    endProgram();
                    endProgram = true;
                    break;
                }

                // list out the tasks
                if(command.equals("list")) {
                    if(!description.equals("") || !time.equals("")) {
                        // throw wrong command exception
                        throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");
                    }
                    listTask(list);
                    continue;
                }

                // mark certain task as done
                if(command.startsWith("mark")) {
                    if(description.equals("")) {
                        // throw no description exception
                        throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
                    }
                    int n = Integer.parseInt(description.replaceAll("[^\\d-]", ""));
                    markDone(n - 1, list);
                    continue;
                }

                // mark certain task as not done yet
                if(command.startsWith("unmark")) {
                    if(description.equals("")) {
                        // throw no description exception
                        throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
                    }
                    int n = Integer.parseInt(description.replaceAll("[^\\d-]", ""));
                    markUndone(n - 1, list);
                    continue;
                }

                // delete task
                if(command.startsWith("delete")) {
                    if(description.equals("")) {
                        // throw no description exception
                        throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
                    }
                    int n = Integer.parseInt(description.replaceAll("[^\\d-]", ""));
                    deleteTask(n - 1, list);
                    continue;
                }

                // create ToDoTask
                if(command.equals("todo")) {
                    if(description.equals("")) {
                        // throw no description exception
                        throw new DukeNoDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTask(new ToDoTask(description), list);
                    continue;
                }

                // create DeadlineTask
                if(command.equals("deadline")) {
                    if(description.equals("")) {
                        // throw no description exception
                        throw new DukeNoDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
                    } else if(time.equals("")) {
                        // throw no time specified exception
                        throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
                    }
                    addTask(new DeadlineTask(description, time), list);
                    continue;
                }

                // create EventTask
                if(command.equals("event")) {
                    if(description.equals("")) {
                        // throw no description exception
                        throw new DukeNoDescriptionException("OOPS!!! The description of a event cannot be empty.");
                    } else if(time.equals("")) {
                        // throw no time specified exception
                        throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
                    }
                    addTask(new EventTask(description, time), list);
                    continue;
                }

                // Invalid command inputs result
                throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");

            } catch(DukeCommandDoesNotExistException | DukeNoDescriptionException | DukeNoTimeSpecifiedException
                    | DukeOutOfBoundException e) {
                errorMessage(e);
            }

        }

        // close the scanner
        scanner.close();
    }

    private void drawDivider() {
        System.out.println("________________________________________");
    }

    private void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        drawDivider();
    }

    private void endProgram() {
        drawDivider();
        System.out.println("Bye. Hope to see you again soon!");
        drawDivider();
    }

    private void errorMessage(DukeException e) {
        drawDivider();
        System.out.println(e.getMessage());
        drawDivider();
    }

    private void listTask(ArrayList<Task> list) {
        drawDivider();
        if(list.size() == 0) {
            System.out.println("Nothing on your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < list.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, list.get(i).toString());
            }
        }
        System.out.printf("Now you have %d tasks in the list%n", list.size());
        drawDivider();
    }

    private void addTask(Task task, ArrayList<Task> list) {
        drawDivider();
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list%n", list.size());
        drawDivider();
    }

    private void markDone(int i, ArrayList<Task> list) throws DukeOutOfBoundException {
        // check if the user's input value is within the range of the list before mark done the task
        checkOutOfBound(i);
        drawDivider();
        list.get(i).markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(i).toString());
        drawDivider();
    }

    private void markUndone(int i, ArrayList<Task> list) throws DukeOutOfBoundException {
        // check if the user's input value is within the range of the list before mark undone the task
        checkOutOfBound(i);
        drawDivider();
        list.get(i).markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(i).toString());
        drawDivider();
    }

    private void deleteTask(int i, ArrayList<Task> list) throws DukeOutOfBoundException {
        // check if the user's input value is within the range of the list before deleting the task
        checkOutOfBound(i);
        drawDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(i).toString());
        list.remove(i);
        System.out.printf("Now you have %d tasks in the list%n", list.size());
        drawDivider();
    }

    private void checkOutOfBound(int i) throws DukeOutOfBoundException {
        // check if the user's input value is within the range of the list
        if(i < 0) {
            throw new DukeOutOfBoundException("OOPS!!! Please key in a positive number");
        } else if(i >= list.size()) {
            throw new DukeOutOfBoundException("OOPS!!! Please double check the task number");
        }
    }

    private void invalidInput() {
        drawDivider();
        System.out.println("Invalid input");
        drawDivider();
    }
}
