import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHow may I help you today?\n");

        ArrayList<Task> inputList = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input.toLowerCase(), "bye")) {
                System.out.println("\nGoodbye.\nHave a nice day!");
                break;
            }

            else if (Objects.equals(input.toLowerCase(), "list")) {
                if (!inputList.isEmpty()) {
                    System.out.println("Here are the tasks in your list: " + "\n");
                    for (int i = 0; i < inputList.size(); i++) {
                        System.out.println( (i + 1) + ". "  + inputList.get(i) + "\n");
                    }
                } else {
                    System.out.println("You currently have no tasks.");
                }
            }

            else if (input.toLowerCase().contains("unmark")) {
                String[] parsedInput = input.toLowerCase().split("\\s+");
                int idx = Integer.parseInt(parsedInput[1]);
                if (idx - 1 >= inputList.size()) {
                    System.out.println("Index provided is out of range.");
                } else {
                    System.out.println("OK. I've marked your task as incomplete.");
                    inputList.get(idx - 1).markAsUndone();
                }
            }

            else if (input.toLowerCase().contains("mark")) {
                String[] parsedInput = input.toLowerCase().split("\\s+");
                int idx = Integer.parseInt(parsedInput[1]);
                if (idx - 1 >= inputList.size()) {
                    System.out.println("Index provided is out of range.");
                } else {
                    System.out.println("Good job. I've marked your task as complete.");
                    inputList.get(idx - 1).markAsDone();
                }
            }

            else {
                String[] parsedString = input.toLowerCase().split(" ", 2);
                if (parsedString[0].equalsIgnoreCase("deadline")) {
                    if (parsedString.length <= 1) {
                        System.out.println("The description cannot be empty!");
                    } else {
                        String [] dlParseBy = parsedString[1].split("/by");
                        if (dlParseBy.length <= 1) {
                            System.out.println("Please check that your input format is correct.");
                        } else {
                            Deadline dl  = new Deadline(dlParseBy[0], dlParseBy[1]);
                            inputList.add(dl);
                            System.out.println("Got it. I've added " + dl + " to the list.");
                            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                        }
                    }
                }

                else if (parsedString[0].equalsIgnoreCase("event")) {
                    if (parsedString.length <= 1) {
                        System.out.println("The description cannot be empty!");
                    } else {
                        String [] eventParseBy = parsedString[1].split("/at");
                        if (eventParseBy.length <= 1) {
                            System.out.println("Please check that your input format is correct.");
                        } else {
                            Event ev  = new Event(eventParseBy[0], eventParseBy[1]);
                            inputList.add(ev);
                            System.out.println("Got it. I've added " + ev + " to the list.");
                            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                        }
                    }
                }

                else if (parsedString[0].equalsIgnoreCase("todo")) {
                    if (parsedString.length <= 1) {
                        System.out.println("The description cannot be empty!");
                    } else {
                        ToDos td = new ToDos(parsedString[1]);
                        inputList.add(td);
                        System.out.println("Got it. I've added " + td + " to the list.");
                        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                    }
                }

                else if (parsedString[0].equalsIgnoreCase("delete")) {
                    String[] parsedInput = input.toLowerCase().split("\\s+");
                    int idx = Integer.parseInt(parsedInput[1]);
                    if (idx - 1 >= inputList.size()) {
                        System.out.println("Index provided is out of range.");
                    } else {
                        System.out.println("OK. I've successfully deleted your task.");
                        inputList.remove(idx - 1);
                    }
                }

                else {
                    System.out.println("Sorry, but I have no idea what you mean.");
                }
            }
        }
    }
}
