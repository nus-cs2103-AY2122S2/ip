import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static boolean parseIntAble(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        // greeting message
        String greetings = "    Hi there! 👋 I'm Duke\n"
                            + "    What can I do for you?";

        // divider
        String lines = "    ---------------------------------";

        System.out.println(lines);
        System.out.println(greetings);
        System.out.println(lines);
        // reading user input
        Scanner sc = new Scanner(System.in);

        // INITIALIZING VARIABLES
        /**
         * whether the user's input is bye
         */
        boolean isBye = false;

        /**
         * Array container for user's to do tasks
         */
        ArrayList<Task> todoList = new ArrayList<Task>();

        // initializing saver to save todoList tasks to relativePath
        String fileSeparator = System.getProperty("file.separator");
        String relativePath = "data" + fileSeparator + "duke.txt";
        DukeTaskSaver saver = new DukeTaskSaver(relativePath);

        // load data when duke starts up
        try {
            todoList = saver.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String userInput = sc.nextLine();

        while (!isBye) {
            /**
             * A string to display the remaining task number
             */
            String displayTaskAmount = String.format("Now you have %d tasks in the list.", todoList.size() + 1);

            if (userInput.equals("bye")) {
                isBye = true;

                System.out.println(lines);
                System.out.println("    Bye. See you again next time! Have a nice day 😊!");
                System.out.println(lines);
            } else {
                // storing input task in todoList
                String[] userInputArr = userInput.split(" ");
                String userCommand = userInputArr[0];
                String userInputTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));

                try {
                    UserInputTaskValidator.userCommandValidator(userCommand);
                } catch (DukeException e) {
                    System.out.println(lines);
                    System.out.println("    OOPS!!! I'm sorry, but I don't know what that means.");
                    System.out.println(lines);
                }

                switch (userCommand) {
                    case "list":
                        System.out.println(lines);
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < todoList.size(); i++) {
                            String display = String.format("    %d.%s", i + 1, todoList.get(i).toString());
                            System.out.println(display);
                        }
                        System.out.println(lines);
                        break;

                    case "todo":

                        // handle error from empty task description
                        try {
                            UserInputTaskValidator.taskDescriptionValidator(userCommand ,userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(lines);
                            break;
                        }

                        System.out.println(lines);

                        // adding task to todoList
                        Todo userToDoTask = new Todo(userInputTask);
                        todoList.add(userToDoTask);

                        // save tasks to duke.txt
                        saver.save(todoList);

                        // display to do task
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("        " + userToDoTask.toString());
                        System.out.println("    " + displayTaskAmount);
                        System.out.println(lines);

//                        count++;
                        break;

                    case "deadline":
                        // handle error from empty task description
                        try {
                            UserInputTaskValidator.taskDescriptionValidator(userCommand, userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
                            System.out.println(lines);
                            break;
                        }

                        // handle error from empty by
                        try {
                            UserInputTaskValidator.deadlineTaskValidator(userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! Deadline tasks require a by day.");
                            System.out.println(lines);
                            break;
                        }

                        // handle error when there is more than one by deadline day
                        try {
                            UserInputTaskValidator.deadlineByDayValidator(userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! Deadline tasks can only have one by day.");
                            System.out.println(lines);
                            break;
                        }

                        // splitting deadline into description and by
                        String[] deadlineTaskArr = userInputTask.split(" /by ");
                        String deadlineDescription = deadlineTaskArr[0];
                        String[] byAndTime = deadlineTaskArr[1].split(" ");
                        String by = byAndTime[0];

                        // splitting deadline into time and the rest
                        String[] deadlineTimeAndTheRest = userInputTask.split(" ");

                        try {
                            String deadlineTime = deadlineTimeAndTheRest[3];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(lines);
                            System.out.println("    Deadline time is required");
                            System.out.println(lines);
                            break;
                        }

                        String deadlineTime = deadlineTimeAndTheRest[3];

                        // handle error when time is not in the hh:mm 24hr clock format
                        try {
                            LocalTime.parse(deadlineTime);
                        } catch (DateTimeParseException e) {
                            System.out.println(lines);
                            System.out.println("Time must be in the hh:mm 24hr format");
                            System.out.println(lines);
                            break;
                        }

                        LocalTime time = LocalTime.parse(deadlineTime);

                        // handle error when there is invalid deadline date format
                        try {
                            UserInputTaskValidator.deadlineDateFormatValidator(by);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.");
                            System.out.println(lines);
                            break;
                        }

                        LocalDate deadlineDate = LocalDate.parse(by);

                        System.out.println(lines);
                        // adding task to todoList
                        Deadline userDeadlineTask = new Deadline(deadlineDescription, deadlineDate, time);
                        todoList.add(userDeadlineTask);

                        // save tasks to duke.txt
                        saver.save(todoList);

                        // displaying
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("        " + userDeadlineTask.toString());
                        System.out.println("    " + displayTaskAmount);
                        System.out.println(lines);


                        break;

                    case "event":
                        // handle error from empty task description
                        try {
                            UserInputTaskValidator.taskDescriptionValidator(userCommand, userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! The description of an event cannot be empty.");
                            System.out.println(lines);
                            break;
                        }

                        // handle error when there is no datTime
                        try {
                            UserInputTaskValidator.eventTaskValidator(userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! Event tasks require an at date and time.");
                            System.out.println(lines);
                            break;
                        }

                        // handle error when there is more than one dateTime
                        try {
                            UserInputTaskValidator.eventAtDateTimeValidator(userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! Event tasks can only have one at date and time.");
                            System.out.println(lines);
                            break;
                        }

                        System.out.println(lines);

                        // splitting event into description and dateTime
                        String[] eventTaskArr = userInputTask.split(" /at ");
                        String eventDescription = eventTaskArr[0];
                        String[] eventDateAndTime = eventTaskArr[1].split(" ");
                        String eventDate = eventDateAndTime[0];

                        // splitting event into time and the rest
                        String[] eventTimeAndTheRest = userInputTask.split(" ");

                        try {
                            String eventTime = eventTimeAndTheRest[3];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(lines);
                            System.out.println("    Event time is required");
                            System.out.println(lines);
                            break;
                        }

                        String eventTime = eventTimeAndTheRest[3];

                        // handle error when time is not in the hh:mm 24hr clock format
                        try {
                            LocalTime.parse(eventTime);
                        } catch (DateTimeParseException e) {
                            System.out.println(lines);
                            System.out.println("Time must be in the hh:mm 24hr format");
                            System.out.println(lines);
                            break;
                        }

                        LocalTime atTime = LocalTime.parse(eventTime);
                        // handle error when there is invalid deadline date format
                        try {
                            UserInputTaskValidator.eventAtDateTimeValidator(eventDate);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.");
                            System.out.println(lines);
                            break;
                        }

                        LocalDate eventAtDate = LocalDate.parse(eventDate);

                        // adding task to todoList
                        Event userEventTask = new Event(eventDescription, eventAtDate, atTime);
                        todoList.add(userEventTask);

                        // save tasks to duke.txt
                        saver.save(todoList);

                        System.out.println("    Got it. I've added this task:");
                        System.out.println("        " + userEventTask.toString());
                        System.out.println("    " + displayTaskAmount);
                        System.out.println(lines);

//                        count++;
                        break;

                    case "mark":
                        int taskToMark = Integer.parseInt(userInputArr[1]);
                        todoList.get(taskToMark - 1).markAsDone();

                        // save update tasks to duke.txt
                        saver.save(todoList);

                        System.out.println(lines);
                        System.out.println("    Nice! I've marked this task as done:");

                        String taskMarkString = String.format("%s", todoList.get(taskToMark - 1).toString());
                        System.out.println("    " + taskMarkString);
                        System.out.println(lines);

                        break;

                    case "unmark":
                        int taskToUnmark = Integer.parseInt(userInputArr[1]);
                        todoList.get(taskToUnmark - 1).markAsNotDone();

                        // save update tasks to duke.txt
                        saver.save(todoList);

                        System.out.println(lines);
                        System.out.println("    OK, I've marked this task as not done yet:");

                        String taskString = String.format("%s", todoList.get(taskToUnmark - 1).toString());
                        System.out.println("    " + taskString);
                        System.out.println(lines);
                        break;

                    case "delete":
                        // handle error when there is no specified task number to be deleted
                        try {
                            UserInputTaskValidator.deleteValidator(userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    Delete command must have a specified task number to be deleted.");
                            System.out.println(lines);
                            break;
                        }

                        // handle error when the task number specified is invalid
                        try {
                            UserInputTaskValidator.deleteTaskNumberValidator(todoList, userInputTask);
                        } catch (DukeException e) {
                            System.out.println(lines);
                            System.out.println("    Invalid task number to be deleted.");
                            System.out.println(lines);
                            break;
                        }

                        int taskToDelete = Integer.parseInt(userInputArr[1]) - 1;
                        // to be removed task
                        Task tobeRemoved = todoList.get(taskToDelete);

                        // deleting task from the array list
                        todoList.remove(taskToDelete);

                        // save update tasks to duke.txt
                        saver.save(todoList);

                        System.out.println(lines);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("        " + tobeRemoved.toString());

                        String taskRemainingString = String.format("    Now you have %d tasks in the list.", todoList.size());
                        System.out.println(taskRemainingString);
                        System.out.println(lines);
                        break;
                }
                userInput = sc.nextLine();
            }
        }
    }
}
