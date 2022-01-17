import java.util.*;
import java.util.Scanner;

import SparkExceptions.FormatExceptions.*;
import Tasks.TaskList;

public class Spark {
    public static void main(String[] args) {
        // assuming that there will be no more than 100 tasks,
        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);

        // print welcome message
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Hello! I'm Spark");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------------------------------------");

        String input;
        String[] tokens;
        String command;
        do {
            // get input from user
            input = sc.nextLine();
            tokens = input.split(" "); // split command into individual keywords by single-space
            command = tokens[0]; // assume that the first keyword is always the command word

            System.out.println("----------------------------------------------------------------------");

            try {
                if (command.equals("bye")) {// print goodbye message
                    System.out.println("Cool, see you around!");

                } else if (command.equals("list")) {
                    taskList.print();

                } else if (command.equals("mark")) {
                    int taskId = Integer.parseInt(tokens[1]);
                    taskList.markTask(taskId);

                } else if (command.equals("unmark")) {
                    int taskId = Integer.parseInt(tokens[1]);
                    taskList.unMarkTask(taskId);

                } else {
                    if (command.equals("event")) {
                        String[] eventParams = getEventParams(tokens);
                        taskList.addEvent(eventParams[0], eventParams[1]);

                    } else if (command.equals("deadline")) {
                        String[] deadlineParams = getDeadlineParams(tokens);
                        taskList.addDeadline(deadlineParams[0], deadlineParams[1]);

                    } else if (command.equals("todo")) {
                        String toDoParams = getToDoParams(tokens);
                        taskList.addToDo(toDoParams);
                    } else {
                        throw new UnrecognisedCommandException();
                    }
                }
            } catch (FormatException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("----------------------------------------------------------------------");
        } while (!command.equals("bye"));
    }

    private static String getToDoParams(String[] tokens) throws EmptyTitleException {
        List<String> words = new ArrayList<>(Arrays.asList(tokens).subList(1, tokens.length));

        String title = String.join(" ", words);

        // ToDo must have a title
        if (title.equals("")) {
            throw new EmptyTitleException();
        }

        return title;
    }

    private static String[] getDeadlineParams(String[] tokens) throws EmptyTitleException, EmptyDateException {
        List<String> firstHalf = new ArrayList<>();
        List<String> secondHalf = new ArrayList<>();

        boolean inSecondHalf = false;
        for (int i=1; i<tokens.length; i++) {
        if (tokens[i].equals("/by")) {
                inSecondHalf = true;
            } else if (!inSecondHalf) {
                firstHalf.add(tokens[i]);
            } else {
                secondHalf.add(tokens[i]);
            }
        }

        String title = String.join(" ", firstHalf);
        String by = String.join(" ", secondHalf);

        // Deadline must have a title
        if (title.equals("")) {
            throw new EmptyTitleException();
        }

        // Deadline must have a date
        if (by.equals("")) {
            throw new EmptyDateException();
        }

        return new String[]{title, by};
    }

    private static String[] getEventParams(String[] tokens) throws EmptyTitleException, EmptyDateException {
        List<String> firstHalf = new ArrayList<>();
        List<String> secondHalf = new ArrayList<>();

        boolean inSecondHalf = false;
        for (int i=1; i<tokens.length; i++) {
            if (tokens[i].equals("/at")) {
                inSecondHalf = true;
            } else if (!inSecondHalf) {
                firstHalf.add(tokens[i]);
            } else {
                secondHalf.add(tokens[i]);
            }
        }

        String title = String.join(" ", firstHalf);
        String at = String.join(" ", secondHalf);

        // Event must have a title
        if (title.equals("")) {
            throw new EmptyTitleException();
        }

        // Event must have a date
        if (at.equals("")) {
            throw new EmptyDateException();
        }

        return new String[]{title, at};
    }
}
