package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException, DukeException {
        // divider
        String LINES = "    ---------------------------------";

        Ui ui = new Ui();
        ui.greet();

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
        TaskList taskList = new TaskList(todoList);

        // initializing storage
        Storage storage = new Storage();

        // load data when duke starts up
        try {
            todoList = storage.load();
            taskList = new TaskList(todoList);
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

                System.out.println(LINES);
                System.out.println("    Bye. See you again next time! Have a nice day 😊!");
                System.out.println(LINES);
            } else {
                // storing input task in todoList
                String[] userInputArr = userInput.split(" ");
                String userCommand = userInputArr[0];
                String userInputTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));

                try {
                    Parser.userCommandValidator(userCommand);
                } catch (DukeException e) {
                    System.out.println(LINES);
                    System.out.println("    OOPS!!! I'm sorry, but I don't know what that means.");
                    System.out.println(LINES);
                }

                switch (userCommand) {
                    case "list":
                        Parser.parserList(taskList);
                        break;
                    case "todo":
                        // handle error from empty task description
                        try {
                            Parser.parserTodo(taskList, userInputTask);
                        } catch (DukeException e) {
                            System.out.println(LINES);
                            System.out.println("    OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(LINES);
                            break;
                        }

                        // save tasks to duke.txt
                        storage.save(taskList);
                        break;
                    case "deadline":
                        // handle error from empty task description
                        try {
                            Parser.parserDeadlineValidator(userInputTask);
                        } catch (DukeException e) {
                            break;
                        }

                        // splitting deadline into description and by
                        String[] deadlineTaskArr = userInputTask.split(" /by ");
                        String[] byAndTime = deadlineTaskArr[1].split(" ");
                        String by = byAndTime[0];

                        try {
                            String deadlineTime = byAndTime[1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(LINES);
                            System.out.println("    Deadline time is required");
                            System.out.println(LINES);
                            break;
                        }

                        String deadlineTime = byAndTime[1];

                        // handle error when time is not in the hh:mm 24hr clock format
                        try {
                            LocalTime.parse(deadlineTime);
                        } catch (DateTimeParseException e) {
                            System.out.println(LINES);
                            System.out.println("    Time must be in the hh:mm 24hr format");
                            System.out.println(LINES);
                            break;
                        }

                        // handle error when there is invalid deadline date format
                        try {
                            Parser.deadlineDateFormatValidator(by);
                        } catch (DukeException e) {
                            System.out.println(LINES);
                            System.out.println("    OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.");
                            System.out.println(LINES);
                            break;
                        }

                        Parser.parserDeadline(taskList, userInputTask);

                        // save tasks to duke.txt
                        storage.save(taskList);
                        break;

                    case "event":

                        try {
                            Parser.parserEventValidator(userInputTask);
                        } catch (DukeException e) {
                            break;
                        }

                        // splitting event into description and dateTime
                        String[] eventTaskArr = userInputTask.split(" /at ");
                        String[] eventDateAndTime = eventTaskArr[1].split(" ");
                        String eventDate = eventDateAndTime[0];

                        try {
                            String eventTime = eventDateAndTime[1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(LINES);
                            System.out.println("    Event time is required");
                            System.out.println(LINES);
                            break;
                        }

                        String eventTime = eventDateAndTime[1];

                        // handle error when time is not in the hh:mm 24hr clock format
                        try {
                            LocalTime.parse(eventTime);
                        } catch (DateTimeParseException e) {
                            System.out.println(LINES);
                            System.out.println("    Time must be in the hh:mm 24hr format");
                            System.out.println(LINES);
                            break;
                        }

                        LocalTime atTime = LocalTime.parse(eventTime);
                        // handle error when there is invalid deadline date format
                        try {
                            Parser.eventDateFormatValidator(eventDate);
                        } catch (DukeException e) {
                            System.out.println(LINES);
                            System.out.println("    OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.");
                            System.out.println(LINES);
                            break;
                        }

                        Parser.parserEvent(taskList, userInputTask);

                        // save tasks to duke.txt
                        storage.save(taskList);
                        break;

                    case "mark":
                        Parser.parserMark(taskList, userInputArr);

                        // save update tasks to duke.txt
                        storage.save(taskList);
                        break;

                    case "unmark":
                        Parser.parserUnmark(taskList, userInputArr);

                        // save update tasks to duke.txt
                        storage.save(taskList);
                        break;

                    case "delete":
                        try {
                            Parser.parserDeleteValidator(taskList, userInputTask);
                        } catch (DukeException e) {
                            break;
                        }

                        Parser.parserDelete(taskList, userInputArr);

                        // save update tasks to duke.txt
                        storage.save(taskList);
                        break;

                    case "find":
                        Parser.parserFind(taskList, userInputTask);
                        break;
                }
                userInput = sc.nextLine();
            }
        }
    }
}
